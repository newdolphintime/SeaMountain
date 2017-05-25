package com.play.zv.seamountain.api;

import java.util.List;

/**
 * Created by hspc on 2017/5/25.
 */

public class AVInfo {
    /**
     * censored : 有碼
     * cover : https://pics.javbus.com/cover/442w_b.jpg
     * director : 梁井一
     * genres : ["高畫質","數位馬賽克","美少女","野外・露出","DMM獨家","單體作品"]
     * label : MOODYZDIVA
     * magnet : [{"isHD":false,"magnetData":"2014-07-27","magnetNum":"MIDE-065","magnetSize":"1.1GB","magnetTitle":"第一會所新片@SIS001@(MOODYZ)(MIDE-065)南の島で開放SEX_つぼみ","magnetUrl":"magnet:?xt=urn:btih:D41D8A6DF4A92109944B78B457343A024FC3755B"},{"isHD":false,"magnetData":"2014-05-02","magnetNum":"MIDE-065","magnetSize":"1.28GB","magnetTitle":"MIDE-065","magnetUrl":"magnet:?xt=urn:btih:6AE924F9F253FACD34179C5956601C32F894F306"},{"isHD":false,"magnetData":"2014-03-26","magnetNum":"MIDE-065","magnetSize":"1.19GB","magnetTitle":"MIDE-065.avi","magnetUrl":"magnet:?xt=urn:btih:0D24C335D1D07A5DCB95A096200640798897250D"},{"isHD":false,"magnetData":"2014-03-13","magnetNum":"MIDE-065","magnetSize":"1.2GB","magnetTitle":"变坏的好人@www.sexinsex.net@mide-065","magnetUrl":"magnet:?xt=urn:btih:2A2F71FC831999E83E65AA6B62459888621CF4DC"},{"isHD":false,"magnetData":"2014-03-08","magnetNum":"MIDE-065","magnetSize":"96.28MB","magnetTitle":"MIDE-065 Tsubomi JAV CENSORED","magnetUrl":"magnet:?xt=urn:btih:0AF6656D1AA86DCA852A0AA1873AD077CABAB6CC"},{"isHD":false,"magnetData":"2014-02-25","magnetNum":"MIDE-065","magnetSize":"1.19GB","magnetTitle":"MIDE-065.avi","magnetUrl":"magnet:?xt=urn:btih:5284C01061E1083B6E8F1C5AD34B75C82A3B1B87"},{"isHD":false,"magnetData":"2014-02-24","magnetNum":"MIDE-065","magnetSize":"1.19GB","magnetTitle":"MIDE-065","magnetUrl":"magnet:?xt=urn:btih:33255346A17257883A52297D2BD5729784F09860"},{"isHD":false,"magnetData":"2014-02-14","magnetNum":"MIDE-065","magnetSize":"1.28GB","magnetTitle":"0209-mide-065","magnetUrl":"magnet:?xt=urn:btih:991F48D6B91FCBD03257E328708995B3F7932461"},{"isHD":false,"magnetData":"2014-02-14","magnetNum":"MIDE-065","magnetSize":"1.19GB","magnetTitle":"MIDE-065-AVI","magnetUrl":"magnet:?xt=urn:btih:29425FF011ED53B923BE5CDCC71F92E794881C42"}]
     * num : MIDE-065
     * previews : ["https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-1.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-2.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-3.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-4.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-5.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-6.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-7.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-8.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-9.jpg","https://pics.dmm.co.jp/digital/video/mide00065/mide00065jp-10.jpg"]
     * release : 2014-02-13
     * runTime : 150分鐘
     * series : 南の島で開放SEX
     * stars : [{"age":"30","birthday":"1987-12-25","bust":"84cm","cup":"D","height":"160cm","hips":"85cm","hometown":"読書、ショッピング、ファンシーグッズ収集、犬の散歩","image":"https://pics.javbus.com/actress/1fw_a.jpg","name":"つぼみ","waist":"58cm"}]
     * studio : ムーディーズ
     * title : 南の島で開放SEX
     */

    private String censored;
    private String cover;
    private String director;
    private String label;
    private String num;
    private String release;
    private String runTime;
    private String series;
    private String studio;
    private String title;
    private List<String> genres;
    private List<MagnetBean> magnet;
    private List<String> previews;
    private List<StarsBean> stars;

    public String getCensored() {
        return censored;
    }

    public void setCensored(String censored) {
        this.censored = censored;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<MagnetBean> getMagnet() {
        return magnet;
    }

    public void setMagnet(List<MagnetBean> magnet) {
        this.magnet = magnet;
    }

    public List<String> getPreviews() {
        return previews;
    }

    public void setPreviews(List<String> previews) {
        this.previews = previews;
    }

    public List<StarsBean> getStars() {
        return stars;
    }

    public void setStars(List<StarsBean> stars) {
        this.stars = stars;
    }

    public static class MagnetBean {
        /**
         * isHD : false
         * magnetData : 2014-07-27
         * magnetNum : MIDE-065
         * magnetSize : 1.1GB
         * magnetTitle : 第一會所新片@SIS001@(MOODYZ)(MIDE-065)南の島で開放SEX_つぼみ
         * magnetUrl : magnet:?xt=urn:btih:D41D8A6DF4A92109944B78B457343A024FC3755B
         */

        private boolean isHD;
        private String magnetData;
        private String magnetNum;
        private String magnetSize;
        private String magnetTitle;
        private String magnetUrl;

        public boolean isIsHD() {
            return isHD;
        }

        public void setIsHD(boolean isHD) {
            this.isHD = isHD;
        }

        public String getMagnetData() {
            return magnetData;
        }

        public void setMagnetData(String magnetData) {
            this.magnetData = magnetData;
        }

        public String getMagnetNum() {
            return magnetNum;
        }

        public void setMagnetNum(String magnetNum) {
            this.magnetNum = magnetNum;
        }

        public String getMagnetSize() {
            return magnetSize;
        }

        public void setMagnetSize(String magnetSize) {
            this.magnetSize = magnetSize;
        }

        public String getMagnetTitle() {
            return magnetTitle;
        }

        public void setMagnetTitle(String magnetTitle) {
            this.magnetTitle = magnetTitle;
        }

        public String getMagnetUrl() {
            return magnetUrl;
        }

        public void setMagnetUrl(String magnetUrl) {
            this.magnetUrl = magnetUrl;
        }
    }

    public static class StarsBean {
        /**
         * age : 30
         * birthday : 1987-12-25
         * bust : 84cm
         * cup : D
         * height : 160cm
         * hips : 85cm
         * hometown : 読書、ショッピング、ファンシーグッズ収集、犬の散歩
         * image : https://pics.javbus.com/actress/1fw_a.jpg
         * name : つぼみ
         * waist : 58cm
         */

        private String age;
        private String birthday;
        private String bust;
        private String cup;
        private String height;
        private String hips;
        private String hometown;
        private String image;
        private String name;
        private String waist;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBust() {
            return bust;
        }

        public void setBust(String bust) {
            this.bust = bust;
        }

        public String getCup() {
            return cup;
        }

        public void setCup(String cup) {
            this.cup = cup;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getHips() {
            return hips;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWaist() {
            return waist;
        }

        public void setWaist(String waist) {
            this.waist = waist;
        }
    }
}
