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

/**
 * @description 疾病实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("illness")
public class Illness {

    // 疾病id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 疾病种类id
    private Integer kindId;

    // 疾病名称
    private String illnessName;

    // 疾病诱因
    private String includeReason;

    // 疾病症状
    private String illnessSymptom;

    // 疾病特殊症状
    private String specialSymptom;

    // 疾病创建时间
    private Date createTime;

    // 疾病更新时间
    private Date updateTime;

    // 疾病种类
    @TableField(exist = false)
    private IllnessKind kind;

    // 疾病对应的药品
    @TableField(exist = false)
    private IllnessMedicine illnessMedicine;
}
