package top.medicine.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;
import top.medicine.utils.Assert;


/**
 * @description  用户相关操作
 */
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController<User> {

    /**
     * 修改用户简介
     * @param user 用户对象
     */
    @PostMapping("/saveProfile")
    public RespResult saveProfile(User user, @RequestParam(defaultValue = "false") boolean isLoginUserUpdate) {
        if (Assert.isEmpty(user)) {
            return RespResult.fail("保存对象不能为空");
        }
        user = userService.save(user);
        if (isLoginUserUpdate) {
            session.setAttribute("loginUser", user);
        }
        return RespResult.success("保存成功");
    }

    /**
     * 修改密码
     * @param oldPass 旧密码
     * @param newPass 新密码
     */
    @PostMapping("/savePassword")
    public RespResult savePassword(String oldPass, String newPass) {
        if (!loginUser.getUserPwd().equals(oldPass)) {
            return RespResult.fail("旧密码错误");
        }
        loginUser.setUserPwd(newPass);
        loginUser = userService.save(loginUser);
        session.setAttribute("loginUser", loginUser);
        return RespResult.success("保存成功");
    }

    /**
     * 获取用户详情
     * @param id 用户id(不填默认为自己)
     */
    @PostMapping("/profile")
    public RespResult getUser(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return loginUser == null ? RespResult.fail("未登录") : RespResult.success("OK", loginUser);
        }

        User s = userService.get(id);
        s.setUserPwd(null);
        s.setRoleStatus(-1);

        return RespResult.success("OK", s);
    }
}
