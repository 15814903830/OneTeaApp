package com.example.oneteaapp.base;

public class UsetBase {

    /**
     * code : 1
     * msg : ok success
     * data : {"id":174,"pid":0,"level":1,"pid_code":"","form_model":1,"nickname":"15814903833","cover":116,"openid":"","account":"15814903830","password":"$2y$10$V/izjCPPBMN86PIBQvV7ge/kf6f6.latSH7Bv5jpi71wN9W5Arti.","email":"","status":1,"ip":"120.79.11.196","is_gm":0,"create_time":1577457060,"update_time":1577546305,"subjection_id":0,"member_level":0,"invitation_code":"Z1MFAprp","money":"0.00","has_stock":0,"cover_path":"/uploads/picture/20200102/3dc370550435367846c93a6ddd5b2a46.png","reward_money":0,"my_team":0}
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
         * id : 174
         * pid : 0
         * level : 1
         * pid_code :
         * form_model : 1
         * nickname : 15814903833
         * cover : 116
         * openid :
         * account : 15814903830
         * password : $2y$10$V/izjCPPBMN86PIBQvV7ge/kf6f6.latSH7Bv5jpi71wN9W5Arti.
         * email :
         * status : 1
         * ip : 120.79.11.196
         * is_gm : 0
         * create_time : 1577457060
         * update_time : 1577546305
         * subjection_id : 0
         * member_level : 0
         * invitation_code : Z1MFAprp
         * money : 0.00
         * has_stock : 0
         * cover_path : /uploads/picture/20200102/3dc370550435367846c93a6ddd5b2a46.png
         * reward_money : 0
         * my_team : 0
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
        private String password;
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
        private int has_stock;
        private String cover_path;
        private int reward_money;
        private int my_team;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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

        public int getHas_stock() {
            return has_stock;
        }

        public void setHas_stock(int has_stock) {
            this.has_stock = has_stock;
        }

        public String getCover_path() {
            return cover_path;
        }

        public void setCover_path(String cover_path) {
            this.cover_path = cover_path;
        }

        public int getReward_money() {
            return reward_money;
        }

        public void setReward_money(int reward_money) {
            this.reward_money = reward_money;
        }

        public int getMy_team() {
            return my_team;
        }

        public void setMy_team(int my_team) {
            this.my_team = my_team;
        }
    }
}
