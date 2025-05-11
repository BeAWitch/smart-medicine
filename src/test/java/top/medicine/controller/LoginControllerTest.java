package top.medicine.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.medicine.component.EmailClient;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;
import top.medicine.service.UserService;

import javax.servlet.http.HttpSession;

import java.util.*;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginControllerTest {

    private LoginController loginController;
    private UserService userService;
    private HttpSession session;
    private EmailClient emailClient;

    @Before
    public void setUp() {
        // 创建模拟对象
        userService = createMock(UserService.class);
        session = createMock(HttpSession.class);
        emailClient = createMock(EmailClient.class);

        // 创建被测试对象并注入模拟依赖
        loginController = new LoginController();
        loginController.userService = userService;
        loginController.session = session;
        loginController.emailClient = emailClient;
    }

    // 邮箱为空
    @Test
    public void testRegister_EmptyEmail() {
        // 准备测试数据
        User user = new User();
        user.setUserEmail("");
        String code = "123456";

        // 执行测试
        RespResult result = loginController.register(user, code);

        // 验证结果
        assertEquals("邮箱不能为空", result.getMessage());
        assertEquals("FAIL", result.getCode());
    }

    // 验证码未发送
    @Test
    public void testRegister_CodeNotSent() {
        // 准备测试数据
        User user = new User();
        user.setUserEmail("test@example.com");
        String code = "123456";

        // 设置模拟行为
        expect(session.getAttribute("EMAIL_CODEtest@example.com")).andReturn(null);
        replay(session);

        // 执行测试
        RespResult result = loginController.register(user, code);

        // 验证结果
        assertEquals("尚未发送验证码", result.getMessage());
        assertEquals("FAIL", result.getCode());
        verify(session);
    }

    // 验证码超时
    @Test
    public void testRegister_CodeExpired() {
        // 准备测试数据
        User user = new User();
        user.setUserEmail("test@example.com");
        String code = "123456";

        // 准备过期验证码数据
        Map<String, Object> codeData = new HashMap<>();
        codeData.put("code", "123456");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -6); // 设置为6分钟前，已过期
        codeData.put("time", calendar.getTime());

        // 设置模拟行为
        expect(session.getAttribute("EMAIL_CODEtest@example.com")).andReturn(codeData);
        session.removeAttribute("EMAIL_CODEtest@example.com");
        replay(session);

        // 执行测试
        RespResult result = loginController.register(user, code);

        // 验证结果
        assertEquals("验证码已经超时", result.getMessage());
        assertEquals("FAIL", result.getCode());
        verify(session);
    }

    // 登录成功
    @Test
    public void testLogin_Success() {
        // 准备测试数据
        User user = new User();
        user.setUserAccount("testuser");
        user.setUserPwd("correctpwd");

        List<User> userList = new ArrayList<>();
        userList.add(user);

        // 设置模拟行为
        expect(userService.query(user)).andReturn(userList);
        session.setAttribute("loginUser", user);
        replay(userService, session);

        // 执行测试
        RespResult result = loginController.login(user);

        // 验证结果
        assertEquals("登录成功", result.getMessage());
        assertEquals("SUCCESS", result.getCode());
        verify(userService, session);
    }

    // 账户未注册
    @Test
    public void testLogin_AccountNotRegistered() {
        // 准备测试数据
        User user = new User();
        user.setUserAccount("nonexistent");
        user.setUserPwd("anypwd");

        // 设置模拟行为
        expect(userService.query(user)).andReturn(new ArrayList<>());
        expect(userService.query(User.builder().userAccount("nonexistent").build()))
                .andReturn(new ArrayList<>());
        replay(userService);

        // 执行测试
        RespResult result = loginController.login(user);

        // 验证结果
        assertEquals("账户尚未注册", result.getMessage());
        assertEquals("FAIL", result.getCode());
        verify(userService);
    }

    // 密码错误
    @Test
    public void testLogin_Fail_WrongPassword() {
        // 准备测试数据
        User inputUser = new User();
        inputUser.setUserAccount("testUser");
        inputUser.setUserPwd("wrongPwd");

        // 模拟数据库返回的用户(使用正确密码)
        User dbUser = new User();
        dbUser.setUserAccount("testUser");
        dbUser.setUserPwd("correctPwd");
        List<User> userList = Collections.singletonList(dbUser);

        // 设置模拟行为
        expect(userService.query(inputUser)).andReturn(new ArrayList<>()); // 密码不匹配时返回空列表
        expect(userService.query(User.builder().userAccount("testUser").build()))
                .andReturn(userList); // 验证账户是否存在
        replay(userService);

        // 执行测试
        RespResult result = loginController.login(inputUser);

        // 验证结果
        assertEquals("密码错误", result.getMessage());
        assertEquals("FAIL", result.getCode());
        verify(userService);
    }

    // 邮箱为空
    @Test
    public void testSendEmailCode_EmptyEmail() {
        // 执行测试
        RespResult result = loginController.sendEmailCode("", new HashMap<>());

        // 验证结果
        assertEquals("邮箱不可为空", result.getMessage());
        assertEquals("FAIL", result.getCode());
    }

    // 发送验证码成功
    @Test
    public void testSendEmailCode_Success() {
        // 准备测试数据
        String email = "test@example.com";
        String verifyCode = "123456";
        Map<String, Object> map = new HashMap<>();

        // 设置模拟行为
        expect(emailClient.sendEmailCode(email)).andReturn(verifyCode);
        session.setAttribute(eq("EMAIL_CODEtest@example.com"), anyObject(Map.class));
        replay(emailClient, session);

        // 执行测试
        RespResult result = loginController.sendEmailCode(email, map);

        // 验证结果
        assertEquals("发送成功", result.getMessage());
        assertEquals("SUCCESS", result.getCode());
        verify(emailClient, session);
    }

}