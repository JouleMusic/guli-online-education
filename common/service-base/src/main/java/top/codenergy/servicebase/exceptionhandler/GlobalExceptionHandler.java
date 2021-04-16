package top.codenergy.servicebase.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codenergy.commonutils.ResultObj;

/**
 * @author wangyu
 */
@ControllerAdvice
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
}
