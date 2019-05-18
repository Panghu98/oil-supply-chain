package group.uchain.oilsupplychain.annotation;


import group.uchain.oilsupplychain.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dgh
 * @date 19-1-19 下午7:48
 * @Target 这里可以将对象设置为整个类,但是要对Interceptor中的捕捉对象进行修改
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleRequired {
    RoleEnum value();

}
