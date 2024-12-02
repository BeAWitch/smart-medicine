package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//没找到这玩意用途，先删了
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Schema(description = "展示给前端的疾病模板")
public class Pageview implements Serializable {

    
    private int id;

    
    private Integer pageviews;


    
    private Integer illnessId;
}
