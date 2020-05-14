package com.example.oneteaapp.base;

import java.util.List;

public class ShoPinCarBase {

    /**
     * code : 1
     * msg : ok success
     * data : [{"id":15,"type":1,"uid":174,"business_id":0,"goods_id":36,"spec_id":203,"count":3,"create_time":1578125551,"update_time":1578125551,"goods_info":{"id":36,"title":"福鼎荒野老白茶饼特级陈8年寿眉","cate_id":13,"brand_id":4,"business_id":0,"price":"100.00","m_price":"290.00","content":"<p><img src=\"/uploads/ueditor/image/20191229/1577605126703434.gif\" title=\"1577605126703434.gif\"/><\/p><p><img src=\"/uploads/ueditor/image/20191229/1577605126178333.gif\" title=\"1577605126178333.gif\"/><\/p><p><img src=\"/uploads/ueditor/image/20191229/1577605126725669.gif\" title=\"1577605126725669.gif\"/><\/p><p><img src=\"/uploads/ueditor/image/20191229/1577605126678667.gif\" title=\"1577605126678667.gif\"/><\/p><p><br/><\/p>","status":1,"is_del":0,"del_time":0,"create_time":1577605129,"update_time":1577606012,"cover":"/uploads/picture/20191229/8909ebf9ad134a5e919a2c1c3b391b76.png","imgs":",109,110","label":"ishost,good","desc":"福鼎荒野老白茶饼特级陈8年寿眉"},"spec_info":{"id":203,"goods_id":36,"price":"100.00","m_price":"290.00","stock":399,"group_name":null,"sku_ids":"1_1","sku_title":"类型:100","sub_value":"{\"ids\":[{\"1\":\"1\"}],\"price\":\"100\",\"m_price\":\"290\",\"stock\":\"399\",\"sku\":\"11\"}","create_time":1577606012,"update_time":1577606012},"cate_info":{"id":13,"pid":3,"title":"2019年","create_time":1577603305,"update_time":1577603305,"business_id":0,"cover":0,"desc":null,"sort":0}}]
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
         * type : 1
         * uid : 174
         * business_id : 0
         * goods_id : 36
         * spec_id : 203
         * count : 3
         * create_time : 1578125551
         * update_time : 1578125551
         * goods_info : {"id":36,"title":"福鼎荒野老白茶饼特级陈8年寿眉","cate_id":13,"brand_id":4,"business_id":0,"price":"100.00","m_price":"290.00","content":"<p><img src=\"/uploads/ueditor/image/20191229/1577605126703434.gif\" title=\"1577605126703434.gif\"/><\/p><p><img src=\"/uploads/ueditor/image/20191229/1577605126178333.gif\" title=\"1577605126178333.gif\"/><\/p><p><img src=\"/uploads/ueditor/image/20191229/1577605126725669.gif\" title=\"1577605126725669.gif\"/><\/p><p><img src=\"/uploads/ueditor/image/20191229/1577605126678667.gif\" title=\"1577605126678667.gif\"/><\/p><p><br/><\/p>","status":1,"is_del":0,"del_time":0,"create_time":1577605129,"update_time":1577606012,"cover":"/uploads/picture/20191229/8909ebf9ad134a5e919a2c1c3b391b76.png","imgs":",109,110","label":"ishost,good","desc":"福鼎荒野老白茶饼特级陈8年寿眉"}
         * spec_info : {"id":203,"goods_id":36,"price":"100.00","m_price":"290.00","stock":399,"group_name":null,"sku_ids":"1_1","sku_title":"类型:100","sub_value":"{\"ids\":[{\"1\":\"1\"}],\"price\":\"100\",\"m_price\":\"290\",\"stock\":\"399\",\"sku\":\"11\"}","create_time":1577606012,"update_time":1577606012}
         * cate_info : {"id":13,"pid":3,"title":"2019年","create_time":1577603305,"update_time":1577603305,"business_id":0,"cover":0,"desc":null,"sort":0}
         */

        private int id;
        private int type;
        private int uid;
        private int business_id;
        private int goods_id;
        private int spec_id;
        private int count;
        private int create_time;
        private int update_time;

        public boolean isXunzhe() {
            return xunzhe;
        }

        public void setXunzhe(boolean xunzhe) {
            this.xunzhe = xunzhe;
        }

        private GoodsInfoBean goods_info;
        private SpecInfoBean spec_info;
        private CateInfoBean cate_info;
        boolean xunzhe;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getBusiness_id() {
            return business_id;
        }

