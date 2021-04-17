package top.codenergy.commonutils.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultObjTest<T> {
    /**
     * 返回编码
     */
    private String code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public T getData(){
        //这里的逻辑可以确保以后调用时，用success方法就可以
        if (isSuccess()){
            return data;
        } else{
            //TODO，涉及到异常的处理
            throw new RemoteServiceException(code, msg);
        }
    }
    public boolean isSuccess(){
        boolean flag = (code == null) || (code.trim().length() == 0);
        return !flag && code.endsWith("I");
    }
}
