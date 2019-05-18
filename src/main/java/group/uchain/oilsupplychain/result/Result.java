package group.uchain.oilsupplychain.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import group.uchain.oilsupplychain.enums.CodeMsg;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 结果返回类封装
 * @author clf
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(String message) {
        this.code = 200;
        this.message = message;
    }

    public Result(T data) {
        this.code=200;
        this.message="success";
        this.data = data;
    }

    private Result(CodeMsg codeMsg) {
        if (codeMsg==null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMsg();
    }

    public static <T> Result<T> successData(T data){
        return new Result<T>(data);
    }

    public static Result success(String message){
        return new Result(message);
    }
    public static Result success(){
        return success(null);
    }

    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<>(codeMsg);
    }

    public static  Result<String> error(String msg){
        return new Result<>(msg);
    }
}
