package com.company;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    // loading data
    private static List<User> m_users = new LinkedList<User>();
    private static List<Question> m_questions;

    // global vars
    private static Scanner  in              = new Scanner(System.in);
    private static User     m_currentUser   = null;
    private static boolean  unknownUser     = true;

    public static void main(String[] args) throws IOException {
        if (loadData()) {
            m_users.add(new Admin());
            m_users.add(new Student("Михаил", "max", "max"));

            while (true) {
                while (unknownUser) {
                    String login = "";
                    String password = "";

                    System.out.print("Login: ");
                    login = in.nextLine();

                    System.out.print("Password: ");
                    password = in.nextLine();

                    m_currentUser = findUser(login, password);

                    if (unknownUser && m_currentUser == null)
                        System.out.println("Unknown user.");
                }

                m_currentUser.consoleInterface();

                m_currentUser = null;
                unknownUser = true;
            }
        }
        else System.out.println("Error loading data.");
    }

    private static User findUser(String login, String password) {
        for (User user : m_users) {
            if (user.enter(login, password)) {
                System.out.println("Welcome, " + user.getName() + ".\nSuccesses authorization!");
                unknownUser = false;
                return user;
            }
        }
        return null;
    }

    private static boolean loadData() throws IOException {
        FileReader reader = new FileReader("students.txt");
        String textFile = "";
        int c;

        while ((c = reader.read()) > 0)
            textFile += (char)c;

        String regex = "[a-zA-Z[а-яА-Я]]{2,}\\G\\s\\b[a-zA-Z[а-яА-Я]]{2,}\\G\\s\\b[a-zA-Z[а-яА-Я]]{2,}\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textFile);

        while (matcher.find()) {
            System.out.println(textFile.substring(matcher.start(), matcher.end()));
        }

        return false;
    }

    private boolean saveData() {
        return false;
    }
}
