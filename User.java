import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class User {
    protected String m_name;
    protected String m_login;
    protected String m_password;

    protected boolean isLogin = false;

    User(String name, String login, String password) {
        this.m_name = name;
        this.m_login = login;
        this.m_password = password;
    }

    boolean enter(String login, String password) {
        if (this.m_login.equals(login) && this.m_password.equals(password))
            isLogin = true;
        return isLogin;
    }

    String getName() {
        return this.m_name;
    }

    abstract void printInfo();
    abstract void consoleInterface() throws InterruptedException;
}
