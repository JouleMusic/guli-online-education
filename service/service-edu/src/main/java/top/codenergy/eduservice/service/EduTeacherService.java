package top.codenergy.eduservice.service;

import top.codenergy.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author wangyu
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 逻辑删除
     * @param id
     * @return 删除成功标识
     */
    Boolean removeLogicalById(String id);

}