        public void setBusiness_id(int business_id) {
            this.business_id = business_id;
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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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

        public SpecInfoBean getSpec_info() {
            return spec_info;
        }

        public void setSpec_info(SpecInfoBean spec_info) {
            this.spec_info = spec_info;
        }

        public CateInfoBean getCate_info() {
            return cate_info;
        }

        public void setCate_info(CateInfoBean cate_info) {
            this.cate_info = cate_info;
        }

        public static class GoodsInfoBean {
            /**
             * id : 36
             * title : 福鼎荒野老白茶饼特级陈8年寿眉
             * cate_id : 13
             * brand_id : 4
             * business_id : 0
             * price : 100.00
             * m_price : 290.00
             * content : <p><img src="/uploads/ueditor/image/20191229/1577605126703434.gif" title="1577605126703434.gif"/></p><p><img src="/uploads/ueditor/image/20191229/1577605126178333.gif" title="1577605126178333.gif"/></p><p><img src="/uploads/ueditor/image/20191229/1577605126725669.gif" title="1577605126725669.gif"/></p><p><img src="/uploads/ueditor/image/20191229/1577605126678667.gif" title="1577605126678667.gif"/></p><p><br/></p>
             * status : 1
             * is_del : 0
             * del_time : 0
             * create_time : 1577605129
             * update_time : 1577606012
             * cover : /uploads/picture/20191229/8909ebf9ad134a5e919a2c1c3b391b76.png
             * imgs : ,109,110
             * label : ishost,good
             * desc : 福鼎荒野老白茶饼特级陈8年寿眉
             */

            private int id;
            private String title;
            private int cate_id;
            private int brand_id;
            private int business_id;
            private String price;
            private String m_price;
            private String content;
            private int status;
            private int is_del;
            private int del_time;
            private int create_time;
            private int update_time;
            private String cover;
            private String imgs;
            private String label;
            private String desc;

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

            public int getCate_id() {
                return cate_id;
            }

            public void setCate_id(int cate_id) {
                this.cate_id = cate_id;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public int getBusiness_id() {
                return business_id;
            }

            public void setBusiness_id(int business_id) {
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_del() {
                return is_del;
            }

            public void setIs_del(int is_del) {
                this.is_del = is_del;
            }

            public int getDel_time() {
                return del_time;
            }

            public void setDel_time(int del_time) {
                this.del_time = del_time;
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

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }
        }

        public static class SpecInfoBean {
            /**
             * id : 203
             * goods_id : 36
             * price : 100.00
             * m_price : 290.00
             * stock : 399
             * group_name : null
             * sku_ids : 1_1
             * sku_title : 类型:100
             * sub_value : {"ids":[{"1":"1"}],"price":"100","m_price":"290","stock":"399","sku":"11"}
             * create_time : 1577606012
             * update_time : 1577606012
             */

            private int id;
            private int goods_id;
            private String price;
            private String m_price;
            private int stock;
            private Object group_name;
            private String sku_ids;
            private String sku_title;
            private String sub_value;
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

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public Object getGroup_name() {
                return group_name;
            }

            public void setGroup_name(Object group_name) {
                this.group_name = group_name;
            }

            public String getSku_ids() {
                return sku_ids;
            }

            public void setSku_ids(String sku_ids) {
                this.sku_ids = sku_ids;
            }

            public String getSku_title() {
                return sku_title;
            }

            public void setSku_title(String sku_title) {
                this.sku_title = sku_title;
            }

            public String getSub_value() {
                return sub_value;
            }

            public void setSub_value(String sub_value) {
                this.sub_value = sub_value;
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

        public static class CateInfoBean {
            /**
             * id : 13
             * pid : 3
             * title : 2019年
             * create_time : 1577603305
             * update_time : 1577603305
             * business_id : 0
             * cover : 0
             * desc : null
             * sort : 0
             */

            private int id;
            private int pid;
            private String title;
            private int create_time;
            private int update_time;
            private int business_id;
            private int cover;
            private Object desc;
            private int sort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public int getBusiness_id() {
                return business_id;
            }

            public void setBusiness_id(int business_id) {
                this.business_id = business_id;
            }

            public int getCover() {
                return cover;
            }

            public void setCover(int cover) {
                this.cover = cover;
            }

            public Object getDesc() {
                return desc;
            }

            public void setDesc(Object desc) {
                this.desc = desc;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
