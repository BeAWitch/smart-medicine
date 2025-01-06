package top.medicine.controller;

import org.springframework.validation.BindException;
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

    @PostMapping("/add")
    public RespResult add(@Validated(Category.Add.class) Category category){
        return RespResult.success("保存成功", categoryService.save(category));
    }

    @PostMapping("update")
    public RespResult update(@Validated(Category.Update.class) Category category){
        return RespResult.success("保存成功", categoryService.save(category));
    }

    @ExceptionHandler(BindException.class)
    public RespResult handleValidationException(BindException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        return RespResult.fail("验证失败：" + errorMessage);
    }
}
