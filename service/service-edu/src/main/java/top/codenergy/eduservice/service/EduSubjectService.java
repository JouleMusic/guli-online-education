package top.codenergy.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.codenergy.eduservice.entity.EduSubject;

import java.io.IOException;

/**
 *
 * @author wangyu
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file) throws IOException;
}
