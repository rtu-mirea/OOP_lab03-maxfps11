import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DistanceExamination {
    // loading data
    private static List<User> m_users = new LinkedList<User>();
    private static List<Question> m_questions = new LinkedList<Question>();

    // global vars
    private static Scanner  in              = new Scanner(System.in);
    private static User     m_currentUser   = null;
    private static boolean  unknownUser     = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (loadData()) {
            m_users.add(new Admin(m_questions, m_users));

            while (true) {
                while (unknownUser) {
                    String login = "";
                    String password = "";

                    System.out.print("Login: ");
                    login = in.nextLine();

                    if (login.equals("exit"))
                        System.exit(0);

                    System.out.print("Password: ");
                    password = in.nextLine();

                    m_currentUser = findUser(login, password);

                    if (unknownUser && m_currentUser == null)
                        System.out.println("Unknown user.");
                }

                assert m_currentUser != null;
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
        FileReader reader = new FileReader("/Users/maxfps11/IdeaProjects/ThirdLaboratoryWork/src/com/company/examInfo.txt");
        StringBuilder examInfoFile = new StringBuilder();
        int c;

        while ((c = reader.read()) > 0)
            examInfoFile.append((char) c);

        //String parameterLineRegex = "\\b[a-zA-Z]+\\b\\s*=\\s*\\b\\d+\\b";
        //String parameterNameRegex = "\\b[a-zA-Z]+\\b";
        //String parameterValueRegex = "\\b\\d+\\b";

        Pattern pattern;// = Pattern.compile(parameterLineRegex);
        /*Matcher parameterLineMatcher = pattern.matcher(examInfoFile.toString());

        Pattern parameterValuePattern = Pattern.compile(parameterValueRegex);
        Pattern parameterNamePattern = Pattern.compile(parameterNameRegex);

        Matcher parameterValueMatcher;
        Matcher parameterNameMatcher;

        String buffer = "";

        while (parameterLineMatcher.find()) {
            buffer = examInfoFile.substring(parameterLineMatcher.start(), parameterLineMatcher.end());

            parameterNameMatcher = parameterNamePattern.matcher(buffer);
            parameterValueMatcher = parameterValuePattern.matcher(buffer);

            while (parameterNameMatcher.find() && parameterValueMatcher.find()) {
                m_settings.setParameters(buffer.substring(parameterNameMatcher.start(), parameterNameMatcher.end()),
                        Integer.parseInt(buffer.substring(parameterValueMatcher.start(), parameterValueMatcher.end())));
            }

            System.out.println(m_settings.getParameters("QuestionQuantity"));
        }*/
        reader.close();

        reader = new FileReader("/Users/maxfps11/IdeaProjects/ThirdLaboratoryWork/src/com/company/students.txt");
        StringBuilder studentsFile = new StringBuilder();

        while ((c = reader.read()) > 0)
            studentsFile.append((char) c);

        String nameRegex = "name:[a-zA-Z[а-яА-Я]]{2,}\\s\\b[a-zA-Z[а-яА-Я]]{2,}\\s\\b[a-zA-Z[а-яА-Я]]{2,}\\b";
        String loginRegex = "login:([a-zA-Z]|[0-9]){3,}";
        String passRegex = "password:([a-zA-Z]|[0-9]){3,}";

        pattern = Pattern.compile(nameRegex);
        Matcher nameMatcher = pattern.matcher(studentsFile.toString());

        pattern = Pattern.compile(loginRegex);
        Matcher loginMatcher = pattern.matcher(studentsFile.toString());

        pattern = Pattern.compile(passRegex);
        Matcher passMatcher = pattern.matcher(studentsFile.toString());

        while (nameMatcher.find() && loginMatcher.find() && passMatcher.find()) {
            m_users.add(new Student(studentsFile.substring(nameMatcher.start(), nameMatcher.end()).replace("name:", ""),
                    studentsFile.substring(loginMatcher.start(), loginMatcher.end()).replace("login:", ""),
                    studentsFile.substring(passMatcher.start(), passMatcher.end()).replace("password:", ""),
                    m_questions));
        }
        reader.close();

        reader = new FileReader("/Users/maxfps11/IdeaProjects/ThirdLaboratoryWork/src/com/company/questions.txt");
        StringBuilder questionsFile = new StringBuilder();

        while ((c = reader.read()) > 0)
            questionsFile.append((char) c);

        String quesRegex = "question:.+";
        String answerRegex = "answer:.+";

        pattern = Pattern.compile(quesRegex);
        Matcher quesMatcher = pattern.matcher(questionsFile.toString());

        pattern = Pattern.compile(answerRegex);
        Matcher answerMatcher = pattern.matcher(questionsFile.toString());

        while (quesMatcher.find() && answerMatcher.find()) {
            m_questions.add(new Question(
                    questionsFile.substring(quesMatcher.start(), quesMatcher.end()).replace(
                            "question:", "").replace(
                                    "\\n", "\n"),
                    questionsFile.substring(answerMatcher.start(), answerMatcher.end()).replace(
                            "answer:", "")));
        }
        reader.close();
        return studentsFile.length() > 0 && questionsFile.length() > 0;
    }

    private boolean saveData() {
        return false;
    }
}
