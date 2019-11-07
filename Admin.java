class Admin extends User {
    private Scanner in = new Scanner(System.in);

    private List<Question> m_questions = null;
    private List<User> m_users = null;

    Admin(List<Question> questions, List<User> users) {
        super("Admin", "admin", "admin");
        this.m_questions = questions;
        this.m_users = users;
    }

    private void firstSubMenuConsoleInterface() {
        while (true) {
            printFirstSubMenu();
            printYourChoice();

            int value = in.nextInt();

            switch (value) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    delAccount();
                    break;
                case 3:
                    findAccount();
                    break;
                case 4:
                    printAllAccounts();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный ввод. Повторите.");
                    break;
            }
        }
    }

    private void secondSubMenuConsoleInterface() {
        while (true) {
            printSecondSubMenu();
            printYourChoice();

            int value = in.nextInt();

            switch (value) {
                case 1:
                    addQuestion();
                    break;
                case 2:
                    delQuestion();
                    break;
                case 3:
                    printAllQuestions();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный ввод. Повторите.");
                    break;
            }
        }
    }

    private void addQuestion() {
        String question = "";
        String answer = "";

        boolean isSuccess = false;

        in.nextLine();

        while (!isSuccess) {
            System.out.print("Введите вопрос(новую строку обозначте \"\\n\"): ");
            question = in.nextLine();

            if (question.length() > 0){
                question = question.replace("\\n", "\n");
                isSuccess = true;
            }
            else System.out.println("Неверный ввод.");
        }
        isSuccess = false;

        while (!isSuccess) {
            System.out.print("Введите ответ на вопрос: ");
            answer = in.nextLine();

            if (answer.length() > 0)
                isSuccess = true;
        }
        this.m_questions.add(new Question(question, answer));
    }

    private void delQuestion() {
        int questionNumber = 0;
        boolean isSuccess = false;

        printAllQuestions();
        System.out.println("Для удаления вопроса, введите его номер \"x\" указанный в \"[x]\".");

        while (!isSuccess) {
            System.out.print("Номер: ");
            questionNumber = in.nextInt();

            if (questionNumber <= this.m_questions.size() && questionNumber >= 1)
                isSuccess = true;
            else
                System.out.println("Вопрос с указанным номером отсутствует. Повторите ввод.");
        }

        this.m_questions.get(questionNumber - 1).print();
        System.out.println("Вопрос найден. Удалить?\n" +
                "[1] Да.\n" +
                "[0] Вернуться.");

        while (true) {
            printYourChoice();
            int value = in.nextInt();

            switch (value) {
                case 1:
                    this.m_questions.remove(questionNumber - 1);
                    System.out.println("Вопрос успешно удален.");
                case 0:
                    return;
                default:
                    System.out.println("Неверный ввод. Повторите.");
                    break;
            }
        }
    }

    private void printAllQuestions() {
        int i = 1;

        for (Question question : this.m_questions) {
            System.out.print("[" + i++ + "]");
            question.print();
        }
    }

    private void addStudent() {
        String name = "";
        String login = "";
        String password = "";

        boolean isSuccess = false;

        String nameRegex = "[A-Z[А-Я]][a-z[а-я]]{1,20}\\s[A-Z[А-Я]][a-z[а-я]]{1,20}\\s[A-Z[А-Я]][a-z[а-я]]{1,20}";
        String loginAndPassRegex = "\\w{6,20}";

        Pattern namePattern = Pattern.compile(nameRegex);
        Matcher nameMatcher;

        Pattern loginAndPassPattern = Pattern.compile(loginAndPassRegex);
        Matcher loginAndPassMatcher;

        in.nextLine();

        while (!isSuccess) {
            System.out.print("Введите ФИО студента: ");
            name = in.nextLine();

            nameMatcher = namePattern.matcher(name);

            if (nameMatcher.find()) {
                if (name.equals(name.substring(nameMatcher.start(), nameMatcher.end())))
                    isSuccess = true;
            }
            else System.out.println("Неверный ввод.");
        }
        isSuccess = false;

        while (!isSuccess) {
            System.out.print("Введите login студента: ");
            login = in.nextLine();

            loginAndPassMatcher = loginAndPassPattern.matcher(login);

            if (loginAndPassMatcher.find())
                if (login.equals(login.substring(loginAndPassMatcher.start(), loginAndPassMatcher.end())))
                    isSuccess = true;
        }
        isSuccess = false;

        while (!isSuccess) {
            System.out.print("Введите пароль студента: ");
            password = in.nextLine();

            loginAndPassMatcher = loginAndPassPattern.matcher(password);

            if (loginAndPassMatcher.find())
                if (password.equals(password.substring(loginAndPassMatcher.start(), loginAndPassMatcher.end())))
                    isSuccess = true;
        }
        this.m_users.add(new Student(name, login, password, this.m_questions));
    }

    private void delAccount() {
        String login = "";
        boolean isSuccess = false;

        System.out.println("Для удаления учетной записи, введите название(login) учетной записи.");

        in.nextLine();

        while (true) {
            while (!isSuccess) {
                System.out.print("Login: ");
                login = in.nextLine();

                if (login.equals("admin"))
                    System.out.println("Отказано в доступе.");
                else
                    isSuccess = true;
            }
            isSuccess = false;


            for (User user : this.m_users) {
                if (user.m_login.equals(login)) {
                    System.out.println("Пользователь найден. Удалить учетную запись?\n" +
                            "[1] Да.\n" +
                            "[0] Вернуться.");

                    while (true) {
                        printYourChoice();
                        int value = in.nextInt();

                        switch (value) {
                            case 1:
                                this.m_users.remove(user);
                                System.out.println("Учетная запись успешно удалена.");
                            case 0:
                                return;
                            default:
                                System.out.println("Неверный ввод.");
                                break;
                        }
                    }
                }
            }
            System.out.println("Пользователь не найден. Повторите ввод.");
        }
    }

    private void findAccount() {
        String login = "";
        boolean isSuccess = false;

        System.out.println("Для поиска введите название(login) учетной записи.");

        in.nextLine();

        while (true) {
            while (!isSuccess) {
                System.out.print("Login: ");
                login = in.nextLine();

                if (!login.equals("admin"))
                    isSuccess = true;
                else System.out.println("Отказано в доступе.");
            }
            isSuccess = false;


            for (User user : this.m_users) {
                if (user.m_login.equals(login)) {
                    System.out.println("Пользователь найден.\n" +
                            "[1] Вывести данные учетной записи.\n" +
                            "[0] Вернуться.");

                    while (true) {
                        printYourChoice();
                        int value = in.nextInt();

                        switch (value) {
                            case 1:
                                user.printInfo();
                            case 0:
                                return;
                            default:
                                System.out.println("Неверный ввод.");
                                break;
                        }
                    }
                }
            }
            System.out.println("Пользователь не найден. Повторите ввод.");
        }
    }

    private void printAllAccounts() {
        for (User user : m_users) {
            if (user.isLogin)
                continue;
            user.printInfo();
        }
    }

    @Override
    void printInfo() {
        //...
    }

    @Override
    void consoleInterface() {
        if (this.isLogin)
            System.out.println("Добро пожаловать в главное меню " + this.m_name + ".");

        while (this.isLogin) {
            printMainConsoleMenu();
            printYourChoice();

            int value = in.nextInt();

            switch (value) {
                case 1:
                    firstSubMenuConsoleInterface();
                    break;
                case 2:
                    secondSubMenuConsoleInterface();
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
                "[3] Список всех вопросов.\n" +
                "[0] Назад.");
    }

    private void printYourChoice() {
        System.out.print("Ваш выбор: ");
    }
}