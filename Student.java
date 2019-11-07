import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Student extends User {
    private Scanner         m_in            = new Scanner(System.in);
    private List<Question>  m_questions     = null;
    private int             m_rightAnswers  = 0;
    private boolean         m_isPassed      = false;

    Student(String name, String login, String password, List<Question> questions) {
        super(name, login, password);
        this.m_questions = questions;
    }

    private void passExam() {
        Random random = new Random();
        
        System.out.println("Успешной сдачи экзамена!");

        String answer = m_in.nextLine();

        for (int i = 0; i < 5; ++i) {
            Question question = this.m_questions.get(random.nextInt(11));
            question.printQuestion();

            System.out.print("Ваш ответ: ");
            answer = m_in.nextLine();

            if (answer.equals(question.getAnswer())) {
                this.m_rightAnswers++;
            }
        }

        System.out.print("Вы ответили на " + this.m_rightAnswers);

        if (this.m_rightAnswers == 1)
            System.out.println(" вопрос.");
        else if (this.m_rightAnswers > 1 && this.m_rightAnswers < 5)
            System.out.println(" вопроса.");
        else
            System.out.println(" вопросов.");

        printResult();
    }

    private void printResult() {
        System.out.print("Ваша оценка за экзамен: ");

        switch (this.m_rightAnswers) {
            case 0:
            case 1:
            case 2:
                System.out.println(2);
                break;
            default:
                System.out.println(this.m_rightAnswers);
                break;
        }
    }

    @Override
    void printInfo() {
        System.out.println("ФИО: " + this.m_name + "\nLogin: " + this.m_login + "\nPassword: " + this.m_password);
    }

    @Override
    void consoleInterface() {
        if (this.isLogin)
            System.out.println("Добро пожаловать в главное меню " + this.m_name + ".");

        while (this.isLogin) {
            printMainConsoleMenu();
            printYourChoice();

            int value = m_in.nextInt();

            switch (value) {
                case 1:
                    if (!this.m_isPassed) {
                        passExam();
                        this.m_isPassed = true;
                    } else {
                        System.out.println("Вы уже сдавали экзамен. Обратитесь к преподавателю за результатами.");
                    }
                    break;
                case 2:
                    if (m_isPassed)
                        printResult();
                    else
                        System.out.println("Вы ещё не сдавали экзамен.");
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
                "[1] Сдать экзамен.\n" +
                "[2] Узнать результат.\n" +
                "[0] Покинуть учетную запись.");
    }

    private void printYourChoice() {
        System.out.print("Ваш выбор: ");
    }
}