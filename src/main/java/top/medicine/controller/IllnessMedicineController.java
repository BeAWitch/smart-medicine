package top.medicine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.entity.IllnessMedicine;



@RestController
@RequestMapping("illness_medicine")
@Tag(name = "疾病对应药品",description = "疾病对应药品相关操作")
public class IllnessMedicineController extends BaseController<IllnessMedicine> {

}
