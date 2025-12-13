import java.util.HashMap;

public class Quiz {
    
}

class Questions {
    private HashMap<Integer, Question> questions;

    public Questions() {
        questions = new HashMap<>();
    }

    private void loadQuestions() {
        //check that hash value == difficulty level; reject if not
        questions.put(1, new Question("What is the keyword to define a class in Java?", "class", 1));
        
    }

    public Question getRandomQuestion(int difficultyLevel) {
        return questions.get());
    }
}
