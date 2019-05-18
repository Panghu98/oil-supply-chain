package group.uchain.oilsupplychain.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author cyg
 * @date 18-9-2 下午4:52
 **/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -6238634545543667266L;

    /**
     * 拦截身份认证异常
     **/
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if (authException instanceof UsernameNotFoundException) {
            response.sendError(401, "用户名未找到");
        }
        //当访问时没有有效凭证时进行拦截返回401未认证
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "访问拦截Unauthorized");
    }
}