package top.medicine.controller;

import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.medicine.dto.RespResult;
import top.medicine.entity.Article;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article>{

    @PostMapping
    public RespResult add(@RequestBody @Validated Article article){
        articleService.save(article);
        return RespResult.success();
    }

    @GetMapping("/detail")
    public RespResult detail(@NotNull final Integer id) {
        final Article article = articleService.get(id);
        return RespResult.success("OK", article);
    }

    @PutMapping
    public RespResult update(@RequestBody @Validated(Article.Update.class) Article article) {
        articleService.save(article);
        return RespResult.success();
    }

    @DeleteMapping
    public RespResult delete(@NotNull final Integer id) {
        articleService.delete(id);
        return RespResult.success();
    }
}
