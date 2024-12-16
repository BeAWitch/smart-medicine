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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class IdentificationService {

    private final RestTemplate restTemplate;

    @Value("${flask.identification.baseurl}")
    private String identificationPort;

    @Autowired
    public IdentificationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String identify(MultipartFile file) {
        String flaskEndpoint = "http://" + identificationPort + "/identify";

        // 创建LinkedMultiValueMap来存储MultipartFile
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        try {
            ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
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
        return restTemplate.postForEntity(flaskEndpoint, requestEntity, String.class).getBody();
    }

}