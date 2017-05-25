package com.play.zv.seamountain.api.AvjsoupApi;

import java.util.List;

/**
 * Created by hspc on 2017/5/18.
 */

public class MovieInfo {
    /**
     * 有没有码
     */
    String censored;
    /**
     * 番号
     */
    String num;
    /**
     * 标题
     */
    String title;
    /**
     * 发行日期
     */
    String release;
    /**
     * 影片长度
     */
    String runTime;
    /**
     * 演员
     */
    List<Star> stars;
    /**
     * 导演
     */
    String director;
    /**
     * 制作商
     */
    String studio;
    /**
     * 发行商
     */
    String label;
    /**
     * 类别
     */
    List<String> genres;
    /**
     * 封面
     */
    String cover;
    /**
     * 预览图
     */
    List<String> Previews;
    /**
     * 系列
     */
    String series;


    /**
     * 磁力链接
     */
    List<Magnet> magnet;
    /**
     * 获取番号
     * @return
     */
    public String getNum() {
        return num;
    }
    /**
     * 设置番号
     * @param num
     */
    public void setNum(String num) {
        this.num = num;
    }
    /**
     * 获取标题
     * @return
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取发行日期
     * @return
     */
    public String getRelease() {
        return release;
    }
    /**
     * 设置发行日期
     * @param release
     */
    public void setRelease(String release) {
        this.release = release;
    }
    /**
     * 获取时长
     * @return
     */
    public String getRunTime() {
        return runTime;
    }
    /**
     * 设置时长
     * @param runTime
     */
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
    /**
     * 获取演员
     * @return
     */
    public List<Star> getStars() {
        return stars;
    }
    /**
     * 设置演员
     * @param stars
     */
    public void setStars(List<Star> stars) {
        this.stars = stars;
    }
    /**
     * 获取导演
     * @return
     */
    public String getDirector() {
        return director;
    }
    /**
     * 设置导演
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }
    /**
     * 获取制作商
     * @return
     */
    public String getStudio() {
        return studio;
    }
    /**
     * 制作商
     * @param studio
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }
    /**
     * 获取发行商
     * @return
     */
    public String getLabel() {
        return label;
    }
    /**
     * 发行商
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }
    /**
     * 获取类别
     * @return
     */
    public List<String> getGenres() {
        return genres;
    }
    /**
     * 设置类别
     * @param genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    /**
     * 获取封面
     * @return
     */
    public String getCover() {
        return cover;
    }
    /**
     * 设置封面
     * @param cover
     */
    public void setCover(String cover) {
        this.cover = cover;
    }
    /**
     * 获取预览图
     * @return
     */
    public List<String> getPreviews() {
        return Previews;
    }
    /**
     * 设置预览图
     * @param previews
     */
    public void setPreviews(List<String> previews) {
        Previews = previews;
    }
    /**
     * 获取磁力链接
     * @return
     */
    public List<Magnet> getMagnet() {
        return magnet;
    }
    /**
     * 设置磁力链接
     * @param magnet
     */
    public void setMagnet(List<Magnet> magnet) {
        this.magnet = magnet;
    }
    /**
     * 有没有码
     * @return
     */
    public String getCensored() {
        return censored;
    }
    /**
     * 有没有码
     * @param censored
     */
    public void setCensored(String censored) {
        this.censored = censored;
    }

    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }
    @Override
    public String toString() {
        return "MovieInfo [censored=" + censored + ", num=" + num + ", title=" + title + ", release=" + release
                + ", runTime=" + runTime + ", stars=" + stars + ", director=" + director + ", studio=" + studio
                + ", label=" + label + ", genres=" + genres + ", cover=" + cover + ", Previews=" + Previews
                + ", series=" + series + ", magnet=" + magnet + "]";
    }
}
