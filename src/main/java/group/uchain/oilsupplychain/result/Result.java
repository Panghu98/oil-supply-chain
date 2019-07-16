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
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result() {
        this.code = 200;
        this.message = "操作成功";
    }

    public Result(String message) {
        this.code = 500;
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

    private Result(T data, String message) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public static <T> Result dataAndMessage(T data, String message){
        if (message  == null){
            return new Result<>(data, "success");
        }else {
            return new Result<>(data, message);
        }
    }

    public static <T> Result<T> successData(T data){
        return new Result<T>(data);
    }

    public static Result success(){
        return new Result();
    }

    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<>(codeMsg);
    }

    public static  Result<String> error(String msg){
        return new Result<>(msg);
    }
}
