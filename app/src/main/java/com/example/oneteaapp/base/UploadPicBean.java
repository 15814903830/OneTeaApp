package com.example.oneteaapp.base;

public class UploadPicBean {

    /**
     * code : 1
     * msg : 上传成功！
     * data : {"path":"/uploads/picture/20200101/2febb26041b4af74c8b50a64a10885ab.png","md5":"5a4e83f125f28a2d43e7df60a59f6c83","sha1":"","status":1,"create_time":1577880955,"id":"115"}
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
         * path : /uploads/picture/20200101/2febb26041b4af74c8b50a64a10885ab.png
         * md5 : 5a4e83f125f28a2d43e7df60a59f6c83
         * sha1 :
         * status : 1
         * create_time : 1577880955
         * id : 115
         */

        private String path;
        private String md5;
        private String sha1;
        private int status;
        private int create_time;
        private String id;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
