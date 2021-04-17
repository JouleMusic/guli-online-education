package top.codenergy.commonutils.test;

import top.codenergy.commonutils.enums.ResultCodeEnum;

/**
 * @author wangyu
 */
public interface BaseResultInterface {
    String OK_CODE_REGEX = "^[0-9]+I$";
    String ERROR_CODE_REGEX = "^[0-9]+E$";

    default <T> ResultObjTest<T> success(){
        return success(ResultCodeEnum.success.getCode(), null);
    }

    <T> ResultObjTest<T> success(String code, Object o);
}
