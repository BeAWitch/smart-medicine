package top.medicine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.medicine.component.EmailClient;
import top.medicine.dto.RespResult;
import top.medicine.entity.IllnessKind;
import top.medicine.entity.User;
import top.medicine.service.*;
import top.medicine.utils.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;



public class BaseController<T> {

    @Autowired
    protected ApiService apiService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected IllnessKindService illnessKindService;
    @Autowired
    protected IllnessMedicineService illnessMedicineService;
    @Autowired
    protected IllnessService illnessService;
    @Autowired
    protected MedicalNewsService medicalNewsService;
    @Autowired
    protected MedicineService medicineService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected FeedbackService feedbackService;

    @Autowired
    protected VideoService videoService;

    @Autowired
    protected BaseService<T> service;

    @Autowired
    protected ArticleService articleService;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected EmailClient emailClient;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;
    protected List<IllnessKind> kindList;


    @Operation(summary = "保存",
            description = "保存对应实例",
            parameters = {
                    @Parameter(name = "obj", description = "保存的对象")
            })
    @ResponseBody
    @PostMapping("save")
    public RespResult save(T obj) {
        if (Assert.isEmpty(obj)) {
            return RespResult.fail("保存对象不能为空");
        }
        obj = service.save(obj);
        return RespResult.success("保存成功", obj);
    }

    @Operation(summary = "删除",
            description = "根据id删除数据库对象",
            parameters = {
                    @Parameter(name = "id", description = "对象id")
            })
    @ResponseBody
    @PostMapping("/delete")
    public RespResult delete(Integer id) {
        if (Assert.isEmpty(id)) {
            return RespResult.fail("删除ID不能为空");
        }
        if (service.delete(id) == 0) {
            T t = service.get(id);
            if (Assert.isEmpty(t)) {
                return RespResult.notFound("数据不存在");
            }
            return RespResult.fail("删除失败");
        }
        return RespResult.success("删除成功");
    }

    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
        session.setAttribute("kindList", illnessKindService.findList());
    }
}
