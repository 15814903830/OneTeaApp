package com.example.oneteaapp.base;

import java.util.List;

public class SellOrderBase {

    /**
     * code : 1
     * msg : ok success
     * data : [{"id":1,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":1,"qrcode_ids":"1","buy_uid":0,"status":1,"order_code":"","create_time":1577179102,"update_time":1577179102,"goods_info":{"id":1,"title":"溯源白茶 2019  ","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":",91","content":"<p>测试商品1<\/p>","init_price":null,"create_time":1577123773,"update_time":1577606159,"is_del":0,"code":"BBCA123465"}},{"id":2,"goods_id":1,"uid":1,"price":"100.00","service_charge":"0.00","sell_count":2,"qrcode_ids":"2,3","buy_uid":0,"status":1,"order_code":"","create_time":1577179106,"update_time":1577179106,"goods_info":{"id":1,"title":"溯源白茶 2019  ","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":",91","content":"<p>测试商品1<\/p>","init_price":null,"create_time":1577123773,"update_time":1577606159,"is_del":0,"code":"BBCA123465"}},{"id":3,"goods_id":1,"uid":1,"price":"100.00","service_charge":"2.00","sell_count":2,"qrcode_ids":"4,5","buy_uid":175,"status":0,"order_code":"1000000002","create_time":1577183139,"update_time":1578056477,"goods_info":{"id":1,"title":"溯源白茶 2019  ","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":",91","content":"<p>测试商品1<\/p>","init_price":null,"create_time":1577123773,"update_time":1577606159,"is_del":0,"code":"BBCA123465"}}]
     * url :
     * wait : 3
     */

    private int code;
    private String msg;
    private String url;
    private int wait;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
         * goods_info : {"id":1,"title":"溯源白茶 2019  ","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":",91","content":"<p>测试商品1<\/p>","init_price":null,"create_time":1577123773,"update_time":1577606159,"is_del":0,"code":"BBCA123465"}
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
        private GoodsInfoBean goods_info;

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

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public static class GoodsInfoBean {
            /**
             * id : 1
             * title : 溯源白茶 2019
             * cover : /uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg
             * imgs : ,91
             * content : <p>测试商品1</p>
             * init_price : null
             * create_time : 1577123773
             * update_time : 1577606159
             * is_del : 0
             * code : BBCA123465
             */

            private int id;
            private String title;
            private String cover;
            private String imgs;
            private String content;
            private Object init_price;
            private int create_time;
            private int update_time;
            private int is_del;
            private String code;

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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
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

            public Object getInit_price() {
                return init_price;
            }

            public void setInit_price(Object init_price) {
                this.init_price = init_price;
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
        }
    }
}
