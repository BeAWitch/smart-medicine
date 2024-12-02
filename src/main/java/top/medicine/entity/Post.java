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
@TableName("PostList")
@Schema(description = "帖子实体类")
public class Post {
    @TableId(type = IdType.AUTO)
    @Schema(description = "帖子id")
    private Integer id;

    @Schema(description = "帖子创建者id")
    private Integer userId;
    @Schema(description = "帖子所属频道id")
    private Integer channelId;
    @Schema(description = "帖子标题")
    private String title;
    @Schema(description = "帖子内容")
    private String description;
    @Schema(description = "帖子创建时间")
    private Date createTime;
    @Schema(description = "帖子更新时间")
    private Date updateTime;
}