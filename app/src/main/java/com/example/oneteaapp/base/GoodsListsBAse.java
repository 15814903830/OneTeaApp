package com.example.oneteaapp.base;

public class GoodsListsBAse {
    private String goods_id;
    private String spec_id;
    private String count;

    @Override
    public String toString() {
        return "GoodsListsBAse{" +
                "goods_id='" + goods_id + '\'' +
                ", spec_id='" + spec_id + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
