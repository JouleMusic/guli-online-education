package top.codenergy.eduservice.vo;

import lombok.Data;

/**
 * @author wangyu
 */
@Data
public class TeacherQueryVo {
    /**
     * 讲师姓名
     */
    private String name;
    /**
     * 讲师级别
     */
    private Integer level;
    /**
     * 查询开始时间
     */
    private String begin;
    /**
     * 查询结束时间
     */
    private String end;

}
