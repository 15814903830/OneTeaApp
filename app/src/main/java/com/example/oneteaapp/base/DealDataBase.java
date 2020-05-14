package com.example.oneteaapp.base;

import java.util.List;

public class DealDataBase {

    /**
     * code : 1
     * msg : ok success
     * data : {"id":1,"title":"溯源白茶 2019  ","cover":91,"imgs":",91","content":"<p>测试商品1<\/p>","create_time":1577123773,"update_time":1577606159,"is_del":0,"code":"BBCA123465","day_min_price":0,"day_max_price":0,"day_sell_count":0,"y_day_min_price":0,"y_day_max_price":0,"y_day_sell_count":0,"has_not_sell":0,"has_count_price":0,"new_sell_lists":[{"id":1,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":1,"qrcode_ids":"1","buy_uid":0,"status":1,"order_code":"","create_time":1577179102,"update_time":1577179102},{"id":2,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":2,"qrcode_ids":"2,3","buy_uid":0,"status":1,"order_code":"","create_time":1577179106,"update_time":1577179106},{"id":3,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":2,"qrcode_ids":"4,5","buy_uid":0,"status":1,"order_code":"1000000002","create_time":1577183139,"update_time":1577183139}],"new_buy_lists":[]}
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
         * id : 1
         * title : 溯源白茶 2019
         * cover : 91
         * imgs : ,91
         * content : <p>测试商品1</p>
         * create_time : 1577123773
         * update_time : 1577606159
         * is_del : 0
         * code : BBCA123465
         * day_min_price : 0
         * day_max_price : 0
         * day_sell_count : 0
         * y_day_min_price : 0
         * y_day_max_price : 0
         * y_day_sell_count : 0
         * has_not_sell : 0
         * has_count_price : 0
         * new_sell_lists : [{"id":1,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":1,"qrcode_ids":"1","buy_uid":0,"status":1,"order_code":"","create_time":1577179102,"update_time":1577179102},{"id":2,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":2,"qrcode_ids":"2,3","buy_uid":0,"status":1,"order_code":"","create_time":1577179106,"update_time":1577179106},{"id":3,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":2,"qrcode_ids":"4,5","buy_uid":0,"status":1,"order_code":"1000000002","create_time":1577183139,"update_time":1577183139}]
         * new_buy_lists : []
         */

        private int id;
        private String title;
        private int cover;
        private String imgs;
        private String content;
        private int create_time;
        private int update_time;
        private int is_del;
        private String code;
        private int day_min_price;
        private int day_max_price;
        private int day_sell_count;
        private int y_day_min_price;
        private int y_day_max_price;
        private int y_day_sell_count;
        private int has_not_sell;
        private int has_count_price;
        private List<NewSellListsBean> new_sell_lists;
        private List<NewSellListsBean> new_buy_lists;

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

        public int getCover() {
            return cover;
        }

        public void setCover(int cover) {
            this.cover = cover;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getDay_min_price() {
            return day_min_price;
        }

        public void setDay_min_price(int day_min_price) {
            this.day_min_price = day_min_price;
        }

        public int getDay_max_price() {
            return day_max_price;
        }

        public void setDay_max_price(int day_max_price) {
            this.day_max_price = day_max_price;
        }

        public int getDay_sell_count() {
            return day_sell_count;
        }

        public void setDay_sell_count(int day_sell_count) {
            this.day_sell_count = day_sell_count;
        }

        public int getY_day_min_price() {
            return y_day_min_price;
        }

        public void setY_day_min_price(int y_day_min_price) {
            this.y_day_min_price = y_day_min_price;
        }

        public int getY_day_max_price() {
            return y_day_max_price;
        }

        public void setY_day_max_price(int y_day_max_price) {
            this.y_day_max_price = y_day_max_price;
        }

        public int getY_day_sell_count() {
            return y_day_sell_count;
        }

        public void setY_day_sell_count(int y_day_sell_count) {
            this.y_day_sell_count = y_day_sell_count;
        }

        public int getHas_not_sell() {
            return has_not_sell;
        }

        public void setHas_not_sell(int has_not_sell) {
            this.has_not_sell = has_not_sell;
        }

        public int getHas_count_price() {
            return has_count_price;
        }

        public void setHas_count_price(int has_count_price) {
            this.has_count_price = has_count_price;
        }

        public List<NewSellListsBean> getNew_sell_lists() {
            return new_sell_lists;
        }

        public void setNew_sell_lists(List<NewSellListsBean> new_sell_lists) {
            this.new_sell_lists = new_sell_lists;
        }

        public List<NewSellListsBean> getNew_buy_lists() {
            return new_buy_lists;
        }

        public void setNew_buy_lists(List<NewSellListsBean> new_buy_lists) {
            this.new_buy_lists = new_buy_lists;
        }

        public static class NewSellListsBean {
            /**
             * id : 1
             * goods_id : 1
             * uid : 1
             * price : 100.00
             * service_charge : 0.00
             * sell_count : 1
             * qrcode_ids : 1
             * buy_uid : 0
             * status : 1
             * order_code :
             * create_time : 1577179102
             * update_time : 1577179102
             */

            private int id;
            private int goods_id;
            private int uid;
            private String price;
            private String service_charge;
            private int sell_count;
            private String qrcode_ids;
            private int buy_uid;
            private int status;
            private String order_code;
            private int create_time;
            private int update_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getService_charge() {
                return service_charge;
            }

            public void setService_charge(String service_charge) {
                this.service_charge = service_charge;
            }

            public int getSell_count() {
                return sell_count;
            }

            public void setSell_count(int sell_count) {
                this.sell_count = sell_count;
            }

            public String getQrcode_ids() {
                return qrcode_ids;
            }

            public void setQrcode_ids(String qrcode_ids) {
                this.qrcode_ids = qrcode_ids;
            }

            public int getBuy_uid() {
                return buy_uid;
            }

            public void setBuy_uid(int buy_uid) {
                this.buy_uid = buy_uid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getOrder_code() {
                return order_code;
            }

            public void setOrder_code(String order_code) {
                this.order_code = order_code;
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
