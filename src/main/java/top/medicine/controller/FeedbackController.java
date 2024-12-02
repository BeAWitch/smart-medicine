package top.medicine.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.medicine.entity.Feedback;


@RestController
@RequestMapping(value = "feedback")
@Tag(name = "反馈",description = "用户反馈相关")
public class FeedbackController extends BaseController<Feedback> {

}
