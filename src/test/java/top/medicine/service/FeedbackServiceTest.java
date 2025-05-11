package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.medicine.dao.FeedbackDao;
import top.medicine.entity.Feedback;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@SpringBootTest
public class FeedbackServiceTest {

    private FeedbackService FeedbackService;
    private FeedbackDao FeedbackDao;

    @Before
    public void setUp() throws Exception {
        // 创建模拟对象
        FeedbackDao = createMock(FeedbackDao.class);

        // 创建被测试对象
        FeedbackService = new FeedbackService();

        // 使用反射注入模拟的DAO
        try {
            Field FeedbackDaoField = FeedbackService.class.getSuperclass().getDeclaredField("FeedbackDao");
            FeedbackDaoField.setAccessible(true);
            FeedbackDaoField.set(FeedbackService, FeedbackDao);
        } catch (Exception e) {
            throw new RuntimeException("依赖注入失败", e);
        }
    }

    @Test
    public void testSave_NewFeedback() {
        // 准备测试数据
        Feedback newFeedback = Feedback.builder()
                .title("New Feedback")
                .build();

        Feedback savedFeedback = Feedback.builder()
                .id(1)
                .title("New Feedback")
                .build();

        // 设置模拟行为
        expect(FeedbackDao.insert(newFeedback)).andReturn(1);
        expect(FeedbackDao.selectById(null)).andReturn(savedFeedback);
        replay(FeedbackDao);

        // 执行测试
        Feedback result = FeedbackService.save(newFeedback);

        // 验证结果
        assertNotNull(result.getId());
        assertEquals("New Feedback", result.getTitle());
        verify(FeedbackDao);
    }

    @Test
    public void testSave_UpdateExistingFeedback() {
        // 准备测试数据
        Feedback existingFeedback = Feedback.builder()
                .id(1)
                .title("Updated Title")
                .build();

        // 设置模拟行为
        expect(FeedbackDao.updateById(existingFeedback)).andReturn(1);
        expect(FeedbackDao.selectById(1)).andReturn(existingFeedback);
        replay(FeedbackDao);

        // 执行测试
        Feedback result = FeedbackService.save(existingFeedback);

        // 验证结果
        assertEquals("Updated Title", result.getTitle());
        verify(FeedbackDao);
    }

    @Test
    public void testSave_NullFeedback() {
        FeedbackService.save(null);
    }

    @Test
    public void testGet_ExistingId() {
        // 准备测试数据
        Feedback expectedFeedback = Feedback.builder()
                .id(1)
                .title("Test Feedback")
                .build();

        // 设置模拟行为
        expect(FeedbackDao.selectById(1)).andReturn(expectedFeedback);
        replay(FeedbackDao);

        // 执行测试
        Feedback result = FeedbackService.get(1);

        // 验证结果
        assertEquals("Test Feedback", result.getTitle());
        verify(FeedbackDao);
    }

    @Test
    public void testGet_NonExistingId() {
        // 设置模拟行为
        expect(FeedbackDao.selectById(999)).andReturn(null);
        replay(FeedbackDao);

        // 执行测试
        Feedback result = FeedbackService.get(999);

        // 验证结果
        assertNull(result);
        verify(FeedbackDao);
    }

    @Test
    public void testDelete_ExistingId() {
        // 设置模拟行为
        expect(FeedbackDao.deleteById(1)).andReturn(1);
        replay(FeedbackDao);

        // 执行测试
        int result = FeedbackService.delete(1);

        // 验证结果
        assertEquals(1, result);
        verify(FeedbackDao);
    }

    @Test
    public void testQuery_WithTitleCondition() {
        // 准备测试数据
        Feedback condition = Feedback.builder().title("Spring Tutorial").build();
        List<Feedback> expectedFeedbacks = Arrays.asList(
                Feedback.builder().id(1).title("Spring Tutorial").build()
        );

        // 设置模拟行为
        expect(FeedbackDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedFeedbacks);
        replay(FeedbackDao);

        // 执行测试
        List<Feedback> result = FeedbackService.query(condition);

        // 验证结果
        assertEquals(1, result.size());
        assertEquals("Spring Tutorial", result.get(0).getTitle());
        verify(FeedbackDao);
    }

    @Test
    public void testQuery_WithEmptyFields() {
        // 准备测试数据 - 包含空值的查询对象
        Feedback condition = Feedback.builder()
                .title("Valid Title")
                .build();

        List<Feedback> expectedFeedbacks = Arrays.asList(
                Feedback.builder().id(1).title("Valid Title").build()
        );

        // 设置模拟行为
        expect(FeedbackDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedFeedbacks);
        replay(FeedbackDao);

        // 执行测试
        List<Feedback> result = FeedbackService.query(condition);

        // 验证结果 - 确保空字段被忽略
        assertEquals(1, result.size());
        verify(FeedbackDao);
    }

    @Test
    public void testQuery_WithoutConditions() {
        // 准备测试数据
        List<Feedback> expectedFeedbacks = Arrays.asList(
                Feedback.builder().id(1).title("Feedback 1").build(),
                Feedback.builder().id(2).title("Feedback 2").build()
        );

        // 设置模拟行为
        expect(FeedbackDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedFeedbacks);
        replay(FeedbackDao);

        // 执行测试
        List<Feedback> result = FeedbackService.query(null);

        // 验证结果
        assertEquals(2, result.size());
        verify(FeedbackDao);
    }

    @Test
    public void testAll() {
        // 准备测试数据
        List<Feedback> expectedFeedbacks = Arrays.asList(
                Feedback.builder().id(1).build(),
                Feedback.builder().id(2).build()
        );

        // 设置模拟行为
        expect(FeedbackDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedFeedbacks);
        replay(FeedbackDao);

        // 执行测试
        List<Feedback> result = FeedbackService.all();

        // 验证结果
        assertEquals(2, result.size());
        verify(FeedbackDao);
    }
}