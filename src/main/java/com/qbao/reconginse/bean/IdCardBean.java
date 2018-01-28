package com.qbao.reconginse.bean;

import java.io.Serializable;

/**
 *   保存用户身份证识别基本信息
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class IdCardBean implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户身份证
     */
    private String userIdCard;
    /**
     * 用户性别
     */
    private String sexType;
    /**
     * 用户种族
     */
    private String race;
    
    /**
     * 用户出生日期
     */
    private String brithDate;
    
    /**
     * 用户地址
     */
    private String address;
    
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserIdCard() {
        return userIdCard;
    }
    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }
    public String getSexType() {
        return sexType;
    }
    public IdCardBean(String userName, String userIdCard, String sexType, String race, String brithDate, String address) {
        super();
        this.userName = userName;
        this.userIdCard = userIdCard;
        this.sexType = sexType;
        this.race = race;
        this.brithDate = brithDate;
        this.address = address;
    }
    public void setSexType(String sexType) {
        this.sexType = sexType;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getBrithDate() {
        return brithDate;
    }
    public void setBrithDate(String brithDate) {
        this.brithDate = brithDate;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
