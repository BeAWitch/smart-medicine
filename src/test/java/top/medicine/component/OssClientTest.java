package top.medicine.component;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import top.medicine.component.OssClient;

@SpringBootTest
public class OssClientTest {

    private OssClient ossClient;
    private OSSClient mockOssClient;

    @Before
    public void setUp() {
        // 创建被测试对象
        ossClient = new OssClient();

        // 使用反射设置私有字段
        setField(ossClient, "bucketName", "test-bucket");
        setField(ossClient, "endPoint", "test-endpoint");
        setField(ossClient, "accessKeyId", "test-access-key");
        setField(ossClient, "accessKeySecret", "test-secret-key");

        // 创建模拟OSSClient
        mockOssClient = createMock(OSSClient.class);
    }

    // 辅助方法：通过反射设置私有字段
    private void setField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testUpload_Success() throws IOException {
        // 准备测试数据
        MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpeg", "image content".getBytes());

        // 模拟行为
        expect(mockOssClient.doesBucketExist("test-bucket")).andReturn(false);
        expect(mockOssClient.createBucket(anyObject(CreateBucketRequest.class))).andReturn(new Bucket());

        PutObjectResult putResult = new PutObjectResult();
        expect(mockOssClient.putObject(anyObject(PutObjectRequest.class))).andReturn(putResult);

        mockOssClient.setBucketAcl(eq("test-bucket"), eq(CannedAccessControlList.PublicRead));
        expectLastCall();

        replay(mockOssClient);

        // 执行测试
        String result = ossClient.upload(file, "uploads");

        // 验证结果
        assertNotNull(result);
        verify(mockOssClient);
    }

    @Test
    public void testUpload_BucketAlreadyExists() throws IOException {
        // 准备测试数据
        String path = "test/path";
        String filename = "test.jpg";

        MockMultipartFile file = new MockMultipartFile(
                "file", filename, "image/jpeg", "test data".getBytes());

        // 模拟行为 - 桶已存在
        expect(mockOssClient.doesBucketExist("test-bucket")).andReturn(true);

        PutObjectResult putResult = new PutObjectResult();
        expect(mockOssClient.putObject(anyObject(PutObjectRequest.class))).andReturn(putResult);

        mockOssClient.setBucketAcl(eq("test-bucket"), eq(CannedAccessControlList.PublicRead));
        expectLastCall();

        replay(mockOssClient);

        // 执行测试
        String result = ossClient.upload(file, path);

        // 验证结果
        assertNotNull(result);
        verify(mockOssClient);
    }

    @Test
    public void testUpload_NullFile() throws IOException {
        // 执行测试
        String result = ossClient.upload(null, "test/path");

        // 验证结果
        assertNull(result);
    }

}