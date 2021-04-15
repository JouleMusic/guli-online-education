package top.codenergy.commonutils;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyu
 *
 */
@Data
public class ResultObj {
    /**
     * 是否返回成功
     */
    private Boolean success;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<>();

    /**
     * 私有化构造器，其他人就无法随便使用，必须要用该类自定义的静态方法
     */
    private ResultObj(){}

    /**
     * 成功返回的静态方法
     * @return 返回成功结果
     */
    public static ResultObj ok(){
        ResultObj resultObj = new ResultObj();
        resultObj.setSuccess(true);
        resultObj.setCode(ResultCode.SUCCESS);
        resultObj.setMessage("成功");
        return resultObj;
    }

    /**
     * 失败返回的静态方法
     * @return 返回失败结果
     */
    public static ResultObj error(){
        ResultObj resultObj = new ResultObj();
        resultObj.setSuccess(false);
        resultObj.setCode(ResultCode.ERROR);
        resultObj.setMessage("失败");
        return resultObj;
    }

    //链式
    public ResultObj success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultObj message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultObj code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultObj data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultObj data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
