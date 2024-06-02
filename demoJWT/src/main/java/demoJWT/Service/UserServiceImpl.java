package demoJWT.Service;

import demoJWT.Configs.JWTConfigs.JWTTokenService;
import demoJWT.DTO.UserDTO;
import demoJWT.Entity.User;
import demoJWT.Form.FormLogin;
import demoJWT.Repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        else {
            return new org.springframework.security.core.userdetails.User(username,user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        }
    }

    @Override
    public UserDTO Login(FormLogin form,  HttpServletResponse response) {
        // Xác thực thông tin đăng nhập
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));

        // Đặt thông tin xác thực vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Thêm JWT token vào tiêu đề phản hồi HTTP
        JWTTokenService.addJWTTokenToHeaders(response, form.getUsername());

        // Tạo và trả về đối tượng UserDTO
        String token = JWTTokenService.addJWTTokenToHeaders(response, form.getUsername());
        return new UserDTO(form.getUsername(), token);
    }
}
