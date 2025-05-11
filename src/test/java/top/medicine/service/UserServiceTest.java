package top.medicine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.medicine.dao.UserDao;
import top.medicine.entity.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    private UserService userService;
    private UserDao userDao;

    @Before
    public void setUp() {
        // 创建模拟对象
        userDao = createMock(UserDao.class);

        // 创建被测试对象并注入依赖
        userService = new UserService();

        // 使用反射注入模拟的DAO
        try {
            Field userDaoField = UserService.class.getSuperclass().getDeclaredField("userDao");
            userDaoField.setAccessible(true);
            userDaoField.set(userService, userDao);
        } catch (Exception e) {
            throw new RuntimeException("依赖注入失败", e);
        }
    }

    @Test
    public void testQuery_WithConditions() {
        // 准备测试数据
        User queryCondition = new User();
        queryCondition.setUserAccount("testUser");

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User());

        // 设置模拟行为
        expect(userDao.selectList(anyObject(QueryWrapper.class))).andReturn(expectedUsers);
        replay(userDao);

        // 执行测试
        List<User> result = userService.query(queryCondition);

        // 验证结果
        assertEquals(1, result.size());
        verify(userDao);
    }

    @Test
    public void testQuery_WithoutConditions() {
        // 设置模拟行为
        expect(userDao.selectList(anyObject(QueryWrapper.class))).andReturn(new ArrayList<>());
        replay(userDao);

        // 执行测试
        List<User> result = userService.query(null);

        // 验证结果
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSave_NewUser() {
        // 准备测试数据
        User newUser = new User();
        newUser.setUserAccount("newUser");

        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setUserAccount("newUser");

        // 设置模拟行为
        expect(userDao.insert(newUser)).andReturn(1);
        expect(userDao.selectById(null)).andReturn(savedUser);
        replay(userDao);

        // 执行测试
        User result = userService.save(newUser);

        // 验证结果
        assertNotNull(result.getId());
        assertEquals("newUser", result.getUserAccount());
        verify(userDao);
    }

    @Test
    public void testSave_ExistingUser() {
        // 准备测试数据
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setUserAccount("updatedUser");

        // 设置模拟行为
        expect(userDao.updateById(existingUser)).andReturn(1);
        expect(userDao.selectById(1)).andReturn(existingUser);
        replay(userDao);

        // 执行测试
        User result = userService.save(existingUser);

        // 验证结果
        assertEquals("updatedUser", result.getUserAccount());
        verify(userDao);
    }

    @Test
    public void testGetIdToNameMap() {
        // 准备测试数据
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setUserAccount("user1");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setUserAccount("user2");
        users.add(user2);

        // 设置模拟行为
        expect(userDao.selectList(anyObject(QueryWrapper.class))).andReturn(users);
        replay(userDao);

        // 执行测试
        Map<Integer, String> result = userService.getIdToNameMap();

        // 验证结果
        assertEquals(2, result.size());
        assertEquals("user1", result.get(1));
        assertEquals("user2", result.get(2));
        verify(userDao);
    }

    @Test
    public void testFindUserOne() {
        // 准备测试数据
        User testUser = new User();
        testUser.setId(1);
        testUser.setUserAccount("testUser");

        // 设置模拟行为
        expect(userDao.selectOne(anyObject(QueryWrapper.class))).andReturn(testUser);
        replay(userDao);

        // 执行测试
        Map<String, Object> result = userService.findUserOne(1);

        // 验证结果
        assertNotNull(result.get("user"));
        User returnedUser = (User) result.get("user");
        assertEquals("testUser", returnedUser.getUserAccount());
        verify(userDao);
    }

    @Test
    public void testSave_NullUser() {
        userService.save(null);
    }

    @Test
    public void testGet_NonExistingId() {
        // 设置模拟行为
        expect(userDao.selectById(999)).andReturn(null);
        replay(userDao);

        // 执行测试
        User result = userService.get(999);

        // 验证结果
        assertNull(result);
        verify(userDao);
    }
}