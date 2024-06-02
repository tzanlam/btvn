package demoJWT.Service;

import demoJWT.DTO.UserDTO;
import demoJWT.Form.FormLogin;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    UserDTO Login(FormLogin form,  HttpServletResponse response);
}
