package cn.sit.edu.cs.exam.pol.web.service;

import cn.sit.edu.cs.exam.pol.web.domain.exam.ExamItem;
import cn.sit.edu.cs.exam.pol.web.domain.exam.ExamPaper;
import cn.sit.edu.cs.exam.pol.web.domain.exam.SitExamItem;
import cn.sit.edu.cs.exam.pol.web.protocol.SitExamResData;
import cn.sit.edu.cs.exam.pol.web.repository.SitExamItemRepository;
import cn.sit.edu.cs.exam.pol.web.utils.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuai on 2015/12/13.
 */
@Service
@CacheConfig(cacheNames = {"singleExam","judgeExam","mutipleExam"})
public class SitExamService {
    @Autowired
    private SitExamItemRepository sitExamItemRepository;

    private List<SitExamItem> examItems = new ArrayList<>();

    @PostConstruct
    public void Init(){
        CollectionHelper<SitExamItem> helper = new CollectionHelper<>();
        examItems = helper.iterableToList(sitExamItemRepository.findAll()) ;


        System.out.println(String.format("总题库：%d", examItems.size()));
    }

    /**
     * 获取单选题
     */
    @Cacheable({"singleExam"})
    public SitExamResData getSingleAnswerExamPaper(){
        ExamPaper paper = new ExamPaper();
        //todo:生成试卷
        for (SitExamItem ei : examItems){
            if(ei.getQuestionType().equals("单选")) {
                paper.getItems().add(WrapItem(ei));
            }
        }

        SitExamResData resData=new SitExamResData();
        resData.setPaper(paper);
        return resData;
    }

    /**
     * 获取判断题
     */
    @Cacheable({"judgeExam"})
    public SitExamResData getJudgeExamPaper(){
        ExamPaper paper = new ExamPaper();
        //todo:生成试卷
        for (SitExamItem ei : examItems){
            if(ei.getQuestionType().equals("判断")) {
                if(ei.getAnwser().equals("正确")){
                    ei.setAnwser("A");
                }
                else if(ei.getAnwser().equals("错误")){
                    ei.setAnwser("B");
                }
                paper.getItems().add(WrapItem(ei));
            }
        }

        SitExamResData resData=new SitExamResData();
        resData.setPaper(paper);
        return resData;
    }


    /**
     * 获取多选题
     */
    @Cacheable({"mutipleExam"})
    public SitExamResData getMultipleAnswerExamPaper(){
        ExamPaper paper = new ExamPaper();
        //todo:生成试卷
        for (SitExamItem ei : examItems){
            if(ei.getQuestionType().equals("多选")) {
                paper.getItems().add(WrapItem(ei));
            }
        }
        SitExamResData resData=new SitExamResData();
        resData.setPaper(paper);
        return resData;
    }

    /**
     * 获取所有试题
     */
    public SitExamResData getAllQuestionExamPaper(){
        ExamPaper paper = new ExamPaper();
        //SalerExamValidation exam = new SalerExamValidation();
        //exam.setSalesmanId(salesmanId);

        //todo:生成试卷
        for (SitExamItem ei : examItems){
            paper.getItems().add(WrapItem(ei));
        }

        //exam.setExamContent(getPaperString(paper));
        //return exam;
        SitExamResData resData=new SitExamResData();
        resData.setPaper(paper);
        return resData;
    }

    /**
     * 根据数据库中的题目封装到试卷中
     */
    private ExamItem WrapItem(SitExamItem sitExamItem) {
        ExamItem item = new ExamItem();
        item.setTopic(sitExamItem.getTopic());
        item.setAnwser1(sitExamItem.getAnwser1());
        item.setAnwser2(sitExamItem.getAnwser2());
        item.setAnwser3(sitExamItem.getAnwser3());
        item.setAnwser4(sitExamItem.getAnwser4());
        item.setAnwserReference(sitExamItem.getAnwser());
        item.setId(sitExamItem.getId());
        //item.setItemCategory(sitExamItem.getItemCategory());
        item.setItemType(sitExamItem.getQuestionType());
        return item;
    }

}
