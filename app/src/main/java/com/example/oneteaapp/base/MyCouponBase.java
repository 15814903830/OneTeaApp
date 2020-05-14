package com.example.oneteaapp.base;

import java.util.List;

public class MyCouponBase {


    /**
     * code : 1
     * msg : 获取成功！
     * data : {"lists":[{"id":18,"tpl_id":1,"uid":174,"title":"无限制","price":"100.00","type":0,"limit_price":"0.00","limit_goods_ids":null,"status":1,"create_time":1578226210,"update_time":1578226210,"limit_end_time":1578312610}]}
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
        private List<ListsBean> lists;

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * id : 18
             * tpl_id : 1
             * uid : 174
             * title : 无限制
             * price : 100.00
             * type : 0
             * limit_price : 0.00
             * limit_goods_ids : null
             * status : 1
             * create_time : 1578226210
             * update_time : 1578226210
             * limit_end_time : 1578312610
             */

            private int id;
            private int tpl_id;
            private int uid;
            private String title;
            private String price;
            private int type;
            private String limit_price;
            private Object limit_goods_ids;
            private int status;
            private int create_time;
            private int update_time;
            private int limit_end_time;

            public boolean isXuanzhe() {
                return xuanzhe;
            }

            public void setXuanzhe(boolean xuanzhe) {
                this.xuanzhe = xuanzhe;
            }

            private boolean xuanzhe;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTpl_id() {
                return tpl_id;
            }

            public void setTpl_id(int tpl_id) {
                this.tpl_id = tpl_id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getLimit_price() {
                return limit_price;
            }

            public void setLimit_price(String limit_price) {
                this.limit_price = limit_price;
            }

            public Object getLimit_goods_ids() {
                return limit_goods_ids;
            }

            public void setLimit_goods_ids(Object limit_goods_ids) {
                this.limit_goods_ids = limit_goods_ids;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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

            public int getLimit_end_time() {
                return limit_end_time;
            }

            public void setLimit_end_time(int limit_end_time) {
                this.limit_end_time = limit_end_time;
            }
        }
    }
}
