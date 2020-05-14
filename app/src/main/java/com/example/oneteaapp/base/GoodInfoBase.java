package com.example.oneteaapp.base;

import java.util.List;

public class GoodInfoBase {

    /**
     * code : 1
     * msg : success
     * data : {"id":32,"title":"2018白牡丹茶300克礼盒装 ","cate_id":24,"brand_id":4,"business_id":0,"price":"100.00","m_price":"200.00","content":"<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0\" name=\"viewport\"><style> img{max-width: 100%!important;height: auto;} body{width: 100%;} .content p img{width: 100%;}<\/style><div class=\"content\" ><p><img src=\"http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604743635227.gif\" title=\"1577604743635227.gif\" alt=\"6.gif\"/><\/p><p><img src=\"http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604751660648.gif\" title=\"1577604751660648.gif\"/><\/p><p><img src=\"http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604751540489.gif\" title=\"1577604751540489.gif\"/><\/p><p><img src=\"http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604751497775.gif\" title=\"1577604751497775.gif\"/><\/p><p><img src=\"http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604752736957.gif\" title=\"1577604752736957.gif\"/><\/p><p><br/><\/p><\/div>","status":1,"is_del":0,"del_time":0,"create_time":1577604755,"update_time":1577606049,"cover":"/uploads/picture/20191229/d898879ed936e4bbd82dd985bc7a21a8.png","imgs":["/uploads/picture/20191229/d898879ed936e4bbd82dd985bc7a21a8.png","/uploads/picture/20191229/8909ebf9ad134a5e919a2c1c3b391b76.png"],"label":"ishost,good","desc":"2018白牡丹茶300克礼盒装 ","suk_lists":[{"id":32,"goods_id":32,"title":"类型","sub":[{"id":"1","name":"100克"},{"id":"2","name":"200克"},{"id":"3","name":"300克"}],"item_id":1,"create_time":1577606049,"update_time":1577606049}],"spec_lists":[{"id":211,"goods_id":32,"price":"100.00","m_price":"200.00","stock":300,"group_name":null,"sku_ids":"1_1","sku_title":"类型:100克","sub_value":"{\"ids\":[{\"1\":\"1\"}],\"price\":\"100\",\"m_price\":\"200\",\"stock\":\"300\",\"sku\":\"1121\"}","create_time":1577606049,"update_time":1577606049},{"id":212,"goods_id":32,"price":"100.00","m_price":"200.00","stock":300,"group_name":null,"sku_ids":"1_2","sku_title":"类型:200克","sub_value":"{\"ids\":[{\"1\":\"2\"}],\"price\":\"100\",\"m_price\":\"200\",\"stock\":\"300\",\"sku\":\"1122\"}","create_time":1577606049,"update_time":1577606049},{"id":213,"goods_id":32,"price":"100.00","m_price":"200.00","stock":300,"group_name":null,"sku_ids":"1_3","sku_title":"类型:300克","sub_value":"{\"ids\":[{\"1\":\"3\"}],\"price\":\"100\",\"m_price\":\"200\",\"stock\":\"300\",\"sku\":\"1123\"}","create_time":1577606049,"update_time":1577606049}],"is_collection":false}
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
         * id : 32
         * title : 2018白牡丹茶300克礼盒装
         * cate_id : 24
         * brand_id : 4
         * business_id : 0
         * price : 100.00
         * m_price : 200.00
         * content : <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"><style> img{max-width: 100%!important;height: auto;} body{width: 100%;} .content p img{width: 100%;}</style><div class="content" ><p><img src="http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604743635227.gif" title="1577604743635227.gif" alt="6.gif"/></p><p><img src="http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604751660648.gif" title="1577604751660648.gif"/></p><p><img src="http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604751540489.gif" title="1577604751540489.gif"/></p><p><img src="http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604751497775.gif" title="1577604751497775.gif"/></p><p><img src="http://m.yihaominggu.com/uploads/ueditor/image/20191229/1577604752736957.gif" title="1577604752736957.gif"/></p><p><br/></p></div>
         * status : 1
         * is_del : 0
         * del_time : 0
         * create_time : 1577604755
         * update_time : 1577606049
         * cover : /uploads/picture/20191229/d898879ed936e4bbd82dd985bc7a21a8.png
         * imgs : ["/uploads/picture/20191229/d898879ed936e4bbd82dd985bc7a21a8.png","/uploads/picture/20191229/8909ebf9ad134a5e919a2c1c3b391b76.png"]
         * label : ishost,good
         * desc : 2018白牡丹茶300克礼盒装
         * suk_lists : [{"id":32,"goods_id":32,"title":"类型","sub":[{"id":"1","name":"100克"},{"id":"2","name":"200克"},{"id":"3","name":"300克"}],"item_id":1,"create_time":1577606049,"update_time":1577606049}]
         * spec_lists : [{"id":211,"goods_id":32,"price":"100.00","m_price":"200.00","stock":300,"group_name":null,"sku_ids":"1_1","sku_title":"类型:100克","sub_value":"{\"ids\":[{\"1\":\"1\"}],\"price\":\"100\",\"m_price\":\"200\",\"stock\":\"300\",\"sku\":\"1121\"}","create_time":1577606049,"update_time":1577606049},{"id":212,"goods_id":32,"price":"100.00","m_price":"200.00","stock":300,"group_name":null,"sku_ids":"1_2","sku_title":"类型:200克","sub_value":"{\"ids\":[{\"1\":\"2\"}],\"price\":\"100\",\"m_price\":\"200\",\"stock\":\"300\",\"sku\":\"1122\"}","create_time":1577606049,"update_time":1577606049},{"id":213,"goods_id":32,"price":"100.00","m_price":"200.00","stock":300,"group_name":null,"sku_ids":"1_3","sku_title":"类型:300克","sub_value":"{\"ids\":[{\"1\":\"3\"}],\"price\":\"100\",\"m_price\":\"200\",\"stock\":\"300\",\"sku\":\"1123\"}","create_time":1577606049,"update_time":1577606049}]
         * is_collection : false
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
        private String label;
        private String desc;
        private boolean is_collection;
        private List<String> imgs;
        private List<SukListsBean> suk_lists;
        private List<SpecListsBean> spec_lists;

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

        public boolean isIs_collection() {
            return is_collection;
        }

        public void setIs_collection(boolean is_collection) {
            this.is_collection = is_collection;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        public List<SukListsBean> getSuk_lists() {
            return suk_lists;
        }

        public void setSuk_lists(List<SukListsBean> suk_lists) {
            this.suk_lists = suk_lists;
        }

        public List<SpecListsBean> getSpec_lists() {
            return spec_lists;
        }

        public void setSpec_lists(List<SpecListsBean> spec_lists) {
            this.spec_lists = spec_lists;
        }

        public static class SukListsBean {
            /**
             * id : 32
             * goods_id : 32
             * title : 类型
             * sub : [{"id":"1","name":"100克"},{"id":"2","name":"200克"},{"id":"3","name":"300克"}]
             * item_id : 1
             * create_time : 1577606049
             * update_time : 1577606049
             */

            private int id;
            private int goods_id;
            private String title;
            private int item_id;
            private int create_time;
            private int update_time;
            private List<SubBean> sub;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
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

            public List<SubBean> getSub() {
                return sub;
            }

            public void setSub(List<SubBean> sub) {
                this.sub = sub;
            }

            public static class SubBean {
                /**
                 * id : 1
                 * name : 100克
                 */

                private String id;

                public boolean isaBoolean() {
                    return aBoolean;
                }

                public void setaBoolean(boolean aBoolean) {
                    this.aBoolean = aBoolean;
                }

                private String name;
                private boolean aBoolean;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class SpecListsBean {

            /**
             * id : 211
             * goods_id : 32
             * price : 100.00
             * m_price : 200.00
             * stock : 300
             * group_name : null
             * sku_ids : 1_1
             * sku_title : 类型:100克
             * sub_value : {"ids":[{"1":"1"}],"price":"100","m_price":"200","stock":"300","sku":"1121"}
             * create_time : 1577606049
             * update_time : 1577606049
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
    }
}
