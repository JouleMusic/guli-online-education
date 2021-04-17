package top.codenergy.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.codenergy.commonutils.ResultObj;
import top.codenergy.oss.service.OssService;

import java.io.IOException;

/**
 * @author wangyu
 */
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;
    /**
     * 上传头像的方法
     */
    @PostMapping
    public ResultObj uploadOssFile(MultipartFile file) throws IOException {
        //获取上传文件,返回上传到oss得到的头像路径
        String url = ossService.uploadFileAvatar(file);
        return ResultObj.ok().data("url",url);
    }
}
