package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Medicine;


/**
 * @description  药品相关操作
 */
@RestController
@RequestMapping("medicine")
public class MedicineController extends BaseController<Medicine> {

    @ResponseBody
    @Operation(summary = "获取药品列表", description = "获取药品列表")
    @PostMapping("all")
    public RespResult all() {
        return RespResult.success("OK",medicineService.all());
    }
}
