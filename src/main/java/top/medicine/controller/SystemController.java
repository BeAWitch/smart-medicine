package top.medicine.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.medicine.constant.MedicalConstants;
import top.medicine.entity.*;
import top.medicine.utils.Assert;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 主控制器
 * @description 主要用于查询和数据准备，不涉及数据修改
 */
@Controller
public class SystemController extends BaseController<User> {


    @GetMapping("/index.html")
    public String index() {
        return "index";
    }

    @SneakyThrows
    @ResponseBody
    @GetMapping("/login")
    public void login(HttpServletResponse response) {
        if (!Assert.isEmpty(loginUser)) {
            response.sendRedirect("/");
            return;
        }
        response.sendRedirect("login/index.html?#");
    }

    @GetMapping("/videoSupport")
    public String videoSupport(Map<String, Object> map, Integer id) {
        /*if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }*/
        List<Video> v = videoService.all();

        if (id == null) {
            id = v.get(0).getId();
        } else {
            Integer finalId = id;
            id = v.stream().filter((video -> video.getId() == finalId)).findFirst().get().getId();
        }
        map.put("videos", v);
        map.put("currentVideoId", id);
        map.put("currentVideo", videoService.get(id));
        return "videoSupport";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/index.html";
    }

    @GetMapping("/all-feedback")
    public String feedback(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Feedback> feedbackList = feedbackService.all();

        map.put("feedbackList", feedbackList);
        return "all-feedback";
    }


    @GetMapping("/profile")
    public String profile(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        return "profile";
    }


    @GetMapping("findIllness")
    public String findIllness(Map<String, Object> map, Integer kind, String illnessName, Integer page) {
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;

        Map<String, Object> illness = illnessService.findIllness(kind, illnessName, page);
        if (Assert.notEmpty(kind)) {
            map.put("title", illnessKindService.get(kind).getName() + (illnessName == null ? "" : ('"' + illnessName + '"' + "的搜索结果")));
        } else {
            map.put("title", illnessName == null ? "全部" : ('"' + illnessName + '"' + "的搜索结果"));
        }
        if (loginUser != null && kind != null) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_OPERATE,
                    illnessKindService.get(kind).getId() + "," + (Assert.isEmpty(illnessName) ? "无" : illnessName));
        }
        if (loginUser != null && Assert.notEmpty(illnessName)) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_ILLNESS, illnessName);
        }
        map.putAll(illness);
        map.put("page", page);
        map.put("kind", kind);
        map.put("illnessName", illnessName);
        map.put("kindList", illnessKindService.findList());
        map.put("history", loginUser == null ? null : historyService.findList(loginUser.getId()));
        return "search-illness";
    }


    @GetMapping("findIllnessOne")
    public String findIllnessOne(Map<String, Object> map, Integer id) {
        Map<String, Object> illnessOne = illnessService.findIllnessOne(id);
        Illness illness = illnessService.get(id);
        if (loginUser != null) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_ILLNESS, illness.getIllnessName());
        }
        map.putAll(illnessOne);
        return "illness-reviews";
    }


    @GetMapping("findMedicineOne")
    public String findMedicineOne(Map<String, Object> map, Integer id) {
        Medicine medicine = medicineService.get(id);
//        historyService.insetOne(loginUser.getId(),MedicalConstants.TYPE_MEDICINE,medicine.getMedicineName());
        map.put("medicine", medicine);
        return "medicine";
    }


    @GetMapping("findMedicines")
    public String findMedicines(Map<String, Object> map, String nameValue, Integer page) {
        // 处理page
        page = ObjectUtils.isEmpty(page) ? 1 : page;
        if (loginUser != null && Assert.notEmpty(nameValue)) {
            historyService.insetOne(loginUser.getId(), MedicalConstants.TYPE_MEDICINE, nameValue);
        }
        map.putAll(medicineService.getMedicineList(nameValue, page));
        map.put("history", loginUser == null ? null : historyService.findList(loginUser.getId()));
        map.put("title", nameValue);
        return "illness";
    }

    @GetMapping("add-illness")
    public String addIllness(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        Illness illness = new Illness();
        if (Assert.notEmpty(id)) {
            illness = illnessService.get(id);
        }
        List<IllnessKind> illnessKinds = illnessKindService.all();
        map.put("illness", illness);
        map.put("kinds", illnessKinds);
        return "add-illness";
    }


    @GetMapping("add-medical")
    public String addMedical(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Illness> illnesses = illnessService.all();
        Medicine medicine = new Medicine();
        if (Assert.notEmpty(id)) {
            medicine = medicineService.get(id);
            for (Illness illness : illnesses) {
                List<IllnessMedicine> query = illnessMedicineService.query(IllnessMedicine.builder().medicineId(id).illnessId(illness.getId()).build());
                if (Assert.notEmpty(query)) {
                    illness.setIllnessMedicine(query.get(0));
                }
            }
        }
        map.put("illnesses", illnesses);
        map.put("medicine", medicine);
        return "add-medical";
    }

    @GetMapping("add-video")
    public String addVideo(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
//        List<Video> videos = videoService.all();
        Video video = new Video();
        if (Assert.notEmpty(id)) {
            video = videoService.get(id);
        }
        map.put("video", video);
        return "add-video";
    }

    @GetMapping("all-illness")
    public String allIllness(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Illness> illnesses = illnessService.all();
        for (Illness illness : illnesses) {
            illness.setKind(illnessKindService.get(illness.getKindId()));
        }
        map.put("illnesses", illnesses);
        return "all-illness";
    }


    @GetMapping("all-medical")
    public String allMedical(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Medicine> medicines = medicineService.all();
        map.put("medicines", medicines);
        return "all-medical";
    }


    @GetMapping("all-video")
    public String allVideo(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<Video> medicines = videoService.all();
        map.put("videos", medicines);
        return "all-video";
    }

    @GetMapping("all-user")
    public String allUser(Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }
        List<User> users = userService.all();
        map.put("users", users);
        return "all-user";
    }

    @GetMapping("add-user")
    public String addUser(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(loginUser)) {
            return "redirect:/index.html";
        }

        User user = new User();

        if (Assert.notEmpty(id)) {
            user = userService.get(id);
        }

        map.put("user", user);

        return "add-user";
    }

}


