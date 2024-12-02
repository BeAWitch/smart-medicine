package top.medicine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
/*
* OCR模块Flask接口
*/

@Service
public class FlaskServiceIntegration {

    private final RestTemplate restTemplate;

    @Value("${flask.ocr.baseurl}")
    private String flaskServiceBaseUrl;

    @Value("${flask.grass.baseurl}")
    private String grassBaseUrl;

    @Autowired
    public FlaskServiceIntegration(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String grassAI(InputStream stream) {
        String flaskEndpoint = "http://" + grassBaseUrl + "/ai/getResultForImage";

        // 创建LinkedMultiValueMap来存储MultipartFile
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        try {
            ByteArrayResource resource = new ByteArrayResource(stream.readAllBytes()) {
                @Override
                public String getFilename() {
                    return "file.png";
                }
            };
            map.add("file", resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用HttpEntity包装请求参数
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // 发送请求并获取响应
        String response = null;

        //多次call会有：could not execute a primitive
        do {
            try {
                response = restTemplate.postForEntity(flaskEndpoint, requestEntity, String.class).getBody();
            } catch (Exception ignored) {
            }
        } while (response == null);

        return response;
    }

    // 一个例子方法，用于调用Flask的API
    public String ocr(InputStream stream) {
        String flaskEndpoint = "http://" + flaskServiceBaseUrl + "/ocr";

        // 创建LinkedMultiValueMap来存储MultipartFile
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        try {
            ByteArrayResource resource = new ByteArrayResource(stream.readAllBytes()) {
                @Override
                public String getFilename() {
                    return "file.png";
                }
            };
            map.add("file", resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用HttpEntity包装请求参数
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        // 发送请求并获取响应
        String response = null;

        //多次call会有：could not execute a primitive
        do {
            try {
                response = restTemplate.postForEntity(flaskEndpoint, requestEntity, String.class).getBody();
            } catch (Exception ignored) {
            }
        } while (response == null);

        return response;
    }
}