package top.codenergy.eduservice.dao;

import org.apache.ibatis.annotations.Mapper;
import top.codenergy.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wangyu
 * @Entity top.codenergy.entity.EduTeacher
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {
    /**
     * 根据id进行逻辑删除
     * @param id id
     * @return 删除成功标识
     */
    Boolean removeLogicalById(String id);
}




