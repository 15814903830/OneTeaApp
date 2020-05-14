package com.example.oneteaapp.base;

import java.util.List;

public class ClassifyBase {

    /**
     * code : 1
     * msg : ok
     * data : [{"id":14,"pid":0,"title":"白牡丹","create_time":1577603455,"update_time":1577603857,"business_id":0,"cover":"/uploads/picture/20191229/a24661844bcfbff6c4a4344012c57ac0.png","desc":"","sort":4,"name":"白牡丹"},{"id":15,"pid":0,"title":"寿眉","create_time":1577603470,"update_time":1577603854,"business_id":0,"cover":"/uploads/picture/20191229/124de5b0ebd8cc29c6bb863c620ce1b8.png","desc":"","sort":3,"name":"寿眉"},{"id":1,"pid":0,"title":"白毫银针","create_time":1576307797,"update_time":1577603852,"business_id":0,"cover":"/uploads/picture/20191229/7f29bc5f818424b1fe362081a2a7bab6.png","desc":" 白毫银针","sort":2,"name":"白毫银针"},{"id":3,"pid":0,"title":"溯源白茶","create_time":1575623519,"update_time":1577603847,"business_id":1,"cover":"/uploads/picture/20191229/04b928ce328d714a41d24a59401d81aa.png","desc":"溯源白茶","sort":1,"name":"溯源白茶"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 14
         * pid : 0
         * title : 白牡丹
         * create_time : 1577603455
         * update_time : 1577603857
         * business_id : 0
         * cover : /uploads/picture/20191229/a24661844bcfbff6c4a4344012c57ac0.png
         * desc :
         * sort : 4
         * name : 白牡丹
         */

        private int id;
        private int pid;
        private String title;
        private int create_time;
        private int update_time;
        private int business_id;
        private String cover;
        private String desc;
        private int sort;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public int getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(int business_id) {
            this.business_id = business_id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
