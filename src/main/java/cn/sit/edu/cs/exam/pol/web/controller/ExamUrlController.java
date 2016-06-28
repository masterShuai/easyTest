package cn.sit.edu.cs.exam.pol.web.controller;

import cn.sit.edu.cs.exam.pol.web.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/10/20.
 */
@Controller
public class ExamUrlController {

    @Autowired
    private UrlConfig urlConfig;


    /**
     * 访问主页
     * */
    @RequestMapping(value = "/")
    public String home() {
        return "exam/examindex";
    }

    /**
     * 单项选择,判断题页面
     * */
    @RequestMapping(value = "/sin",method= RequestMethod.GET)
    public String sitExamSingleAnswer(@RequestParam(value = "choose",
            required=true) String salesmanId,
                                  Model model) {
        model.addAttribute("choose",salesmanId);
        return "exam/sitSingleAnswer";
    }

    /**
     * 多项选择界面
     * */
    @RequestMapping(value = "/mul",method= RequestMethod.GET)
    public String sitExamMultipleAnswer() {return "exam/sitMultipleAnswer";}
}
