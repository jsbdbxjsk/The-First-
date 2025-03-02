package bean;
//用户名包含登录名，用户名,密码

public class User {
    private  String username;
    private  String password;
    private  String loginName;

    public User(String username, String number, String loginName) {
        this.username=username;
        this.password=number;
        this.loginName=loginName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
