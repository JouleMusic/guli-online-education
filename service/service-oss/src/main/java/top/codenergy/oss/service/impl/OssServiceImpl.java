package top.codenergy.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.codenergy.oss.service.OssService;
import top.codenergy.oss.utils.ConstantPropertiesUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author wangyu
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String filename = file.getOriginalFilename();
            //1.在文件名中添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid + filename;
            //2.把文件按照日期进行分类，比如2020/11/22/01.jpg
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            filename = datePath + "/" + filename;
            // 调用oss方法实现上传
            /**
             * 第一个参数 bucket名称
             * 第二个参数 上传到oss的文佳路径和名称，比如/aa/bb/1.jpg
             * 第三个参数 上传文件的输入流
             */
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            //把文件上传后要返回路径，这里需要手动拼接oss路径
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return url;
        } catch (IOException e){
            return null;
        }
    }
}
