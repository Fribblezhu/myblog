package com.zwj.blog.domain;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name= "t_user")
public class User {

    @Id
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @GeneratedValue(generator = "uuidGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "user_name")
    private String userName; //用户名

    @Column(name = "password")
    private String password;    //密码

    @Column(name = "is_expired")
    private boolean expired; //过期

    @Column(name = "is_locked")
    private boolean locked; //被锁

    @Column(name = "is_credential")
    private boolean credential;  //凭证

    @Column(name = "is_enabled")
    private boolean enabled;  //禁用

    @Column(name = "create_time")
    private Date createTime; //创建时间

    @Column(name = "password_update_time")
    private Date lastPasswordResetDate;

    @Column(name = "avatar")
    private String avatar; //图像src

    @Column(name = "nick_name")
    private String nickName; //昵称

    @Column(name = "phone")
    private String phone; //电话号码

    @Column(name = "email")
    private String email; //邮箱

    @Column(name = "signature")
    private String signature; //个性签名

    @Column(name = "address")
    private String address; //地址

    @Column(name = "about")
    private String about; //简介

    @Column(name ="telegram")
    private String telegram; //tg

    @Column(name = "wechart")
    private String wechart;  //微信

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isCredential() {
        return credential;
    }

    public void setCredential(boolean credential) {
        this.credential = credential;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
