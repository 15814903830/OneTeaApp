package com.example.oneteaapp.base;

import java.util.List;

public class JiaoYiBase {

    /**
     * code : 1
     * msg : ok success
     * data : {"count":1,"data":[{"id":1,"title":"溯源白茶 2019  ","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":["/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg"],"content":"<p>测试商品1<\/p>","init_price":null,"create_time":"2019-12-24 01:56:13","update_time":"2019-12-29 15:55:59","is_del":"启用","code":"BBCA123465"}]}
     * url :
     * wait : 3
     */

    private int code;
    private String msg;
    private DataBeanX data;
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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
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

    public static class DataBeanX {
        /**
         * count : 1
         * data : [{"id":1,"title":"溯源白茶 2019  ","cover":"/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg","imgs":["/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg"],"content":"<p>测试商品1<\/p>","init_price":null,"create_time":"2019-12-24 01:56:13","update_time":"2019-12-29 15:55:59","is_del":"启用","code":"BBCA123465"}]
         */

        private int count;
        private List<DataBean> data;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
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
             * title : 溯源白茶 2019
             * cover : /uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg
             * imgs : ["/uploads/picture/20191215/5e4dd6f2526ca71877f2d1c3cc8d1bc5.jpg"]
             * content : <p>测试商品1</p>
             * init_price : null
             * create_time : 2019-12-24 01:56:13
             * update_time : 2019-12-29 15:55:59
             * is_del : 启用
             * code : BBCA123465
             */

            private int id;
            private String title;
            private String cover;
            private String content;
            private Object init_price;
            private String create_time;
            private String update_time;
            private String is_del;
            private String code;
            private List<String> imgs;

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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public List<String> getImgs() {
                return imgs;
            }

            public void setImgs(List<String> imgs) {
                this.imgs = imgs;
            }
        }
    }
}
