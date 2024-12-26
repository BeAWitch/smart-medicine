package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Tag(name = "地图",description = "地图相关操作")
public class MapController {
    @Value("${map-key.key}")
    private String MapAPIkey;

    @Operation(summary = "附近建筑查询",
            description = "查询坐标附近的建筑",
            parameters = {
                    @Parameter(name = "page_index", description = "页数"),
                    @Parameter(name = "pos", description = "经纬度坐标")
            })
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
    @Operation(summary = "最短路径查询",
            description = "获取两个建筑物之间的路径",
            parameters = {
                    @Parameter(name = "a1", description = "第一个建筑的经度"),
                    @Parameter(name = "n1", description = "第二个建筑的纬度"),
                    @Parameter(name = "a2", description = "第一个建筑的经度"),
                    @Parameter(name = "n2", description = "第二个建筑的纬度"),
            })
    @GetMapping("/waypoint")
    public String wayPoint(
            @RequestParam("a1") String lat,
            @RequestParam("n1") String lng,

            @RequestParam("a2") String lat1,
            @RequestParam("n2") String lng1

    ) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request r = new Request.Builder()
                .url(String.format("https://apis.map.qq.com/ws/direction/v1/walking/?from=%s,%s&to=%s,%s&output=json&callback=cb&key="+MapAPIkey,lat,lng,lat1,lng1))
                .method("GET", null)
                .build();

        return client.newCall(r).execute().body().string();
//        return "{\"status\":0,\"message\":\"Success\",\"request_id\":\"c14a65490fed4b56bc8eb800df3fff3b\",\"result\":{\"routes\":[{\"mode\":\"WALKING\",\"distance\":2204,\"duration\":33,\"direction\":\"东\",\"polyline\":[41.729373,123.489508,113,622,0,0,95,196,0,0,101,-26,0,0,324,-84,0,0,125,-38,0,0,355,-105,0,0,412,-123,0,0,67,6,0,0,485,-167,0,0,167,-56,0,0,1196,-399,0,0,662,-221,0,0,492,-165,0,0,149,-50,0,0,60,-20,0,0,-7,-38,0,0,47,-11,0,0,135,-27,0,0,575,-134,0,0,78,-12,0,0,71,-14,0,0,-5,-40,0,0,-23,-314,0,0,335,-122,38,-42,56,-117,0,0,89,8,112,-14,664,-186,0,0,641,-203,0,0,408,-137,0,0,53,-17,0,0,282,-84,259,-63,0,0,363,-70,286,-36,0,0,165,-13,0,0,346,-28,0,0,262,-13,331,-1,0,0,816,11,0,0,455,14,0,0,187,174,0,0,148,150,110,154,0,0,116,174,0,0,49,245,0,0,103,526,0,0,348,1886,0,0,29,148,0,0,139,718,0,0,61,310,0,0,44,228,0,0,29,167,0,0,44,249,0,0,94,532,0,0,-112,31,0,0,-254,68,0,0,-120,33,0,0,46,284,-1,22,-20,58,-83,135,0,0,-330,138,0,0,-132,55,0,0,-704,265,-215,65,-131,28,-54,4],\"steps\":[{\"instruction\":\"从起点朝东,行进53米,过路口左转\",\"polyline_idx\":[0,3],\"road_name\":\"\",\"dir_desc\":\"东\",\"distance\":53,\"act_desc\":\"左转\",\"type\":0},{\"instruction\":\"行进169米,过路口直行进入文源街\",\"polyline_idx\":[4,27],\"road_name\":\"\",\"dir_desc\":\"北\",\"distance\":169,\"act_desc\":\"直行\",\"type\":0},{\"instruction\":\"沿文源街行进351米,直行进入文源街\",\"polyline_idx\":[28,51],\"road_name\":\"文源街\",\"dir_desc\":\"北\",\"distance\":351,\"act_desc\":\"直行\",\"type\":0},{\"instruction\":\"沿文源街行进17米,过路口左转进入地下通道\",\"polyline_idx\":[52,55],\"road_name\":\"文源街\",\"dir_desc\":\"北\",\"distance\":17,\"act_desc\":\"左转\",\"type\":0},{\"instruction\":\"经地下通道行进141米,右转\",\"polyline_idx\":[56,91],\"road_name\":\"地下通道\",\"dir_desc\":\"西\",\"distance\":141,\"act_desc\":\"右转\",\"type\":2},{\"instruction\":\"行进55米,偏右转进入朗日街\",\"polyline_idx\":[92,99],\"road_name\":\"\",\"dir_desc\":\"北\",\"distance\":55,\"act_desc\":\"偏右转\",\"type\":0},{\"instruction\":\"沿朗日街行进217米,过路口直行进入朗日街\",\"polyline_idx\":[100,115],\"road_name\":\"朗日街\",\"dir_desc\":\"北\",\"distance\":217,\"act_desc\":\"直行\",\"type\":0},{\"instruction\":\"沿朗日街行进403米,右转\",\"polyline_idx\":[116,153],\"road_name\":\"朗日街\",\"dir_desc\":\"北\",\"distance\":403,\"act_desc\":\"右转\",\"type\":0},{\"instruction\":\"行进83米,直行进入浑南中路\",\"polyline_idx\":[154,167],\"road_name\":\"\",\"dir_desc\":\"东北\",\"distance\":83,\"act_desc\":\"直行\",\"type\":0},{\"instruction\":\"沿浑南中路行进227米,过路口直行进入浑南中路\",\"polyline_idx\":[168,179],\"road_name\":\"浑南中路\",\"dir_desc\":\"东\",\"distance\":227,\"act_desc\":\"直行\",\"type\":0},{\"instruction\":\"沿浑南中路行进120米,直行进入浑南中路\",\"polyline_idx\":[180,195],\"road_name\":\"浑南中路\",\"dir_desc\":\"东\",\"distance\":120,\"act_desc\":\"直行\",\"type\":0},{\"instruction\":\"沿浑南中路行进81米,右转\",\"polyline_idx\":[196,207],\"road_name\":\"浑南中路\",\"dir_desc\":\"东\",\"distance\":81,\"act_desc\":\"右转\",\"type\":0},{\"instruction\":\"行进55米,左转\",\"polyline_idx\":[208,219],\"road_name\":\"\",\"dir_desc\":\"南\",\"distance\":55,\"act_desc\":\"左转\",\"type\":0},{\"instruction\":\"行进45米,偏右转\",\"polyline_idx\":[220,229],\"road_name\":\"\",\"dir_desc\":\"东\",\"distance\":45,\"act_desc\":\"偏右转\",\"type\":0},{\"instruction\":\"行进180米,到达终点\",\"polyline_idx\":[230,247],\"road_name\":\"\",\"dir_desc\":\"南\",\"distance\":180,\"act_desc\":\"\",\"type\":0}]}]}}";
    }
}
