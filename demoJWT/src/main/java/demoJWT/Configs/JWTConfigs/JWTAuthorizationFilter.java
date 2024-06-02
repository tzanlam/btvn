package demoJWT.Configs.JWTConfigs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JWTAuthorizationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = JWTTokenService.parseTokenToUserInfomation((HttpServletRequest) servletRequest);

        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
