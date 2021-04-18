package top.codenergy.eduservice.controller;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.codenergy.commonutils.ResultObj;
import top.codenergy.eduservice.service.EduSubjectService;
import top.codenergy.servicebase.exceptionhandler.GuliException;

import java.io.IOException;

/**
 * @author wangyu
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public ResultObj addSubject(MultipartFile file) throws IOException {
        if (ObjectUtil.isEmpty(file)){
            throw new GuliException(20002,"未传入文件！");
        }
        eduSubjectService.saveSubject(file);
        return ResultObj.ok();
    }
}
