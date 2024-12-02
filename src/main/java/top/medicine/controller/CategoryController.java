package top.medicine.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.medicine.dto.RespResult;
import top.medicine.entity.Category;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<Category> {

    @PostMapping
    public RespResult add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.save(category);
        return RespResult.success();
    }

    @GetMapping("/detail")
    public RespResult detail(Integer id){
        Category category = categoryService.get(id);
        return RespResult.success("",category);
    }

    @PutMapping
    public RespResult update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.save(category);
        return RespResult.success();
    }

    @DeleteMapping
    public RespResult delete(Integer id){
        Category category = categoryService.get(id);
        if (category == null){
            return RespResult.fail("文章分类不存在");
        }

        categoryService.delete(id);
        return RespResult.success();
    }
}
