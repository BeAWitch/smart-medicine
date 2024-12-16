package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;
import top.medicine.service.IdentificationService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@RestController
@RequestMapping("/service")
@Tag(name = "其他",description = "其他相关操作")
@CrossOrigin(methods = {RequestMethod.OPTIONS,RequestMethod.POST},origins = "*",maxAge = 3600)
public class OtherController extends BaseController<User> {
    @Autowired
    private IdentificationService identificationService;

    private Properties medicines;

    public OtherController() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/medicine.properties");
        InputStreamReader reader = null;
        if (inputStream != null) {
            reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        }
        medicines = new Properties();
        medicines.load(reader);
    }

    //中草药识别
    @Operation(summary = "中草药识别",
            description = "调用中草药识别接口",
            parameters = {
                    @Parameter(name = "file", description = "文件")
            })
    @PostMapping(value = "/identify",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RespResult identify(MultipartFile file) throws Exception {
        String result = identificationService.identify(file);
        result = (String) medicines.get(result);
        return RespResult.success(result);
    }

}