package com.play.zv.seamountain.api.GrilApi;

import java.util.List;

/**
 * Created by Zv on 2016/11/12.
 */

public class GrilInfo {

    @Override
    public String toString() {
        return "GrilInfo{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    /**
     * error : false
     * results : [{"_id":"58250c1d421aa90e799ec2bf","createdAt":"2016-11-11T08:09:01.465Z","desc":"11-11","publishedAt":"2016-11-11T12:03:10.196Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f9nuk0nvrdj20u011haci.jpg","used":true,"who":"daimajia"},{"_id":"5823bc03421aa91369f95a1d","createdAt":"2016-11-10T08:14:59.395Z","desc":"11-10","publishedAt":"2016-11-10T11:40:42.4Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9mp3xhjdhj20u00u0ta9.jpg","used":true,"who":"daimajia"},{"_id":"58228280421aa90e6f21b4e2","createdAt":"2016-11-09T09:57:20.445Z","desc":"11-9","publishedAt":"2016-11-09T11:40:48.268Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1f9lmfwy2nij20u00u076w.jpg","used":true,"who":"喵~"},{"_id":"582147ef421aa90e799ec297","createdAt":"2016-11-08T11:35:11.5Z","desc":"11-8","publishedAt":"2016-11-08T11:51:41.578Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1f9kjnm8uo1j20u00u0q5q.jpg","used":true,"who":"daimajia"},{"_id":"581fc2c0421aa91369f959f9","createdAt":"2016-11-07T07:54:40.74Z","desc":"11-7","publishedAt":"2016-11-07T11:47:36.373Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f9j7nvnwjdj20u00k0jsl.jpg","used":true,"who":"daimajia"},{"_id":"581bd560421aa91376974628","createdAt":"2016-11-04T08:25:04.30Z","desc":"11-4","publishedAt":"2016-11-04T11:48:56.654Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9frojtu31j20u00u0go9.jpg","used":true,"who":"daimajia"},{"_id":"581a838a421aa90e799ec261","createdAt":"2016-11-03T08:23:38.560Z","desc":"11-3","publishedAt":"2016-11-03T11:48:43.342Z","source":"chrome","type":"福利","url":"http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg","used":true,"who":"daimajia"},{"_id":"58193781421aa90e6f21b49f","createdAt":"2016-11-02T08:46:57.726Z","desc":"11-2","publishedAt":"2016-11-02T11:49:08.835Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f9dh2ohx2vj20u011hn0r.jpg","used":true,"who":"daimajia"},{"_id":"5817e1fa421aa913769745fe","createdAt":"2016-11-01T08:29:46.640Z","desc":"11-1","publishedAt":"2016-11-01T11:46:01.617Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034jw1f9cayjaa96j20u011hqbs.jpg","used":true,"who":"daimajia"},{"_id":"5816871a421aa91369f959b6","createdAt":"2016-10-31T07:49:46.592Z","desc":"10-31","publishedAt":"2016-10-31T11:43:44.770Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1f9b46kpoeoj20ku0kuwhc.jpg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<GrilsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<GrilsEntity> getResults() {
        return results;
    }

    public void setResults(List<GrilsEntity> results) {
        this.results = results;
    }

    public static class GrilsEntity {
        public boolean hasFadedIn = false;
        /**
         * _id : 58250c1d421aa90e799ec2bf
         * createdAt : 2016-11-11T08:09:01.465Z
         * desc : 11-11
         * publishedAt : 2016-11-11T12:03:10.196Z
         * source : chrome
         * type : 福利
         * url : http://ww3.sinaimg.cn/large/610dc034jw1f9nuk0nvrdj20u011haci.jpg
         * used : true
         * who : daimajia
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        @Override
        public String toString() {
            return "GrilsEntity{" +
                    "url='" + url + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof GrilsEntity))
                return false;
            GrilsEntity p = (GrilsEntity) obj;
            return this.getUrl().equals(p.getUrl());

        }
    }
}
