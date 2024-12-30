package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.dto.RespResult;
import top.medicine.entity.Video;

/**
 * @description  视频相关操作
 */
@RestController
@RequestMapping("video")
public class VideoController extends BaseController<Video> {

    /**
     * 获取视频列表
     */
    @PostMapping("all")
    public Object all() {
        return RespResult.success("OK",videoService.all());
    }
}
