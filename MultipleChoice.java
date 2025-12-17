
// MultipleChoice question type class. Allows for expansion of different question types in the future.
public class MultipleChoice extends Questions {
    private String[] options;

    //Constructor for MultipleChoice questions, takes in question text, correct answer, difficulty level, and the list of options.
    public MultipleChoice(String questionText, String correctAnswer, int difficultyLevel, String[] options) {
        super(questionText, correctAnswer, difficultyLevel);
        this.options = options;
    }

    // The CheckAnswer method checks for both the text of the answer and the number corresponding to the choice for smoother user experience.
    @Override
    public boolean checkAnswer(String input) {
        String normalizedInput = input.trim();

        if (normalizedInput.equalsIgnoreCase(this.correctAnswer.trim())) {
            return true;
        }
       
        try{
            int choiceNumber = Integer.parseInt(normalizedInput);
            int choiceIndex = choiceNumber - 1;

            if(choiceIndex >= 0 && choiceIndex < options.length) {

                String selectedOption = this.options[choiceIndex];

                if (selectedOption.equalsIgnoreCase(this.correctAnswer.trim())) {
                    return true;
                }
            }
        } catch (NumberFormatException e){
            
        }
        return false;

    }

    // @Override Possible future reward system for answering questions correctly.
    // public void applyReward(Player player) {
    //    switch (this.getDifficultyLevel()) {
    //        case 1:
    //            player.addPoints(10);
    //            break;
    //        case 2:
    //            player.addPoints(20);
    //            break;
    //        case 3:
    //            player.addPoints(30);
    //            break;
    //        default:
    //            player.addPoints(5);
    //            break;
    //    }
    // }

    public String[] getOptions() {
        return options;
    }
    
}
