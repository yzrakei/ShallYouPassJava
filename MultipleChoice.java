
public class MultipleChoice extends Question {
    private String[] options;

    public MultipleChoice(String questionText, String correctAnswer, int difficultyLevel, String[] options) {
        super(questionText, correctAnswer, difficultyLevel);
        this.options = options;
    }

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

    // @Override
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

// change to a quiz class that generates a stage and multiplechoice is a method