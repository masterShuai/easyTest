package cn.sit.edu.cs.exam.pol.web.domain.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/10/19.
 */
@Entity
public class SitExamItem {
    @Id
    private Long id;

    @Column(length = 500)
    private String topic="";
    @Column(length = 200)
    private String anwser1="";
    @Column(length = 200)
    private String anwser2="";
    @Column(length = 200)
    private String anwser3="";
    @Column(length = 200)
    private String anwser4="";
    @Column(length = 200)
    private String anwser="";
    @Column(length = 200)
    private String questionType="";

    private Boolean isActivity = false;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAnwser1() {
        return anwser1;
    }

    public void setAnwser1(String anwser1) {
        this.anwser1 = anwser1;
    }

    public String getAnwser2() {
        return anwser2;
    }

    public void setAnwser2(String anwser2) {
        this.anwser2 = anwser2;
    }

    public String getAnwser3() {
        return anwser3;
    }

    public void setAnwser3(String anwser3) {
        this.anwser3 = anwser3;
    }

    public String getAnwser4() {
        return anwser4;
    }

    public void setAnwser4(String anwser4) {
        this.anwser4 = anwser4;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public Boolean isActivity() {
        return isActivity;
    }

    public void setIsActivity(Boolean isActivity) {
        this.isActivity = isActivity;
    }
}
