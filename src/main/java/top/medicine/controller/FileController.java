package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.medicine.component.OssClient;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;
import top.medicine.utils.Assert;

import java.io.IOException;


@RestController
@RequestMapping("/file")
@Tag(name = "文件",description = "文件相关操作")
public class FileController extends BaseController<User> {

    @Autowired
    private OssClient ossClient;


    @Operation(summary = "上传",
            description = "将文件上传到阿里云OSS",
            parameters = {
                    @Parameter(name = "file", description = "将要上传的文件")
            })
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RespResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = ossClient.upload(file, String.valueOf(loginUser.getId()));
        if (Assert.isEmpty(url)) {
            return RespResult.fail("上传失败", url);
        }
        return RespResult.success("上传成功", url);
    }
}
