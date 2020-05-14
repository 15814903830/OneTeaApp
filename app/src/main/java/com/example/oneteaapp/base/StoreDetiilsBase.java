package com.example.oneteaapp.base;

import java.util.List;


//店铺详情基类
public class StoreDetiilsBase {

    /**
     * code : 1
     * msg : ok success
     * data : {"id":2,"uid":174,"cover":"/uploads/picture/20200105/422a181bb5be070dbd1851d159ed4c80.png","name":"太古汇","mobile":"15814903830","money":"123.00","create_time":1578211393,"update_time":1578211432,"imgs":["","/uploads/picture/20200105/4085826139eb541ba8312e2cac05b9cb.png","/uploads/picture/20200105/beff71d0ff2e7c3290fd26c50b0196b0.png"],"open_time":"2019","address":"广东省广州市天河区天河南街道太古汇董事长办公室","content":"","shut_time":"2020"}
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
         * id : 2
         * uid : 174
         * cover : /uploads/picture/20200105/422a181bb5be070dbd1851d159ed4c80.png
         * name : 太古汇
         * mobile : 15814903830
         * money : 123.00
         * create_time : 1578211393
         * update_time : 1578211432
         * imgs : ["","/uploads/picture/20200105/4085826139eb541ba8312e2cac05b9cb.png","/uploads/picture/20200105/beff71d0ff2e7c3290fd26c50b0196b0.png"]
         * open_time : 2019
         * address : 广东省广州市天河区天河南街道太古汇董事长办公室
         * content :
         * shut_time : 2020
         */

        private int id;
        private int uid;
        private String cover;
        private String name;
        private String mobile;
        private String money;
        private int create_time;
        private int update_time;
        private String open_time;
        private String address;
        private String content;
        private String shut_time;
        private List<String> imgs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getShut_time() {
            return shut_time;
        }

        public void setShut_time(String shut_time) {
            this.shut_time = shut_time;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
