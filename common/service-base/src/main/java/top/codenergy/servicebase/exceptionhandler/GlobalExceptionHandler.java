package top.codenergy.servicebase.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codenergy.commonutils.ResultObj;

/**
 * @author wangyu
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 该注解ExceptionHandler指定什么异常就会捕获什么异常
     * 注解ResponseBody让数据返回json格式
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultObj error(Exception e){
        e.printStackTrace();
        return ResultObj.error().message("全局异常处理。");
    }
    /**
     * 特定异常,当她与全局异常同时出现时，会优先执行特定的异常
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultObj error(ArithmeticException e){
        e.printStackTrace();
        return ResultObj.error().message("除零异常。");
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public ResultObj error(GuliException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultObj.error().code(e.getCode()).message(e.getMsg());
    }
}
