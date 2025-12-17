
public abstract class Questions {
    protected int difficultyLevel;
    protected String questionText;
    protected String correctAnswer;

    public Questions(String questionText, String correctAnswer, int difficultyLevel) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
    }

    public abstract boolean checkAnswer (String input);

    public String getQuestionText() {
        return questionText;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    // public abstract void applyReward(Player player);

}
