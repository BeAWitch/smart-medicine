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

/**
 * @description 药品实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("medicine")
public class Medicine {

    // 药品id
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 药品名称
    private String medicineName;

    // 药品关键词,可简要描述药品的功能
    private String keyword;

    // 药品功效
    private String medicineEffect;

    // 药品品牌
    private String medicineBrand;

    // 药品原理
    private String interaction;

    // 禁忌
    private String taboo;

    // 使用方法
    private String usAge;

    // 药品类型,0为中药,1为西药,2为中成药
    private Integer medicineType;

    // 药品图片链接
    private String imgPath;

    // 药品价格
    private BigDecimal medicinePrice;

    // 药品创建时间
    private Date createTime;

    // 药品更新时间
    private Date updateTime;

}
