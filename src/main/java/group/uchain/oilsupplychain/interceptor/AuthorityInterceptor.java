package group.uchain.oilsupplychain.interceptor;


import com.alibaba.fastjson.JSON;
import group.uchain.oilsupplychain.annotation.RoleRequired;
import group.uchain.oilsupplychain.dto.User;
import group.uchain.oilsupplychain.enums.CodeMsg;
import group.uchain.oilsupplychain.result.Result;
import group.uchain.oilsupplychain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dgh
 * @date 19-1-19 下午7:54
 */
@Service
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String json;
        User user = userService.getCurrentUser();
        log.info(String.valueOf(user));
        if (user == null) {
            return true;
        }
        log.info("Security 执行权限验证--------");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleRequired roleRequired = handlerMethod.getMethodAnnotation(RoleRequired.class);
            if (roleRequired == null) {
                return true;
            }
            /*将需要的权限对应的数字转化成字符数组,通过字符进行对比*/
            String[] requireValue = roleRequired.value().getRole().toString().split("");
            String[] requireValueArr =  new String[requireValue.length];
            for(int i=0;i<requireValueArr.length;i++)
            {
                requireValueArr[i] = requireValue[i];
            }
            String userValue = user.getRole();
            log.info("requireValue:{},userValue:{}", requireValueArr, userValue);
            for (String c : requireValueArr) {
                if (userValue.equals(c)) {
                    return true;
                }
            }
        }
        json = JSON.toJSONString(Result.error(CodeMsg.PERMISSION_DENNY));
        log.error("............权限不足...........");
        response.getWriter().append(json);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
