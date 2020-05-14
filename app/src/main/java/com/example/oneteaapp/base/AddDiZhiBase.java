package com.example.oneteaapp.base;

import java.util.List;

public class AddDiZhiBase {

    /**
     * code : 1
     * msg : ok success
     * data : [{"id":15,"uid":174,"is_default":1,"consignee":"蔡","mobile":"15814903830","province_city_area":"北京市 东城区","address":"测试地址","province_code":110000,"city_code":110101,"area_code":0,"create_time":1578058273,"update_time":1578058273}]
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
         * id : 15
         * uid : 174
         * is_default : 1
         * consignee : 蔡
         * mobile : 15814903830
         * province_city_area : 北京市 东城区
         * address : 测试地址
         * province_code : 110000
         * city_code : 110101
         * area_code : 0
         * create_time : 1578058273
         * update_time : 1578058273
         */

        private int id;
        private int uid;
        private int is_default;
        private String consignee;
        private String mobile;
        private String province_city_area;
        private String address;
        private int province_code;
        private int city_code;
        private int area_code;
        private int create_time;
        private int update_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getProvince_city_area() {
            return province_city_area;
        }

        public void setProvince_city_area(String province_city_area) {
            this.province_city_area = province_city_area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getProvince_code() {
            return province_code;
        }

        public void setProvince_code(int province_code) {
            this.province_code = province_code;
        }

        public int getCity_code() {
            return city_code;
        }

        public void setCity_code(int city_code) {
            this.city_code = city_code;
        }

        public int getArea_code() {
            return area_code;
        }

        public void setArea_code(int area_code) {
            this.area_code = area_code;
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
