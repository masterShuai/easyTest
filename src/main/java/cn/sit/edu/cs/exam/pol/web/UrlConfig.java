package cn.sit.edu.cs.exam.pol.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2015/7/29.
 * 与其他服务器连接的配置类
 * 具体的配置内容由系统自动从配置文件中注入
 */
@Configuration
@ConfigurationProperties(prefix = "servers")
public class UrlConfig {
    private String bzserver;
    private String smsserver;
    private String pushserver;
    private String lbsserver;

    public String getBzserver() {
        return bzserver;
    }

    public void setBzserver(String bzserver) {
        this.bzserver = bzserver;
    }

    public String getSmsserver() {
        return smsserver;
    }

    public void setSmsserver(String smsserver) {
        this.smsserver = smsserver;
    }

    public String getPushserver() {
        return pushserver;
    }

    public void setPushserver(String pushserver) {
        this.pushserver = pushserver;
    }

    public String getLbsserver() {
        return lbsserver;
    }

    public void setLbsserver(String lbsserver) {
        this.lbsserver = lbsserver;
    }

    public String getUrl(String moduleUrlBase,String moduleUrl){
        System.out.println("-------"+getBzserver());
        return String.format("%s%s%s",getBzserver(),moduleUrlBase,moduleUrl);
    }
}
