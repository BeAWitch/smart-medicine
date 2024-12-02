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
@TableName("feedback")
@Schema(description = "反馈实体类")
public class Feedback {

    
    @TableId(type = IdType.AUTO)
    @Schema(description = "反馈的id")
    private Integer id;

    @Schema(description = "反馈人姓名")
    private String name;

    @Schema(description = "反馈人邮箱")
    private String email;

    private String title;

    @Schema(description = "反馈内容")
    private String content;

    
    @Schema(description = "反馈创建时间")
    private Date createTime;

    
    @Schema(description = "反馈更新时间")
    private Date updateTime;

}
