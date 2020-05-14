package com.example.oneteaapp.base;

import com.google.gson.annotations.SerializedName;

public class WeixBase {

    /**
     * code : 1
     * msg : 下单成功！
     * data : {"payStr":{"appid":"wxcf6ea6e0b71b8132","partnerid":"1571749511","prepayid":"wx291054389750284da3bafd961140540000","package":"Sign=WXPay","noncestr":"kntgan2dxsxxx3y123jc1ha2endvu4si","timestamp":1577588079,"sign":"779098FECC294EC44B2FFDF7F6882A81"},"is_pay":false}
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
         * payStr : {"appid":"wxcf6ea6e0b71b8132","partnerid":"1571749511","prepayid":"wx291054389750284da3bafd961140540000","package":"Sign=WXPay","noncestr":"kntgan2dxsxxx3y123jc1ha2endvu4si","timestamp":1577588079,"sign":"779098FECC294EC44B2FFDF7F6882A81"}
         * is_pay : false
         */

        private PayStrBean payStr;
        private boolean is_pay;

        public PayStrBean getPayStr() {
            return payStr;
        }

        public void setPayStr(PayStrBean payStr) {
            this.payStr = payStr;
        }

        public boolean isIs_pay() {
            return is_pay;
        }

        public void setIs_pay(boolean is_pay) {
            this.is_pay = is_pay;
        }

        public static class PayStrBean {
            /**
             * appid : wxcf6ea6e0b71b8132
             * partnerid : 1571749511
             * prepayid : wx291054389750284da3bafd961140540000
             * package : Sign=WXPay
             * noncestr : kntgan2dxsxxx3y123jc1ha2endvu4si
             * timestamp : 1577588079
             * sign : 779098FECC294EC44B2FFDF7F6882A81
             */

            private String appid;
            private String partnerid;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String noncestr;
            private int timestamp;
            private String sign;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }
        }
    }
}
