package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 视频实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("video")
public class Video {
    // 视频id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 视频标题
    private String title;

    // 视频描述
    private String description;

    // 视频链接
    private String link;
}
