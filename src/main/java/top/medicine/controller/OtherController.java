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

    //处方识别
    @Operation(summary = "处方识别",
            description = "调用paddle实现的OCR操作",
            parameters = {
                    @Parameter(name = "file", description = "文件")
            })
    @PostMapping(value = "ocr",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String ocr(MultipartFile file) throws IOException, TencentCloudSDKException {
        OcrClient client1 = new OcrClient(new Credential("AKIDNpOAxfJoAXtHi2TvIayYAjASH2jTFvZ7","sdKtMnDViL9KsU1h8cIzW852eHuUIE8H"),"ap-beijing");
        GeneralBasicOCRRequest request = new GeneralBasicOCRRequest();
        request.setImageBase64(Base64.getEncoder().encodeToString(file.getInputStream().readAllBytes()));
        GeneralBasicOCRResponse resp = client1.GeneralBasicOCR(request);
        List<String> results = new ArrayList<>();

        for (int i = 0; i < resp.getTextDetections().length; i++) {
            results.add(resp.getTextDetections()[i].getDetectedText());
        }
//        return "{\n" +
//                "    \"doctor\": \"【功能主治】滋阴清热，利咽解毒。适用于咽部灼热，疼痛，咽干不适等。\",\n" +
//                "    \"use\": \"含服。一次1～2片，每小时一次，一日10~20片。\",\n" +
//                "    \"name\": \"复方青橄榄利咽含片\",\n" +
//                "    \"width\": 1279,\n" +
//                "    \"id\": \"B20050002\",\n" +
//                "    \"percent\": \"20%\",\n" +
//                "    \"url\": \"https://yinlin712.oss-cn-beijing.aliyuncs.com/4/a38f9e28cdf34b4599a4e76d728414f6.png\",\n" +
//                "    \"otc\": \"乙类\",\n" +
//                "    \"height\": 1706\n" +
//                "}\n";
//        String ocr = integration.ocr(file.getInputStream());
//        String url = client.upload(file, String.valueOf(loginUser.getId()));
//        byte[] imgByte = file.getInputStream().readAllBytes();
//        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imgByte));
//
//        JSONArray result = JSONObject.parseArray(ocr)
//                .getJSONObject(0)
//                .getJSONArray("data");
//
//        List<String> results = result.stream().map((v) -> ((JSONObject) v))
//                .map((v) -> v.getString("text"))
//                .collect(Collectors.toList());

        //药品名称
        String name = results.stream().filter((v) -> v.contains("说明书")).findFirst().orElse("识别失败说明书").replace("说明书", "");
        //国药准字
        String id = results.stream().filter((v) -> v.contains("国药准字")).findFirst().orElse("国药准字识别失败").split("国药准字")[1];

        //甲类/乙类/丙类
        String otc = results.stream().filter((v) -> v.contains("甲") || v.contains("乙") || v.contains("丙")).findFirst().orElse("识别失败");

        //用法用量
        String use = results.stream().filter((v) -> v.contains("一次") || v.contains("小时")).findFirst().orElse("识别失败");

        //功能主治
        String doctor = results.stream().filter((v) -> v.contains("功能主治")).findFirst().orElse("未知");

        /*
         * 1. 18周岁以下，甲类药报销30%，乙类药报销20%
         * 2. 18~25周岁，甲类药报销25%，乙类药报销20%
         * 3. 25~45周岁，甲类要报销20%，乙类药报销25%
         */

        //报销比例
        String percent = null;
        if (loginUser.getUserAge() > 25) { //25-
            if (otc.contains("甲")) {
                percent = "20%";
            }
            if (otc.contains("乙")) {
                percent = "25%";
            }
        }
        if (loginUser.getUserAge() > 18) {//18-25
            if (otc.contains("甲")) {
                percent = "25%";
            }
            if (otc.contains("乙")) {
                percent = "20%";
            }
        }

        if (loginUser.getUserAge() > 0) {
            if (otc.contains("甲")) {
                percent = "30%";
            }
            if (otc.contains("乙")) {
                percent = "20%";
            }
        }
        if (percent == null) {
            percent = "未知";
        }


        String finalPercent = percent;
        return new JSONObject() {{
            put("name", name);
            put("id", id);
            put("otc", otc);
            put("use", use);
            put("doctor", doctor);

//            put("width", image.getWidth());
//            put("height", image.getHeight());
//            put("url", url);

            put("percent", finalPercent);
        }}.toJSONString();
    }
}