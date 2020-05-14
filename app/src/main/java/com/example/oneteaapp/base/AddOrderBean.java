package com.example.oneteaapp.base;

public class AddOrderBean {

    /**
     * code : 1
     * msg : ok success
     * data : {"payStr":"app_id=2021001103611075&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%22281446401078178400402840826%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22disable_pay_channels%22%3A%22creditCard%22%2C%22timeout_express%22%3A%2260m%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fm.yihaominggu.com%2Fshop%2Fnotifypay%2Findex.html&sign_type=RSA2×tamp=2020-01-05+18%3A50%3A55&version=1.0&sign=URmHYtl7SwsukboAN6TgpI1aIytHw7al7UtcD3s6auNlGHJZ9RmRUTIi57KAcf8PC5roXq14j0cCSrgsq%2BjxtG6uB9bEgkyxBDm1JxJLGXgET8ZE6qm7f8IFYIwObmReMai6bMyws55HDEkfbe7tCypR3yBd9a29iHPfn690qcxwrPU95RJiSbOxfWujcBpUZJN6n6DiUHtneL84hnsfMQvvXnfDV7Q99GfriwJ9CP8d6sx4IjvYa8JoT5J5AV4s4lFEc36YrqVTMZgy%2Fb4M4d3rwDHufC6zEWamtMAbfs5ccPeV55REeyFI74DCAnkV5irw%2BfqRQ2zaj4VUW5jxLw%3D%3D","is_pay":false}
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
         * payStr : app_id=2021001103611075&biz_content=%7B%22body%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22subject%22%3A%22%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%22281446401078178400402840826%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22disable_pay_channels%22%3A%22creditCard%22%2C%22timeout_express%22%3A%2260m%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fm.yihaominggu.com%2Fshop%2Fnotifypay%2Findex.html&sign_type=RSA2×tamp=2020-01-05+18%3A50%3A55&version=1.0&sign=URmHYtl7SwsukboAN6TgpI1aIytHw7al7UtcD3s6auNlGHJZ9RmRUTIi57KAcf8PC5roXq14j0cCSrgsq%2BjxtG6uB9bEgkyxBDm1JxJLGXgET8ZE6qm7f8IFYIwObmReMai6bMyws55HDEkfbe7tCypR3yBd9a29iHPfn690qcxwrPU95RJiSbOxfWujcBpUZJN6n6DiUHtneL84hnsfMQvvXnfDV7Q99GfriwJ9CP8d6sx4IjvYa8JoT5J5AV4s4lFEc36YrqVTMZgy%2Fb4M4d3rwDHufC6zEWamtMAbfs5ccPeV55REeyFI74DCAnkV5irw%2BfqRQ2zaj4VUW5jxLw%3D%3D
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
