package top.codenergy.eduservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.codenergy.eduservice.entity.EduTeacher;
import top.codenergy.eduservice.service.EduTeacherService;

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
    public List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }
    @PostMapping("/{id}")
    public Boolean deleteById(@PathVariable String id){
        return teacherService.removeLogicalById(id);
    }

}
