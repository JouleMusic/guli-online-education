package top.codenergy.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.codenergy.eduservice.entity.EduSubject;
import top.codenergy.eduservice.vo.OneSubjectVo;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author wangyu
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file) throws IOException;

    List<OneSubjectVo> getAllOneTwoSubject();
}
