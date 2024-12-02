package top.medicine.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableCollection;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.GeneralBasicOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.TextDetection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.medicine.component.OssClient;
import top.medicine.entity.User;
import top.medicine.service.FlaskServiceIntegration;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service")
@Tag(name = "其他",description = "其他相关操作")
@CrossOrigin(methods = {RequestMethod.OPTIONS,RequestMethod.POST},origins = "*",maxAge = 3600)
public class OtherController extends BaseController<User> {
    @Resource
    private FlaskServiceIntegration integration;

    @Resource 
    private OssClient client;

    //中草药识别
    @Operation(summary = "中草药识别",
            description = "调用中草药识别接口",
            parameters = {
                    @Parameter(name = "file", description = "文件")
            })
    @PostMapping(value = "/ocr1",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object ocr1(MultipartFile file) throws IOException {
        return integration.grassAI(file.getInputStream());
//        return "{" +
//                "  \"code\": 200,\n" +
//                "  \"data\": [\n" +
//                "    {\n" +
//                "      \"name\": \"安息香\",\n" +
//                "      \"rate\": 0.816,\n" +
//                "      \"spell\": \"Anxixiang\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"msg\": \"OK\"\n" +
//                "}";
    }

}