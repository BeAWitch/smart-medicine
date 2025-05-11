package top.medicine.controller;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;
import top.medicine.utils.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description  用户登陆与注册
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "login")
public class LoginController extends BaseController<User> {

    /**
     * 注册
     * @param user 用户信息
     * @param code 邮箱验证码
     */
    @PostMapping("/register")
    public RespResult register(User user, String code) {
        String email = user.getUserEmail();
        if (Assert.isEmpty(email)) {
            return RespResult.fail("邮箱不能为空");
        }
        Map<String, Object> codeData = (Map<String, Object>) session.getAttribute("EMAIL_CODE" + email);
        if (codeData == null) {
            return RespResult.fail("尚未发送验证码");
        }
        String sentCode = (String) codeData.get("code");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) codeData.get("time"));
        calendar.add(Calendar.MINUTE, 5);
        if (System.currentTimeMillis() > calendar.getTime().getTime()) {
            session.removeAttribute("EMAIL_CODE" + email);
            return RespResult.fail("验证码已经超时");
        }
        if (!sentCode.equals(code)) {
            return RespResult.fail("验证码错误");
        }
        List<User> query = userService.query(User.builder().userAccount(user.getUserAccount()).build());
        if (Assert.notEmpty(query)) {
            return RespResult.fail("账户已被注册");
        }
        user.setRoleStatus(3);
        user.setImgPath("https://moti-cloud-v2.oss-cn-beijing.aliyuncs.com/Snipaste_2022-05-01_15-37-01.png");
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("注册成功", user);
    }

    /**
     * 登录
     * @param user 用户字段(只需要userAccount和userPwd)
     */
    @PostMapping("/login")
    public RespResult login(User user) {
        List<User> users = userService.query(user);
        if (Assert.notEmpty(users)) {
            session.setAttribute("loginUser", users.get(0));
            return RespResult.success("登录成功");
        }
        if (Assert.isEmpty(userService.query(User.builder().userAccount(user.getUserAccount()).build()))) {
            return RespResult.fail("账户尚未注册");
        }
        return RespResult.fail("密码错误");
    }

    /**
     * 发送邮箱验证码
     * @param email 邮箱
     */
    @PostMapping("/sendEmailCode")
    public RespResult sendEmailCode(String email, Map<String, Object> map) {
        if (StrUtil.isEmpty(email)) {
            return RespResult.fail("邮箱不可为空");
        }
        // 发送验证码
        String verifyCode = emailClient.sendEmailCode(email);
        map.put("email", email);
        map.put("code", verifyCode);
        map.put("time", new Date());
        session.setAttribute("EMAIL_CODE" + email, map);
        return RespResult.success("发送成功");
    }
}
