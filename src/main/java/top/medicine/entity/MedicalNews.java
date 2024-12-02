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


//没出现这玩意的用途，暂时隐藏了先
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("medical_news")
@Schema(hidden = true)
public class MedicalNews {

    
    @TableId(type = IdType.AUTO)
    private Integer id;

    
    private String newsName;

    
    private String newsKey;

    
    private String newsContent;

    
    private String imgPath;

    
    private Integer relationIllness;

    
    private Date createTime;

    
    private Date updateTime;

}
