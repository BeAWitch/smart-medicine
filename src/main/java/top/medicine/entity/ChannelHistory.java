package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("ChannelHistory")
@Schema(description = "聊天室历史记录实体类")
public class ChannelHistory {
    @TableId(type = IdType.AUTO)
    @Schema(description = "消息的id")
    private Integer id;

    //频道id
    @Schema(description = "所属聊天室频道id")
    private Integer channelId;

    //用户id
    @Schema(description = "发送消息的用户id")
    private Integer userId;

    //消息
    @Schema(description = "消息内容")
    private String message;

    //创建时间
    @Schema(description = "消息创建时间")
    private LocalDateTime createTime;
}
