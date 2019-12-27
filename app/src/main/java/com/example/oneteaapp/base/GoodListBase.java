package com.example.oneteaapp.base;

import java.util.List;

public class GoodListBase {

    /**
     * code : 1
     * msg : 请求成功！
     * data : {"count":1,"lists":[{"id":27,"title":"测试电子大屏幕手机","cate_id":"大屏幕","brand_id":"联想","status":"上架","create_time":"2019-12-14 15:41:58","update_time":"2019-12-15 11:15:29","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":["/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg"],"business_id":"测试店铺","price":"10.00","m_price":"10.00"}]}
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
         * count : 1
         * lists : [{"id":27,"title":"测试电子大屏幕手机","cate_id":"大屏幕","brand_id":"联想","status":"上架","create_time":"2019-12-14 15:41:58","update_time":"2019-12-15 11:15:29","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":["/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg"],"business_id":"测试店铺","price":"10.00","m_price":"10.00"}]
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
             * id : 27
             * title : 测试电子大屏幕手机
             * cate_id : 大屏幕
             * brand_id : 联想
             * status : 上架
             * create_time : 2019-12-14 15:41:58
             * update_time : 2019-12-15 11:15:29
             * cover : /uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg
             * imgs : ["/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg"]
             * business_id : 测试店铺
             * price : 10.00
             * m_price : 10.00
             */

            private int id;
            private String title;
            private String cate_id;
            private String brand_id;
            private String status;
            private String create_time;
            private String update_time;
            private String cover;
            private String business_id;
            private String price;
            private String m_price;
            private List<String> imgs;

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

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getBusiness_id() {
                return business_id;
            }

            public void setBusiness_id(String business_id) {
                this.business_id = business_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getM_price() {
                return m_price;
            }

            public void setM_price(String m_price) {
                this.m_price = m_price;
            }

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }
        }
    }
}
