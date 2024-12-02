package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Illness;



@RestController
@RequestMapping("illness")
@Tag(name = "疾病",description = "疾病相关操作")
public class IllnessController extends BaseController<Illness> {

    @Operation(summary = "获取疾病",
            description = "获取所有存在的疾病")
    @ResponseBody
    @PostMapping("all")
    public RespResult all() {
        return RespResult.success("OK", illnessService.all());
    }
}
