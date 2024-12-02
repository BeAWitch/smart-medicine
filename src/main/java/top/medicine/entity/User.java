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


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
@Schema(description = "用户实体类")
public class User {

    @Schema(description = "用户id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户账号")
    private String userAccount;

    @Schema(description = "用户姓名")
    private String userName;

    @Schema(description = "用户密码")
    private String userPwd;

    @Schema(description = "用户年龄")
    private Integer userAge;

    @Schema(description = "用户性别")
    private String userSex;

    @Schema(description = "用户邮箱")
    private String userEmail;

    @Schema(description = "用户电话")
    private String userTel;

    @Schema(description = "用户角色状态")
    private Integer roleStatus;

    @Schema(description = "用户头像")
    private String imgPath;

    @Schema(description = "用户创建时间")
    private Date createTime;

    @Schema(description = "用户更新时间")
    private Date updateTime;

}
