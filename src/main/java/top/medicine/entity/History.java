package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("history")
@Schema(description = "用户的搜索记录")
public class History {


    @Schema(description = "搜索记录的id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户的id")
    private Integer userId;

    @Schema(description = "搜索记录的操作类型: 2为疾病，3为药品")
    private Integer operateType;

    @Schema(description = "搜索记录的内容")
    private String keyword;

    @Schema(description = "搜索记录的创建时间")
    private Date createTime;

    @Schema(description = "搜索记录的更新时间")
    private Date updateTime;

}
