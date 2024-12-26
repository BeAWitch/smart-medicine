package top.medicine.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.medicine.dto.RespResult;
import top.medicine.entity.Category;

/**
 * @description  文章类别相关操作
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<Category> {

    @PostMapping
    public RespResult add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.save(category);
        return RespResult.success();
    }

}
