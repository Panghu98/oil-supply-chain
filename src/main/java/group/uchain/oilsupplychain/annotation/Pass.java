package group.uchain.oilsupplychain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author panghu
 * @title: Pass
 * @projectName demo
 * @date 19-5-5 上午10:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Pass {
    
    String value() default "";
    
    
    
}
