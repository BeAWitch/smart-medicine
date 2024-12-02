package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;


@RestController
@RequestMapping("/message")
@Tag(name = "医生",description = "智能医生相关")
public class MessageController extends BaseController<User> {

    
    @PostMapping("/query")
    @Operation(summary = "查询医生意见",
            description = "调用大模型获取医生意见",
            parameters = {
                    @Parameter(name = "content", description = "用户输入的内容")
            })
    public RespResult query(String content) {
        String result = apiService.query(content);
        return RespResult.success(result);
    }
}
