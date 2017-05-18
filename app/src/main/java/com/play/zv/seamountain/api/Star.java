package com.play.zv.seamountain.api;

/**
 * Created by hspc on 2017/5/18.
 */

public class Star {
    /**
     * 名字
     */
    String name ;
    /**
     * 生日
     */
    String birthday;
    /**
     * 年龄
     */
    String age;
    /**
     * 身高
     */
    String height;
    /**
     * 罩杯
     */
    String cup;
    /**
     * 胸围
     */
    String bust;
    /**
     * 腰围
     */
    String waist;
    /**
     * 臀围
     */
    String hips;
    /**
     * 出生地
     */
    String hometown;
    /**
     * 头像
     */
    String image;
    /**
     * 爱好
     */
    String hobby;
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getCup() {
        return cup;
    }
    public void setCup(String cup) {
        this.cup = cup;
    }
    /**
     * 胸围
     * @return
     */
    public String getBust() {
        return bust;
    }
    /**
     * 胸围
     * @param bust
     */
    public void setBust(String bust) {
        this.bust = bust;
    }
    /**
     * 腰围
     * @return
     */
    public String getWaist() {
        return waist;
    }
    /**
     * 腰围
     * @param waist
     */
    public void setWaist(String waist) {
        this.waist = waist;
    }
    /**
     * 臀围
     * @return
     */
    public String getHips() {
        return hips;
    }
    /**
     * 臀围
     * @param hips
     */
    public void setHips(String hips) {
        this.hips = hips;
    }
    public String getHometown() {
        return hometown;
    }
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return "Star [name=" + name + ", birthday=" + birthday + ", age=" + age + ", height=" + height + ", cup=" + cup
                + ", bust=" + bust + ", waist=" + waist + ", hips=" + hips + ", hometown=" + hometown + ", image="
                + image + ", hobby=" + hobby + "]";
    }
}
