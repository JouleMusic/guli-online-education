package top.codenergy.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author wangyu
 */
public interface OssService {
    /**
     * 上传头像图片文件
     * @param file file
     * @return 返回图片的路径
     */
    String uploadFileAvatar(MultipartFile file) throws IOException;
}
