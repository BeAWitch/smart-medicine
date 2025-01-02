package top.medicine.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MapController {
    @Value("${map-key.key}")
    private String MapAPIkey;

    @GetMapping("/medicineNearBy")
    public String medicineNearBy(@RequestParam("page_index") int page,
                                 @RequestParam("pos") String pos) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request r = new Request.Builder()
                .url("https://h5gw.map.qq.com/ws/place/v1/suggestion/?location=" + pos + "&keyword=%E5%8C%BB%E9%99%A2&key=OB4BZ-D4W3U&apptag=lbsplace_sug&page_size=20&page_index="+page)
                .method("GET", null)
                .build();

        return client.newCall(r).execute().body().string();
    }

}
