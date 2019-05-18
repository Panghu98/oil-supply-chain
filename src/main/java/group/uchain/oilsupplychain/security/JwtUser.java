package group.uchain.oilsupplychain.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;


/**
 * @description 封装用户的相关信息,在UserDetailService中封装了这个对象
 * 默认密码为空字符串  , 并且设置为启用,没有锁定 . 没有过期.
 *
 * @author panghu
 */
@Component
@Slf4j
@Data
public class JwtUser implements UserDetails {

    private String username;
    private String password;
    private String role;


    public JwtUser(String username, String password,String role) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role;

    }

    public JwtUser() {

    }


    /**
     * 可以不进行权限的配置
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 默认有效账户
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 默认账户没有被锁
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 默认凭证有效
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 默认账户可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


}
