package top.medicine.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.medicine.dto.RespResult;
import top.medicine.entity.Article;

/**
 * @description  文章相关操作
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article>{

    @PostMapping("/add")
    public RespResult add(@Validated(Article.Add.class) Article article){
        return RespResult.success("保存成功", articleService.save(article));
    }

    @PostMapping("/update")
    public RespResult update(@Validated(Article.Update.class) Article article) {
        return RespResult.success("保存成功", articleService.save(article));
    }

    @ExceptionHandler(BindException.class)
    public RespResult handleValidationException(BindException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return RespResult.fail("验证失败：" + errorMessage);
    }
}
