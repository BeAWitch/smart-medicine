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
 * @description 反馈实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("feedback")
public class Feedback {

    // 反馈的id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 反馈人姓名
    private String name;

    // 反馈人邮箱
    private String email;

    // 反馈标题
    private String title;

    // 反馈内容
    private String content;

    // 反馈创建时间
    private Date createTime;

    // 反馈更新时间
    private Date updateTime;

}
