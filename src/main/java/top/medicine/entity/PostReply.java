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
@TableName("PostReply")
@Schema(description = "帖子回复实体类")
public class PostReply {
    @TableId(type = IdType.AUTO)
    @Schema(description = "回复id")
    private Integer id;

    @Schema(description = "回复创建者id")
    private Integer userId;
    @Schema(description = "回复所属帖子id")
    private Integer postId;
    @Schema(description = "回复内容")
    private String message;
    @Schema(description = "回复更新时间")
    private Date createTime;
}
