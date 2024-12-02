package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//    id: number
//    type: number
//    name: string
//    icon: string
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("Channel")
@Schema(description = "频道实体类")
public class Channel {
    
    @Schema(description = "频道id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    
    @Schema(description = "0为聊天室，1为贴吧")
    private Integer type;

    
    @Schema(description = "频道名字")
    private String name;

    
    @Schema(description = "频道图标链接")
    private String icon;
}
