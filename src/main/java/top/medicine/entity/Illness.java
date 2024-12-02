package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("illness")
@Schema(description = "疾病实体类")
public class Illness {

    @Schema(description = "疾病id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "疾病种类id")
    private Integer kindId;

    @Schema(description = "疾病名称")
    private String illnessName;

    @Schema(description = "疾病诱因")
    private String includeReason;

    @Schema(description = "疾病症状")
    private String illnessSymptom;

    @Schema(description = "疾病特殊症状")
    private String specialSymptom;

    @Schema(description = "疾病创建时间")
    private Date createTime;

    @Schema(description = "疾病更新时间")
    private Date updateTime;

    @TableField(exist = false)
    @Schema(description = "疾病种类")
    private IllnessKind kind;

    @TableField(exist = false)
    @Schema(description = "疾病对应的药品")
    private IllnessMedicine illnessMedicine;
}
