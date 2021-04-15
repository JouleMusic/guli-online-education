package top.codenergy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.codenergy.eduservice.dao.EduTeacherMapper;
import top.codenergy.eduservice.entity.EduTeacher;
import top.codenergy.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;

/**
 * @author wangyu
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
        implements EduTeacherService {
    @Autowired
    private EduTeacherMapper teacherMapper;

    /**
     * 逻辑删除
     *
     * @param id id
     * @return 删除成功标识
     */
    @Override
    public Boolean removeLogicalById(String id) {
        //先查询id是否存在（只能只计算id记录数来判断是否存在）
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("is_deleted",0).last("limit 1");
        Integer flag = teacherMapper.selectCount(queryWrapper);
        if (flag > 0){
            return teacherMapper.removeLogicalById(id);
        }
        return false;
    }
}




