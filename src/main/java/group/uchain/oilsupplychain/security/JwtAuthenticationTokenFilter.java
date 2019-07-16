package group.uchain.oilsupplychain.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtFilter
 *
 * @author panghu
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*
         * 跨域问题的设置
         */
        String option = "OPTIONS";
        if (option.equals(request.getMethod())) {
            log.info("浏览器的预请求的处理..");
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT, OPTIONS, DELETE,HEAD");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Accept, token,Origin, No-Cache, X-Requested-With, If-Modified-Since,authorization,Pragma, Last-Modified, Cache-Control, Expires, Authorization,Token");
            return;
        } else {
            String requestURI = request.getRequestURI();
            log.info("requestURI:{}", requestURI);
        }
        /*
         * 定义的tokenHeader的名称
         * 在postman中在header中作为参数名
         */
        String tokenHeader = "Authorization";
        String authToken = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(authToken);

        log.info("checking authentication for user " + username);

        /*
            当token中的username不为空是进行验证token是否是有效的token
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.info("token中的username不为空,Context中的authentication为空时,进行token的验证..");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            log.info("加载userDetails:{}", userDetails.getUsername());
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);

    }
}