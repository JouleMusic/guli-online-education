package top.codenergy.eduservice.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.codenergy.commonutils.ResultObj;
import top.codenergy.eduservice.entity.EduTeacher;
import top.codenergy.eduservice.service.EduTeacherService;
import top.codenergy.eduservice.vo.TeacherQueryVo;

import java.util.HashMap;
import java.util.List;

/**
 * @author wangyu
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    @GetMapping("/findAll")
    public ResultObj findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return ResultObj.ok().data("item",list);
    }

    @PostMapping("/{id}")
    public ResultObj deleteById(@PathVariable String id){
        Boolean flag = teacherService.removeLogicalById(id);
        return flag ? ResultObj.ok():ResultObj.error();
    }

    @GetMapping("/pageTeacher/{current}/{limit}")
    public ResultObj pageTeacher(@PathVariable Long current,
                                 @PathVariable Long limit){
        //创建page
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页
        //调用方法时底层封装，把分页数据封装到pageTeacher中
        teacherService.page(pageTeacher, null);
        //获取总记录数
        Long total = pageTeacher.getTotal();
        //获取分页后的list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return ResultObj.ok().data(map);
    }

    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public ResultObj pageTeacherCondition(@PathVariable Long current,
                                          @PathVariable Long limit,
                                          @RequestBody TeacherQueryVo teacherQueryVo){
        //创建page
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建条件

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        //判断条件是否为空，拼接条件
        wrapper.eq(!StrUtil.isEmpty(name),"name",name)
                .eq(level != null,"level",level)
                .eq(!StrUtil.isEmpty(begin),"gmt_create",begin)
                .eq(!StrUtil.isEmpty(end),"gmt_create",end)
                .orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);
        //获取总记录数
        Long total = pageTeacher.getTotal();
        //获取分页后的list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return ResultObj.ok().data("total",total).data("rows",records);

    }

}
