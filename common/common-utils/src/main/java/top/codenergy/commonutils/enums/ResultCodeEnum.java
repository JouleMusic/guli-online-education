package top.codenergy.commonutils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangyu
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    /**
     * 成功
     */
    success("100000I");
    private String code;
}
