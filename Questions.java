// Initial idea to make Questions an abstract class was done by Christian.
// We wanted to make the Questions class abstract to allow for different types of questions (like MultipleChoice, TrueFalse, etc.) to inherit from it and implement their own specific behaviors, such as how answers are checked. For this demo we only have MultipleChoice, but this structure allows for easy expansion in the future.
public abstract class Questions {
    protected int difficultyLevel;
    protected String questionText;
    protected String correctAnswer;

    // Constructor for the Questions class, initializes the question text, correct answer, and difficulty level.
    public Questions(String questionText, String correctAnswer, int difficultyLevel) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.difficultyLevel = difficultyLevel;
    }
    // Abstract method to be implemented by subclasses to check if the provided answer is correct.
    public abstract boolean checkAnswer (String input);

    //Getter methods for question properties.
    public String getQuestionText() {
        return questionText;
    }

    //
    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    // public abstract void applyReward(Player player); // Possible future reward system for answering questions correctly.

}
