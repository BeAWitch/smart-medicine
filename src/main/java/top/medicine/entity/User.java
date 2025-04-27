package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class User {

    // 用户id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 用户账号
    private String userAccount;

    // 用户姓名
    private String userName;

    // 用户密码
    private String userPwd;

    // 用户年龄
    private Integer userAge;

    // 用户性别
    private String userSex;

    // 用户邮箱
    private String userEmail;

    // 用户电话
    private String userTel;

    // 用户角色状态
    // 0 - 超级用户
    // 1 - 用户管理员
    // 2 - 信息管理员
    // 3 - 普通用户
    private Integer roleStatus;

    // 用户头像
    private String imgPath;

    // 用户创建时间
    private Date createTime;

    // 用户更新时间
    private Date updateTime;

}
