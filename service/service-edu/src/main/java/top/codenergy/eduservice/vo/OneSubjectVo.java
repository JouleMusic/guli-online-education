package top.codenergy.eduservice.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyu
 */
@Data
public class OneSubjectVo {
    private String id;
    private String title;
    private List<TwoSubjectVo> children = new ArrayList<>();
}
