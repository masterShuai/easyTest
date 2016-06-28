package cn.sit.edu.cs.exam.pol.web.protocol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Created by Administrator on 2015/10/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamResultBody {
    public static final String TypeValidate ="v";
    public static final String TypePeriodicity ="p";

    private String salesmanId;
    private String examType;
    private String periodicity;
    private Integer score;
    private Date examTime;

    public ExamResultBody(){
        examTime = new Date();
    }

    public ExamResultBody(String salesmanId, String examType, Integer score){
        examTime = new Date();
        this.setExamType(examType);
        this.setSalesmanId(salesmanId);
        this.setScore(score);
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    @Override
    public String toString() {
        return "ExamResultBody{" +
                "salesmanId='" + salesmanId + '\'' +
                ", examType='" + examType + '\'' +
                ", periodicity='" + periodicity + '\'' +
                ", score=" + score +
                ", examTime=" + examTime +
                '}';
    }
}
