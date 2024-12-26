package top.medicine.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Illness;


/**
 * @description  疾病相关操作
 */
@RestController
@RequestMapping("illness")
public class IllnessController extends BaseController<Illness> {

    /**
     * 获取所有存在的疾病
     */
    @ResponseBody
    @PostMapping("all")
    public RespResult all() {
        return RespResult.success("OK", illnessService.all());
    }
}
