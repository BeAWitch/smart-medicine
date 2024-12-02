package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("medicine")
@Schema(description = "药品实体类")
public class Medicine {

    @Schema(description = "药品id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Schema(description = "药品名称")
    private String medicineName;

    @Schema(description = "药品关键词,可简要描述药品的功能")
    private String keyword;

    @Schema(description = "药品功效")
    private String medicineEffect;

    @Schema(description = "药品品牌")
    private String medicineBrand;

    @Schema(description = "药品原理")
    private String interaction;

    @Schema(description = "禁忌")
    private String taboo;

    @Schema(description = "使用方法")
    private String usAge;

    @Schema(description = "药品类型,0为中药,1为西药,2为中成药")
    private Integer medicineType;

    @Schema(description = "药品图片链接")
    private String imgPath;

    @Schema(description = "药品价格")
    private BigDecimal medicinePrice;

    @Schema(description = "药品创建时间")
    private Date createTime;

    
    private Date updateTime;

}
