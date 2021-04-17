package top.codenergy.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangyu
 */

@Component
public class ConstantPropertiesUtils implements InitializingBean {
    /**
     * 读取配置文件中内容
     */
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    /**
     * 读取配置文件中内容
     */
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    /**
     * 读取配置文件中内容
     */
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    /**
     * 读取配置文件中内容
     */
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    /**
     * 定义公开静态常量
     */
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;
    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
