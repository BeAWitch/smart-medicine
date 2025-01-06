package top.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

@Data
public class Category {
    @NotNull(groups = Update.class)
    @TableId(type = IdType.AUTO)
    private Integer id;//主键ID
    @NotEmpty
    private String categoryName;//分类名称
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;//更新时间

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }

}