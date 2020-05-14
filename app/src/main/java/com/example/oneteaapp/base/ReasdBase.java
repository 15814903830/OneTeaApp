package com.example.oneteaapp.base;

public class ReasdBase {

    /**
     * code : 1
     * msg : 注册成功!
     * data : {"user_info":{"id":178,"pid":2,"level":3,"pid_code":",2,1,","form_model":1,"nickname":"15814903833","cover":0,"openid":"","account":"15814903833","email":"","status":1,"ip":"120.79.11.196","is_gm":0,"create_time":1578239701,"update_time":1578239701,"subjection_id":2,"member_level":0,"invitation_code":"","money":"0.00"},"token":"2aFEczjgkO_178"}
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
         * user_info : {"id":178,"pid":2,"level":3,"pid_code":",2,1,","form_model":1,"nickname":"15814903833","cover":0,"openid":"","account":"15814903833","email":"","status":1,"ip":"120.79.11.196","is_gm":0,"create_time":1578239701,"update_time":1578239701,"subjection_id":2,"member_level":0,"invitation_code":"","money":"0.00"}
         * token : 2aFEczjgkO_178
         */

        private UserInfoBean user_info;
        private String token;

        public UserInfoBean getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfoBean user_info) {
            this.user_info = user_info;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserInfoBean {
            /**
             * id : 178
             * pid : 2
             * level : 3
             * pid_code : ,2,1,
             * form_model : 1
             * nickname : 15814903833
             * cover : 0
             * openid :
             * account : 15814903833
             * email :
             * status : 1
             * ip : 120.79.11.196
             * is_gm : 0
             * create_time : 1578239701
             * update_time : 1578239701
             * subjection_id : 2
             * member_level : 0
             * invitation_code :
             * money : 0.00
             */

            private int id;
            private int pid;
            private int level;
            private String pid_code;
            private int form_model;
            private String nickname;
            private int cover;
            private String openid;
            private String account;
            private String email;
            private int status;
            private String ip;
            private int is_gm;
            private int create_time;
            private int update_time;
            private int subjection_id;
            private int member_level;
            private String invitation_code;
            private String money;

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPid_code() {
                return pid_code;
            }

            public void setPid_code(String pid_code) {
                this.pid_code = pid_code;
            }

            public int getForm_model() {
                return form_model;
            }

            public void setForm_model(int form_model) {
                this.form_model = form_model;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getCover() {
                return cover;
            }

            public void setCover(int cover) {
                this.cover = cover;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public int getIs_gm() {
                return is_gm;
            }

            public void setIs_gm(int is_gm) {
                this.is_gm = is_gm;
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

            public int getSubjection_id() {
                return subjection_id;
            }

            public void setSubjection_id(int subjection_id) {
                this.subjection_id = subjection_id;
            }

            public int getMember_level() {
                return member_level;
            }

            public void setMember_level(int member_level) {
                this.member_level = member_level;
            }

            public String getInvitation_code() {
                return invitation_code;
            }

            public void setInvitation_code(String invitation_code) {
                this.invitation_code = invitation_code;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
