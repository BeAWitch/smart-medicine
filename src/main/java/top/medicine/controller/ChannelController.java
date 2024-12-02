package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Channel;
import top.medicine.entity.ChannelHistory;

@RestController
@RequestMapping("channel")
@Tag(name = "频道", description = "管理频道(聊天室模式)")
public class ChannelController extends BaseController<Channel> {

    @Operation(summary = "获取频道列表",
            description = "根据频道id获取消息历史(聊天室模式)")
    @PostMapping("list")
    public Object list() {
        return RespResult.success("OK",channelService.all());
    }

    @Operation(summary = "获取消息列表",
            description = "根据频道id获取消息",
            parameters = {
                    @Parameter(name = "id", description = "频道id")
            })
    @PostMapping("history")
    public RespResult history(@RequestParam("id") Integer channelId) {
        return RespResult.success("OK!",channelHistoryService.query(ChannelHistory.builder().channelId(channelId).build()));
    }
}
