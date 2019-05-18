package group.uchain.oilsupplychain.test;
import lombok.Data;

import java.util.Date;

/**
 * JSON序列化和反序列化使用的User类
 * @author panghu
 */
@Data
public class User {

    private String name;
    private Integer age;
    private Date birthday;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                '}';
    }
}