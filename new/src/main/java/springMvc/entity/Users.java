package springMvc.entity;

import java.io.Serializable;

/**
 * cs
 */
public class Users implements Serializable {
    private String userName;
    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
