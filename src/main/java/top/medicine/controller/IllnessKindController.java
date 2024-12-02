package top.medicine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.entity.IllnessKind;


@RestController
@RequestMapping("illness_kind")
@Tag(name = "疾病种类",description = "疾病种类相关操作")
public class IllnessKindController extends BaseController<IllnessKind> {

}
