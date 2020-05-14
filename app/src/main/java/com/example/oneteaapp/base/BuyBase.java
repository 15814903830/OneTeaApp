package com.example.oneteaapp.base;

public class BuyBase {

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
