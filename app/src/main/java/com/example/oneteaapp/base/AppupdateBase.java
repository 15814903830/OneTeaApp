package com.example.oneteaapp.base;

public class AppupdateBase {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"ios_version":"1.0.0","ios_up_link":"www.xxxx.com","ios_up_desc":"升级描述","ios_force_up":"true","android_version":"1.0.0","android_up_link":"www.xxxx.com","android_up_desc":"升级描述","android_force_up":"true"}
     * url :
     * wait : 3
     */

    private int code;
    private String msg;
    private DataBean data;
    private String url;
    private int wait;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public static class DataBean {
        /**
         * ios_version : 1.0.0
         * ios_up_link : www.xxxx.com
         * ios_up_desc : 升级描述
         * ios_force_up : true
         * android_version : 1.0.0
         * android_up_link : www.xxxx.com
         * android_up_desc : 升级描述
         * android_force_up : true
         */

        private String ios_version;
        private String ios_up_link;
        private String ios_up_desc;
        private String ios_force_up;
        private String android_version;
        private String android_up_link;
        private String android_up_desc;
        private String android_force_up;

        public String getIos_version() {
            return ios_version;
        }

        public void setIos_version(String ios_version) {
            this.ios_version = ios_version;
        }

        public String getIos_up_link() {
            return ios_up_link;
        }

        public void setIos_up_link(String ios_up_link) {
            this.ios_up_link = ios_up_link;
        }

        public String getIos_up_desc() {
            return ios_up_desc;
        }

        public void setIos_up_desc(String ios_up_desc) {
            this.ios_up_desc = ios_up_desc;
        }

        public String getIos_force_up() {
            return ios_force_up;
        }

        public void setIos_force_up(String ios_force_up) {
            this.ios_force_up = ios_force_up;
        }

        public String getAndroid_version() {
            return android_version;
        }

        public void setAndroid_version(String android_version) {
            this.android_version = android_version;
        }

        public String getAndroid_up_link() {
            return android_up_link;
        }

        public void setAndroid_up_link(String android_up_link) {
            this.android_up_link = android_up_link;
        }

        public String getAndroid_up_desc() {
            return android_up_desc;
        }

        public void setAndroid_up_desc(String android_up_desc) {
            this.android_up_desc = android_up_desc;
        }

        public String getAndroid_force_up() {
            return android_force_up;
        }

        public void setAndroid_force_up(String android_force_up) {
            this.android_force_up = android_force_up;
        }
    }
}
