package com.company;

abstract class User {
    protected String m_name;
    protected String m_login;
    protected String m_password;

    User(String name, String login, String password) {
        this.m_name = name;
        this.m_login = login;
        this.m_password = password;
    }

    abstract boolean enter(String login, String password);
    abstract String getName();
}

class Student extends User {
    private int m_questionsCount = 0;
    private int m_rightAnswers = 0;

    Student(String name, String login, String password) {
        super(name, login, password);
    }

    void getAnswer() {
        //...
    }

    void clear() {
        this.m_questionsCount = 0;
        this.m_rightAnswers = 0;
    }

    @Override
    String getName() {
        return this.m_name;
    }

    @Override
    boolean enter(String login, String password) {
        return this.m_login.equals(login) && this.m_password.equals(password);
    }
}

class Admin extends User {
    Admin() {
        super("Admin", "admin", "admin");
    }

    @Override
    String getName() {
        return this.m_name;
    }

    @Override
    boolean enter(String login, String password) {
        return this.m_login.equals(login) && this.m_password.equals(password);
    }
}