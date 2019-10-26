package com.company;

import java.util.Scanner;

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

    abstract void consoleInterface();
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
    void consoleInterface() {
        Scanner in = new Scanner(System.in);

        if (this.isLogin)
            System.out.println("Добро пожаловать в главное меню " + this.m_name + ".");

        while (this.isLogin) {
            printMainConsoleMenu();
            printYourChoice();

            int value = in.nextInt();

            switch (value) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    this.isLogin = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Повторите.");
                    break;
            }
        }
    }

    // menus methods


    // support methods for menus methods
    private void printMainConsoleMenu() {
        System.out.println("\tГлавное меню.\n" +
                "[1] Учетные записи.\n" +
                "[2] Экзаменационные вопросы.\n" +
                "[3] Результаты.\n" +
                "[0] Покинуть учетную запись.");
    }

    private void printFirstSubMenu() {
        System.out.println("\tУчетные записи.\n" +
                "[1] Добавить.\n" +
                "[2] Удалить.\n" +
                "[3] Найти.\n" +
                "[4] Список всех учетных записей.\n" +
                "[0] Назад.");
    }

    private void printSecondSubMenu() {
        System.out.println("\tЭкзаменационные вопросы.\n" +
                "[1] Добавить.\n" +
                "[2] Удалить.\n" +
                "[3] Найти.\n" +
                "[4] Список всех вопросов.\n" +
                "[0] Назад.");
    }

    private void printThirdSubMenu() {
        System.out.println("\tРезультаты.\n" +
                "[1] Найти.\n" +
                "[2] Список всех результатов.\n" +
                "[0] Назад.");
    }

    private void printYourChoice() {
        System.out.print("Ваш выбор: ");
    }
}

class Admin extends User {
    Admin() {
        super("Admin", "admin", "admin");
    }

    void addStudent() {
        //...
    }

    void addQuestion() {
        //...
    }

    @Override
    void consoleInterface() {
        Scanner in = new Scanner(System.in);

        if (this.isLogin)
            System.out.println("Добро пожаловать в главное меню " + this.m_name + ".");

        while (this.isLogin) {
            printMainConsoleMenu();
            printYourChoice();

            int value = in.nextInt();

            switch (value) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    this.isLogin = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Повторите.");
                    break;
            }
        }
    }

    // support methods for menus methods
    private void printMainConsoleMenu() {
        System.out.println("\tГлавное меню.\n" +
                "[1] Учетные записи.\n" +
                "[2] Экзаменационные вопросы.\n" +
                "[3] Результаты.\n" +
                "[0] Покинуть учетную запись.");
    }

    private void printFirstSubMenu() {
        System.out.println("\tУчетные записи.\n" +
                "[1] Добавить.\n" +
                "[2] Удалить.\n" +
                "[3] Найти.\n" +
                "[4] Список всех учетных записей.\n" +
                "[0] Назад.");
    }

    private void printSecondSubMenu() {
        System.out.println("\tЭкзаменационные вопросы.\n" +
                "[1] Добавить.\n" +
                "[2] Удалить.\n" +
                "[3] Найти.\n" +
                "[4] Список всех вопросов.\n" +
                "[0] Назад.");
    }

    private void printThirdSubMenu() {
        System.out.println("\tРезультаты.\n" +
                "[1] Найти.\n" +
                "[2] Список всех результатов.\n" +
                "[0] Назад.");
    }

    private void printYourChoice() {
        System.out.print("Ваш выбор: ");
    }
}