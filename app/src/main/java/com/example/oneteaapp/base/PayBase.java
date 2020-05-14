package com.example.oneteaapp.base;

public class PayBase {

    /**
     * code : 1
     * msg : 下单成功！
     * data : {"payStr":"app_id=2021001103611075&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%2218953531935099684246020888%22%2C%22total_amount%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22passback_params%22%3A%22http%253A%252F%252Fyhmg.58xmh.com%252Fshop%252Fnotifypay%252Frecharge.html%22%2C%22disable_pay_channels%22%3A%22creditCard%22%2C%22timeout_express%22%3A%2260m%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fyhmg.58xmh.com%2Fshop%2Fnotifypay%2Frecharge.html&sign_type=RSA2&timestamp=2019-12-28+22%3A11%3A45&version=1.0&sign=JmWzAWG6rCqs3ve9CPUs4sgIEpCdYpur1OWnjbI7ORBaM7ehoIJZ7EfKmTJOFsBga37v%2BEl4h1tZE6EAH9%2B6453Q08jCPW5XqCIFuD6Bw%2Br4GgZ1PdiTNkFqJ3B%2BiLZasPtx3Kkpqnzm5XyYwZkUukufBACohjQEKhau3nUoNhjHwpNzxZK%2F0mIo%2FoDykWTAuD3YIPu9Z3xR9I8LuZYjsCcnNIfjrtTIonlSaSPqiQsw%2FTaV1xKTmxh%2BQxHPg%2FoXEpxdooD4x6UITQUFgFfOxm6rX9wW0H32ZrlOmEC15ZGbWIwSZ%2BoWJTVMZHdHu9BewGlVxeCW30ak07gDgsx4sA%3D%3D","is_pay":false}
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
         * payStr : app_id=2021001103611075&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%2218953531935099684246020888%22%2C%22total_amount%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22passback_params%22%3A%22http%253A%252F%252Fyhmg.58xmh.com%252Fshop%252Fnotifypay%252Frecharge.html%22%2C%22disable_pay_channels%22%3A%22creditCard%22%2C%22timeout_express%22%3A%2260m%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fyhmg.58xmh.com%2Fshop%2Fnotifypay%2Frecharge.html&sign_type=RSA2&timestamp=2019-12-28+22%3A11%3A45&version=1.0&sign=JmWzAWG6rCqs3ve9CPUs4sgIEpCdYpur1OWnjbI7ORBaM7ehoIJZ7EfKmTJOFsBga37v%2BEl4h1tZE6EAH9%2B6453Q08jCPW5XqCIFuD6Bw%2Br4GgZ1PdiTNkFqJ3B%2BiLZasPtx3Kkpqnzm5XyYwZkUukufBACohjQEKhau3nUoNhjHwpNzxZK%2F0mIo%2FoDykWTAuD3YIPu9Z3xR9I8LuZYjsCcnNIfjrtTIonlSaSPqiQsw%2FTaV1xKTmxh%2BQxHPg%2FoXEpxdooD4x6UITQUFgFfOxm6rX9wW0H32ZrlOmEC15ZGbWIwSZ%2BoWJTVMZHdHu9BewGlVxeCW30ak07gDgsx4sA%3D%3D
         * is_pay : false
         */

        private String payStr;
        private boolean is_pay;

        public String getPayStr() {
            return payStr;
        }

        public void setPayStr(String payStr) {
            this.payStr = payStr;
        }

        public boolean isIs_pay() {
            return is_pay;
        }

        public void setIs_pay(boolean is_pay) {
            this.is_pay = is_pay;
        }
    }
}
