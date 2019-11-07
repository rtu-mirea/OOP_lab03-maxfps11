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

    void printQuestion() {
        System.out.println(this.m_text);
    }

    void print() {
        System.out.println("Текст вопроса: " + this.m_text + "\nОтвет: " + this.m_answer);
    }
}