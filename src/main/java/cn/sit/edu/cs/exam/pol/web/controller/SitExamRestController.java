package cn.sit.edu.cs.exam.pol.web.controller;

import cn.sit.edu.cs.exam.pol.web.protocol.ResponseContent;
import cn.sit.edu.cs.exam.pol.web.protocol.SitExamResData;
import cn.sit.edu.cs.exam.pol.web.service.SitExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2015/10/21.
 */
@RestController
@RequestMapping(value = "/sitExam")
public class SitExamRestController {

    @Autowired
    private SitExamService examService;

    /**
     * 获取所有题目的试卷
     * /v  POST
     * @return 返回一个ExamPaper对象
     *
     * */
    //@RequestMapping(value = "/a", method = RequestMethod.POST)
    //public ResponseContent getPaperAll(@RequestBody SitExamValidateReqData paras) {
    @RequestMapping(value = "/a")
    public ResponseContent getPaperAll() {
        //初始化部分信息
        System.out.println("getPaperForValitation-----------");
        long startTime=System.currentTimeMillis();   //获取开始时间
        SitExamResData ep =examService.getAllQuestionExamPaper();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        return ResponseContent.makeSuccessResponse(ep);
    }

    /**
     * 获取单选题试卷
     * @return 返回一个ExamPaper对象
     */
    @RequestMapping(value = "/single")
    public ResponseContent getSingleAnswerQuestion(){
        //初始化部分信息
        System.out.println("getPaperForValitation-----------");
        long startTime=System.currentTimeMillis();   //获取开始时间
        SitExamResData ep =examService.getSingleAnswerExamPaper();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        return ResponseContent.makeSuccessResponse(ep);
    }


    /**
     * 获取判断题试卷
     * @return 返回一个ExamPaper对象
     */
    @RequestMapping(value = "/judge")
     public ResponseContent getJudgeQuestion(){
        //初始化部分信息
        System.out.println("getPaperForValitation-----------");
        long startTime=System.currentTimeMillis();   //获取开始时间
        SitExamResData ep =examService.getJudgeExamPaper();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        return ResponseContent.makeSuccessResponse(ep);
    }

    /**
     * 获取多选题试卷
     * @return 返回一个ExamPaper对象
     */
    @RequestMapping(value = "/multiple")
    public ResponseContent getMultipleAnswerQuestion(){
        //初始化部分信息
        System.out.println("getPaperForValitation-----------");
        long startTime=System.currentTimeMillis();   //获取开始时间
        SitExamResData ep =examService.getMultipleAnswerExamPaper();
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        return ResponseContent.makeSuccessResponse(ep);
    }
}
