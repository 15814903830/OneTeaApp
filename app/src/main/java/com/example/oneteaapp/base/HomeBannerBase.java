package com.example.oneteaapp.base;

import java.util.List;

public class HomeBannerBase {

    /**
     * code : 1
     * msg : 请求成功！
     * data : {"count":2,"lists":[{"id":2,"title":"首页轮播2","position":"首页BANNER","status":"启用","cover":"/uploads/picture/20191229/7e1e99ae4a218e39f738f32f95358984.png","link":"","create_time":"2019-12-29 11:58:00","update_time":"2019-12-29 11:58:00"},{"id":1,"title":"首页Banner","position":"首页BANNER","status":"启用","cover":"/uploads/picture/20191229/0b834579e09cc384736c9e7fe25d4c58.png","link":"","create_time":"2019-12-29 11:57:41","update_time":"2019-12-29 11:57:41"}]}
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
         * count : 2
         * lists : [{"id":2,"title":"首页轮播2","position":"首页BANNER","status":"启用","cover":"/uploads/picture/20191229/7e1e99ae4a218e39f738f32f95358984.png","link":"","create_time":"2019-12-29 11:58:00","update_time":"2019-12-29 11:58:00"},{"id":1,"title":"首页Banner","position":"首页BANNER","status":"启用","cover":"/uploads/picture/20191229/0b834579e09cc384736c9e7fe25d4c58.png","link":"","create_time":"2019-12-29 11:57:41","update_time":"2019-12-29 11:57:41"}]
         */

        private int count;
        private List<ListsBean> lists;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * id : 2
             * title : 首页轮播2
             * position : 首页BANNER
             * status : 启用
             * cover : /uploads/picture/20191229/7e1e99ae4a218e39f738f32f95358984.png
             * link :
             * create_time : 2019-12-29 11:58:00
             * update_time : 2019-12-29 11:58:00
             */

            private int id;
            private String title;
            private String position;
            private String status;
            private String cover;
            private String link;
            private String create_time;
            private String update_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
