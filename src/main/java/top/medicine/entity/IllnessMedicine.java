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
@TableName("illness_medicine")
@Schema(description = "根据疾病查询药品的关联实体类")
public class IllnessMedicine {

    @Schema(description = "一条记录的id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "疾病id")
    private Integer illnessId;

    @Schema(description = "药品id")
    private Integer medicineId;

    @Schema(description = "药品创建时间")
    private Date createTime;

    @Schema(description = "药品更新时间")
    private Date updateTime;

}
