package top.medicine.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Article {
    @NotNull(groups = {Update.class})
    private Integer id;//主键ID

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题

    @NotEmpty
    private String content;//文章内容

    @NotNull
    private Integer categoryId;//文章分类id
    private Integer createUser;//创建人ID
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }
}
