package group.uchain.oilsupplychain.service.impl;


import group.uchain.oilsupplychain.dto.User;
import group.uchain.oilsupplychain.mapper.UserFormMapper;
import group.uchain.oilsupplychain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author panghu
 * @Title: UserServiceImpl
 * @ProjectName demo
 * @date 19-1-21 下午5:00
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFormMapper userMapper;

    @Override
    public User getCurrentUser() {
        String anonymousUser = "anonymousUser";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (!anonymousUser.equals(name)) {
            return userMapper.selectByUsername(name);
        }
        return null;
    }

}
