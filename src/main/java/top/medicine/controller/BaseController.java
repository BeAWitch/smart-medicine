package top.medicine.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.medicine.component.EmailClient;
import top.medicine.dto.RespResult;
import top.medicine.entity.User;
import top.medicine.service.*;
import top.medicine.utils.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@AllArgsConstructor
public class BaseController<T> {
    @Autowired
    protected UserService userService;
    @Autowired
    protected IllnessKindService illnessKindService;
    @Autowired
    protected IllnessMedicineService illnessMedicineService;
    @Autowired
    protected IllnessService illnessService;
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
    protected EmailClient emailClient;
    protected HttpSession session;
    protected User loginUser;

    public BaseController() {
    }


    /**
     * 保存对应实例
     * @param obj 保存的对象
     */
    @ResponseBody
    @PostMapping("save")
    public RespResult save(T obj) {
        if (Assert.isEmpty(obj)) {
            return RespResult.fail("保存对象不能为空");
        }
        obj = service.save(obj);
        return RespResult.success("保存成功", obj);
    }

    /**
     * 根据id删除数据库对象
     * @param id 对象id
     */
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

    /**
     * 数据准备
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
    }
}
