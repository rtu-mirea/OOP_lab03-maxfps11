package com.company;


import java.util.*;

class Question {
    private String m_text;
    private String m_answer;

    Question(String text, String answer) {
        this.m_text = text;
        this.m_answer = answer;
    }

    String getText() {
        return this.m_text;
    }

    String getAnswer() {
        return this.m_answer;
    }

    boolean isCorrect(String answer) {
        return this.m_answer.equals(answer);
    }
}

class Result {
    //...
}

public class DistanceExamination {
    private List<User> m_users;
    private List<Question> m_questions;
    private User m_currentUser;

    public static void main(String[] args) {
        List<User> users = new LinkedList<User>();

        users.add(new Admin());

        Scanner in = new Scanner(System.in);

        boolean unknownUser = true;

        while (unknownUser) {
            String login = "";
            String password = "";

            System.out.print("Login: ");
            login = in.nextLine();

            System.out.print("Password: ");
            password = in.nextLine();

            for (User user : users) {
                if (user.enter(login, password)) {
                    System.out.println("Welcome, " + user.getName() + ".\nSuccesses authorization!");
                    unknownUser = false;
                }
            }

            if (unknownUser)
                System.out.println("Unknown user.");
        }
    }

    private void addUser(String name, String login, String password, String reputation) {
        //...
    }

    private User findUser(String login, String password) {
        //...
        return null;
    }

    private void save() {
        //...
    }

    private void load() {
        //...
    }

    private List<Result> getResults() {
        return null;
    }
}
