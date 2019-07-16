package group.uchain.oilsupplychain.service.impl;

import group.uchain.oilsupplychain.enums.CodeMsg;
import group.uchain.oilsupplychain.mapper.UserFormMapper;
import group.uchain.oilsupplychain.result.Result;
import group.uchain.oilsupplychain.security.JwtTokenUtil;
import group.uchain.oilsupplychain.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dgh
 * @date 19-1-19 下午12:57
 */
@Slf4j
@Service
public class LoginServiceImpl  implements LoginService {

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private UserFormMapper userFormMapper;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                            UserDetailsService userDetailsService, UserFormMapper userFormMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userFormMapper = userFormMapper;
    }

    @Override
    public Result login(String username, String password) {
        if (userFormMapper.selectByUsername(username) == null) {
            log.info("用户不存在");
            return Result.error(CodeMsg.USER_NOT_EXIST);
        }

        log.info("进行验证用户密码..");
        Authentication token1 = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token1);
        log.info("验证通过..");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //返回的对象中是带有权限信息的
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info("加载userDetails:" + userDetails.getUsername());
        //将UserDetails放入Token的 payload中
        final String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String, String> r = new HashMap<>(10);
        r.put("token", token);

        String role = userFormMapper.selectByUsername(username).getRole();
        r.put("role", String.valueOf(role));

        return Result.successData(r);
    }



}
