package top.medicine.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.entity.History;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;



@RestController
@RequestMapping("history")
@Tag(name = "历史",description = "获取搜索历史相关数据")
public class HistoryController extends BaseController<History> {

    @Operation(summary = "获取历史数据",
            description = "获取历史数据的统计量")
    @PostMapping("/data")
    public Object rank() {

        List<HistoryNew> historyNews = historyService.all().stream()
                .map((v) -> new HistoryNew(
                        v.getId(),
                        v.getUserId(),
                        v.getOperateType(),
                        v.getKeyword(),
                        v.getCreateTime(),
                        v.getUpdateTime()
                ))
                .collect(Collectors.toList());

        Set<LocalDate> a = historyNews.stream().map((v) -> v.createTime0).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        Set<String> keys = historyNews.stream().map(History::getKeyword).collect(Collectors.toSet());

        JSONArray x = new JSONArray();
        for (String key : keys) {
            //特定关键词的全部搜索结果
            List<HistoryNew> keys0 = historyNews.stream().filter(v->v.getKeyword().equals(key)).collect(Collectors.toList());

            List<Long> o = new ArrayList<>();
            for (LocalDate localDate : a) {
                o.add(keys0.stream().filter((v)->v.getCreateTime0().equals(localDate)).count());
            }

            x.add(new JSONObject() {{
                put("text",key);
                put("point",o);
            }});
        }

        return new JSONObject() {{
            put("keys", a);
            put("data", x);
        }};
    }
    @Getter
    @AllArgsConstructor
    public static class HistoryNew extends History {
        private LocalDate createTime0;

        public HistoryNew(Integer id, Integer userId, Integer operateType, String keyword, Date date, Date updateTime) {
            super(id,
                    userId,
                    operateType,
                    keyword.split(",")[0],
                    date,
                    updateTime);
            this.createTime0 = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault()).toLocalDate();
        }
    }
}
