package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Medicine;
import top.medicine.entity.User;
import top.medicine.entity.UserStatis;
import top.medicine.utils.Assert;


@RestController
@RequestMapping(value = "user")
@Tag(name = "用户",description = "用户相关操作")
public class UserController extends BaseController<User> {

    @Operation(summary = "修改用户简介",
            description = "设置简介",
            parameters = {
                    @Parameter(name = "user", description = "用户对象"),
            })
    @PostMapping("/saveProfile")
    public RespResult saveProfile(User user) {
        if (Assert.isEmpty(user)) {
            return RespResult.fail("保存对象不能为空");
        }
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("保存成功");
    }

    @PostMapping("/statis")
    public RespResult getDataStatis() {
        val list = userService.all();
        val sex = new UserStatis.SexStatis();

        for (User user : list) {
            if (user.getUserAge() == null) {
                continue;
            }
            if (user.getUserAge() <= 18) {
                sex.l18 += 1;
                continue;
            }
            if (user.getUserAge() <= 30) {
                sex.l30 += 1;
                continue;
            }
            if (user.getUserAge() <= 48) {
                sex.l48 += 1;
                continue;
            }
            if (user.getUserAge() <= 65) {
                sex.l65 += 1;
                continue;
            }
            if (user.getUserAge() <= 80) {
                sex.l80 += 1;
                continue;
            }
            if (user.getUserAge() <= 95) {
                sex.l95 += 1;
                continue;
            }
            sex.m95 += 1;
        }

        val medicine = new UserStatis.MedicineStatis();

        for (Medicine medicine1 : medicineService.all()) {
//            0代表西药，1中药，2中成药
            switch (medicine1.getMedicineType()) {
                case 1 -> {
                    medicine.a += 1;
                }
                case 2 -> {
                    medicine.b += 1;
                }
                case 3 -> {
                    medicine.c += 1;
                }
            }
        }

        return RespResult.success("OK",UserStatis.builder()
                .sex(sex)
                .medicine(medicine)
                .build());
    }

    @Operation(summary = "修改密码",
            description = "修改密码",
            parameters = {
                    @Parameter(name = "oldPass", description = "旧密码"),
                    @Parameter(name = "newPass", description = "新密码"),
            })
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
    @Operation(summary = "详情",
            description = "获取用户详情",
            parameters = {
                    @Parameter(name = "id", description = "用户id(不填默认为自己)")
            })
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
