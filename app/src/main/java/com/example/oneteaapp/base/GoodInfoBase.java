package com.example.oneteaapp.base;

import java.util.List;

public class GoodInfoBase {

    /**
     * code : 1
     * msg : success
     * data : {"id":27,"title":"测试电子大屏幕手机","cate_id":4,"brand_id":1,"business_id":1,"price":"10.00","m_price":"10.00","content":"<p>123<\/p>","status":1,"is_del":0,"del_time":0,"create_time":1576309318,"update_time":1576379729,"cover":91,"imgs":",91","suk_lists":[{"id":27,"goods_id":27,"title":"类型","sub":[{"id":"1","name":"大"},{"id":"2","name":"中"},{"id":"3","name":"小"}],"item_id":1,"create_time":1576379729,"update_time":1576379729}]}
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
         * id : 27
         * title : 测试电子大屏幕手机
         * cate_id : 4
         * brand_id : 1
         * business_id : 1
         * price : 10.00
         * m_price : 10.00
         * content : <p>123</p>
         * status : 1
         * is_del : 0
         * del_time : 0
         * create_time : 1576309318
         * update_time : 1576379729
         * cover : 91
         * imgs : ,91
         * suk_lists : [{"id":27,"goods_id":27,"title":"类型","sub":[{"id":"1","name":"大"},{"id":"2","name":"中"},{"id":"3","name":"小"}],"item_id":1,"create_time":1576379729,"update_time":1576379729}]
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
        private int cover;
        private String imgs;
        private List<SukListsBean> suk_lists;

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

        public List<SukListsBean> getSuk_lists() {
            return suk_lists;
        }

        public void setSuk_lists(List<SukListsBean> suk_lists) {
            this.suk_lists = suk_lists;
        }

        public static class SukListsBean {
            /**
             * id : 27
             * goods_id : 27
             * title : 类型
             * sub : [{"id":"1","name":"大"},{"id":"2","name":"中"},{"id":"3","name":"小"}]
             * item_id : 1
             * create_time : 1576379729
             * update_time : 1576379729
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
                 * name : 大
                 */

                private String id;
                private String name;

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
    }
}
