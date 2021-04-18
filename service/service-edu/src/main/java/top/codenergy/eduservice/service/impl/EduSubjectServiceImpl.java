package top.codenergy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.codenergy.eduservice.dao.EduSubjectMapper;
import top.codenergy.eduservice.entity.EduSubject;
import top.codenergy.eduservice.entity.excel.SubjectData;
import top.codenergy.eduservice.listener.SubjectExcelListener;
import top.codenergy.eduservice.service.EduSubjectService;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author wangyu
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
implements EduSubjectService{
    /**
     * 添加课程分类
     */
    @Autowired
    private EduSubjectService subjectService;
    @Override
    public void saveSubject(MultipartFile file) throws IOException {
        try (InputStream in = file.getInputStream()) {
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }
    }
}




