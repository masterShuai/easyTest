package cn.sit.edu.cs.exam.pol.web.domain.exam;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Administrator on 2015/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamItem {
    private Long id;

    private String topic="";
    private String anwser1="";
    private String anwser2="";
    private String anwser3="";
    private String anwser4="";
    private String itemCategory="";
    private String itemType="";
    private String anwser="";
    private String anwserReference="";

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

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

    public String getAnwserReference() {
        return anwserReference;
    }

    public void setAnwserReference(String anwserReference) {
        this.anwserReference = anwserReference;
    }

    public Boolean anwserCorrect(){
        return anwser!=null && anwser.equalsIgnoreCase(anwserReference);
    }
}
