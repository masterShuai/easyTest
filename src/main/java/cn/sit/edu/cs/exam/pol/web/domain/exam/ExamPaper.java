package cn.sit.edu.cs.exam.pol.web.domain.exam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamPaper {

    private Integer score=0;
    private Integer correctCount=0;

    private List<ExamItem> items;


    public ExamPaper(){
        items = new ArrayList<>();
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    public List<ExamItem> getItems() {
        return items;
    }

    public void setItems(List<ExamItem> items) {
        this.items = items;
    }

    public String commitAnwser(Long itemId, String anwser) {
        for(ExamItem item : items){
            if(item.getId().equals(itemId)){
                item.setAnwser(anwser);
                return "success";
            }
        }
        return "fail";
    }

    public boolean checkComplete() {
        int count = 0;
        for(ExamItem item : items){
            if(item.anwserCorrect()){
                count++;
            }
        }
        setCorrectCount(count);
        return getScore().equals(items.size());
    }
}
