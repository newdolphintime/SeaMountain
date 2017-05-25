package com.play.zv.seamountain.api.AvjsoupApi;

/**
 * Created by hspc on 2017/5/18.
 */

public class Magnet {
    //磁力链接
    String MagnetUrl;
    //磁力大小
    String MagnetSize;
    //分享日期
    String MagnetData;
    //磁链名称
    String MagnetTitle;
    //关联番号
    String MagnetNum;
    //是否高清
    boolean isHD;
    public boolean getIsHD() {
        return isHD;
    }
    public void setIsHD(boolean isHD) {
        this.isHD = isHD;
    }
    public String getMagnetUrl() {
        return MagnetUrl;
    }
    public void setMagnetUrl(String magnetUrl) {
        MagnetUrl = magnetUrl;
    }
    public String getMagnetSize() {
        return MagnetSize;
    }
    public void setMagnetSize(String magnetSize) {
        MagnetSize = magnetSize;
    }
    public String getMagnetData() {
        return MagnetData;
    }
    public void setMagnetData(String magnetData) {
        MagnetData = magnetData;
    }
    public String getMagnetTitle() {
        return MagnetTitle;
    }
    public void setMagnetTitle(String magnetTitle) {
        MagnetTitle = magnetTitle;
    }
    public String getMagnetNum() {
        return MagnetNum;
    }
    public void setMagnetNum(String magnetNum) {
        MagnetNum = magnetNum;
    }
    @Override
    public String toString() {
        return "Magnet [MagnetUrl=" + MagnetUrl + ", MagnetSize=" + MagnetSize + ", MagnetData=" + MagnetData
                + ", MagnetTitle=" + MagnetTitle + ", MagnetNum=" + MagnetNum + "]";
    }
}
