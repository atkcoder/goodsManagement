package jju.soft;

import java.io.Serializable;

public class userData implements Serializable {
    private String userName, userPwd;

    public userData(){

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public boolean ensurePwd(String s){
        return s .equals(userPwd);
    }
}
