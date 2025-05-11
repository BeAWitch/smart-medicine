package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.medicine.dao.VideoDao;
import top.medicine.entity.Video;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@SpringBootTest
public class VideoServiceTest {

    private VideoService videoService;
    private VideoDao videoDao;

    @Before
    public void setUp() throws Exception {
        // 创建模拟对象
        videoDao = createMock(VideoDao.class);

        // 创建被测试对象
        videoService = new VideoService();

        // 使用反射注入模拟的DAO
        try {
            Field videoDaoField = VideoService.class.getSuperclass().getDeclaredField("videoDao");
            videoDaoField.setAccessible(true);
            videoDaoField.set(videoService, videoDao);
        } catch (Exception e) {
            throw new RuntimeException("依赖注入失败", e);
        }
    }

    @Test
    public void testSave_NewVideo() {
        // 准备测试数据
        Video newVideo = Video.builder()
                .title("New Video")
                .description("Test Description")
                .link("http://example.com/video.mp4")
                .build();

        Video savedVideo = Video.builder()
                .id(1)
                .title("New Video")
                .description("Test Description")
                .link("http://example.com/video.mp4")
                .build();

        // 设置模拟行为
        expect(videoDao.insert(newVideo)).andReturn(1);
        expect(videoDao.selectById(null)).andReturn(savedVideo);
        replay(videoDao);

        // 执行测试
        Video result = videoService.save(newVideo);

        // 验证结果
        assertNotNull(result.getId());
        assertEquals("New Video", result.getTitle());
        verify(videoDao);
    }

    @Test
    public void testSave_UpdateExistingVideo() {
        // 准备测试数据
        Video existingVideo = Video.builder()
                .id(1)
                .title("Updated Title")
                .description("Updated Description")
                .link("http://example.com/updated.mp4")
                .build();

        // 设置模拟行为
        expect(videoDao.updateById(existingVideo)).andReturn(1);
        expect(videoDao.selectById(1)).andReturn(existingVideo);
        replay(videoDao);

        // 执行测试
        Video result = videoService.save(existingVideo);

        // 验证结果
        assertEquals("Updated Title", result.getTitle());
        verify(videoDao);
    }

    @Test
    public void testSave_NullVideo() {
        videoService.save(null);
    }

    @Test
    public void testGet_ExistingId() {
        // 准备测试数据
        Video expectedVideo = Video.builder()
                .id(1)
                .title("Test Video")
                .build();

        // 设置模拟行为
        expect(videoDao.selectById(1)).andReturn(expectedVideo);
        replay(videoDao);

        // 执行测试
        Video result = videoService.get(1);

        // 验证结果
        assertEquals("Test Video", result.getTitle());
        verify(videoDao);
    }

    @Test
    public void testGet_NonExistingId() {
        // 设置模拟行为
        expect(videoDao.selectById(999)).andReturn(null);
        replay(videoDao);

        // 执行测试
        Video result = videoService.get(999);

        // 验证结果
        assertNull(result);
        verify(videoDao);
    }

    @Test
    public void testDelete_ExistingId() {
        // 设置模拟行为
        expect(videoDao.deleteById(1)).andReturn(1);
        replay(videoDao);

        // 执行测试
        int result = videoService.delete(1);

        // 验证结果
        assertEquals(1, result);
        verify(videoDao);
    }

    @Test
    public void testQuery_WithTitleCondition() {
        // 准备测试数据
        Video condition = Video.builder().title("Spring Tutorial").build();
        List<Video> expectedVideos = Arrays.asList(
                Video.builder().id(1).title("Spring Tutorial").build()
        );

        // 设置模拟行为
        expect(videoDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedVideos);
        replay(videoDao);

        // 执行测试
        List<Video> result = videoService.query(condition);

        // 验证结果
        assertEquals(1, result.size());
        assertEquals("Spring Tutorial", result.get(0).getTitle());
        verify(videoDao);
    }

    @Test
    public void testQuery_WithEmptyFields() {
        // 准备测试数据 - 包含空值的查询对象
        Video condition = Video.builder()
                .title("Valid Title")
                .description(null)  // 空字段
                .link("")           // 空字符串
                .build();

        List<Video> expectedVideos = Arrays.asList(
                Video.builder().id(1).title("Valid Title").build()
        );

        // 设置模拟行为
        expect(videoDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedVideos);
        replay(videoDao);

        // 执行测试
        List<Video> result = videoService.query(condition);

        // 验证结果 - 确保空字段被忽略
        assertEquals(1, result.size());
        verify(videoDao);
    }

    @Test
    public void testQuery_WithoutConditions() {
        // 准备测试数据
        List<Video> expectedVideos = Arrays.asList(
                Video.builder().id(1).title("Video 1").build(),
                Video.builder().id(2).title("Video 2").build()
        );

        // 设置模拟行为
        expect(videoDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedVideos);
        replay(videoDao);

        // 执行测试
        List<Video> result = videoService.query(null);

        // 验证结果
        assertEquals(2, result.size());
        verify(videoDao);
    }

    @Test
    public void testAll() {
        // 准备测试数据
        List<Video> expectedVideos = Arrays.asList(
                Video.builder().id(1).build(),
                Video.builder().id(2).build()
        );

        // 设置模拟行为
        expect(videoDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedVideos);
        replay(videoDao);

        // 执行测试
        List<Video> result = videoService.all();

        // 验证结果
        assertEquals(2, result.size());
        verify(videoDao);
    }
}