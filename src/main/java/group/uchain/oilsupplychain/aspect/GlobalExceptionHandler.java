package group.uchain.oilsupplychain.aspect;

import group.uchain.oilsupplychain.enums.CodeMsg;
import group.uchain.oilsupplychain.exception.OilException;
import group.uchain.oilsupplychain.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author panghu
 * @title: GlobalExceptionHandler
 * @projectName oil-supply-chain
 * @date 19-4-7 下午5:28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    @ResponseBody
    @ExceptionHandler(OilException.class)
    public Result handleOilException(Exception exception){
        log.error(EXCEPTION_MSG_KEY+exception.getMessage());
        return Result.error(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public Result<Object> handleSQLException(SQLException exception){
        log.error(EXCEPTION_MSG_KEY+exception.getMessage());
        return Result.error(CodeMsg.DATABASE_ERROR);
    }

}
