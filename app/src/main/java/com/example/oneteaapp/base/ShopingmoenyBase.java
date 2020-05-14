package com.example.oneteaapp.base;

import java.util.List;

public class ShopingmoenyBase {

    /**
     * code : 1
     * msg : 获取订单需付金额成功！
     * data : {"price":"104.65","goods_lists":[{"order_id":0,"count":1,"goods_id":37,"spec_id":221,"price":"299.00","status":1,"create_time":1578401076,"update_time":1578401076}]}
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
         * price : 104.65
         * goods_lists : [{"order_id":0,"count":1,"goods_id":37,"spec_id":221,"price":"299.00","status":1,"create_time":1578401076,"update_time":1578401076}]
         */

        private String price;
        private List<GoodsListsBean> goods_lists;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<GoodsListsBean> getGoods_lists() {
            return goods_lists;
        }

        public void setGoods_lists(List<GoodsListsBean> goods_lists) {
            this.goods_lists = goods_lists;
        }

        public static class GoodsListsBean {
            /**
             * order_id : 0
             * count : 1
             * goods_id : 37
             * spec_id : 221
             * price : 299.00
             * status : 1
             * create_time : 1578401076
             * update_time : 1578401076
             */

            private int order_id;
            private int count;
            private int goods_id;
            private int spec_id;
            private String price;
            private int status;
            private int create_time;
            private int update_time;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getSpec_id() {
                return spec_id;
            }

            public void setSpec_id(int spec_id) {
                this.spec_id = spec_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
        }
    }
}
