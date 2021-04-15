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

    /**
     * 查询讲师表所有数据
     * @return 讲师表所有数据
     */
    @GetMapping("/findAll")
    public ResultObj findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return ResultObj.ok().data("item",list);
    }

    /**
     * 根据id进行逻辑删除
     * @param id id
     * @return 删除成功标识
     */
    @PostMapping("/{id}")
    public ResultObj deleteById(@PathVariable String id){
        Boolean flag = teacherService.removeLogicalById(id);
        return flag ? ResultObj.ok():ResultObj.error();
    }

    /**
     * 讲师分页查询
     * @param current 当前页
     * @param limit 每页条数
     * @return 讲师分页查询结果
     */
    @GetMapping("/pageTeacher/{current}/{limit}")
    public ResultObj pageTeacher(@PathVariable Long current,
                                 @PathVariable Long limit){
        // 创建page
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 调用方法实现分页
        // 调用方法时底层封装，把分页数据封装到pageTeacher中
        teacherService.page(pageTeacher, null);
        // 获取总记录数
        Long total = pageTeacher.getTotal();
        // 获取分页后的list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return ResultObj.ok().data(map);
    }

    /**
     * 讲师分页条件查询
     * @param current 当前页
     * @param limit 每页条数
     * @param teacherQueryVo 查询条件VO
     * @return 讲师分页条件查询结果
     */
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public ResultObj pageTeacherCondition(@PathVariable Long current,
                                          @PathVariable Long limit,
                                          @RequestBody(required = false) TeacherQueryVo teacherQueryVo){
        // 创建page
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        // 判断条件是否为空，拼接条件
        wrapper.likeRight(!StrUtil.isEmpty(name),"name",name.replace("*",""))
                .eq(level != null,"level",level)
                .eq(!StrUtil.isEmpty(begin),"gmt_create",begin)
                .eq(!StrUtil.isEmpty(end),"gmt_create",end)
                .orderByDesc("gmt_create");
        // 调用方法实现条件查询分页
        teacherService.page(pageTeacher, wrapper);
        // 获取总记录数
        Long total = pageTeacher.getTotal();
        // 获取分页后的list集合
        List<EduTeacher> records = pageTeacher.getRecords();
        return ResultObj.ok().data("total",total).data("rows",records);
    }

    /**
     * 添加讲师接口的方法
     * @param eduTeacher 要添加的讲师信息
     * @return 添加成功标识
     */
    @PostMapping("/addTeacher")
    public ResultObj addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        return save ? ResultObj.ok():ResultObj.error();
    }

    /**
     * 根据id查询教师
     * @param id id
     * @return 查询结果
     */
    @GetMapping("/getTeacher/{id}")
    public ResultObj getTeacher(@PathVariable String id){
        EduTeacher byId = teacherService.getById(Long.parseLong(id));
        return ResultObj.ok().data("teacher", byId);
    }

    /**
     * 修改讲师信息
     * @param eduTeacher 讲师信息类
     * @return 修改成功标识
     */
    @PostMapping("/updateTeacher")
    public ResultObj updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        return b ? ResultObj.ok():ResultObj.error();
    }

}
