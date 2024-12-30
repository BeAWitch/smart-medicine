package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description 疾病种类实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("illness_kind")
public class IllnessKind {

    // 疾病种类id
    private int id;

    // 疾病种类名称
    private String name;

    // 疾病种类信息
    private String info;

    // 疾病种类创建时间
    private Date createTime;

    // 疾病种类更新时间
    private Date updateTime;

}
