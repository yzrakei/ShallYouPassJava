import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Dungeon {

    private List<Questions> easyQuestions;
    private List<Questions> mediumQuestions;
    private List<Questions> hardQuestions;
    private List<Questions> bossQuestions;

    private List<Questions> currentQuestions;

    private final int totalRooms;

    private Boss finalBoss;

    public Dungeon(int difficulty, int totalRooms) {
        this.totalRooms = totalRooms;
       
        this.easyQuestions = new ArrayList<>();
        this.mediumQuestions = new ArrayList<>();
        this.hardQuestions = new ArrayList<>();
        this.currentQuestions = new ArrayList<>();
        this.bossQuestions = new ArrayList<>();

        loadQuestions();

        // Randomly shuffle and select questions based on difficulty

        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);

        buildRun(difficulty);

        if(difficulty == 3) {
            this.finalBoss = new Boss();
        }
    }

    private void loadQuestions() {
        //-----Easy Difficuly Questions-----
        easyQuestions.add(new MultipleChoice("What is the keyword to define a class in Java?", "class", 1, new String[]{"class", "def", "struct", "object"}));
        easyQuestions.add(new MultipleChoice("What data type is used to represent true/false values in Java?", "boolean", 1, new String[]{"int", "String", "boolean", "char"}));

        //-----Medium Difficulty Questions-----
        mediumQuestions.add(new MultipleChoice("Which of the following is NOT a Java access modifier?", "protectedish", 2, new String[]{"public", "private", "protectedish", "protected"}));
        mediumQuestions.add(new MultipleChoice("What is the size of an int data type in Java?", "4 bytes", 2, new String[]{"2 bytes", "4 bytes", "8 bytes", "16 bytes"}));

        //-----Hard Difficulty Questions-----
        hardQuestions.add(new MultipleChoice("What kind of loop is guaranteed to execute at least once?", "do-while loop", 3, new String[]{"for loop", "while loop", "do-while loop", "enhanced for loop"}));
        hardQuestions.add(new MultipleChoice("Interfaces in Java can contain which of the following?", "All of the above", 3, new String[]{"Abstract methods", "Default methods", "Static methods", "All of the above"}));

        //-----Boss Questions-----
        bossQuestions.add(new MultipleChoice("What is the output of the following code snippet?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++ + ++x);\n    }\n}", "12", 3, new String[]{"11", "12", "10", "Error"}));
        bossQuestions.add(new MultipleChoice("Which of the following is NOT a valid Java identifier?", "3DPoint", 3, new String[]{"_myVar", "myVar2", "3DPoint", "$value"}));
        bossQuestions.add(new MultipleChoice("What will be the result of the following code?\n\nString str = null;\nSystem.out.println(str.length());", "Throws NullPointerException", 3, new String[]{"Prints 0", "Prints null", "Throws NullPointerException", "Compilation error"}));
        bossQuestions.add(new MultipleChoice("Which of these keywords is used to prevent a method from being overridden in Java?", "final", 3, new String[]{"static", "final", "private", "protected"}));
    }

    private void buildRun(int difficulty) {
        List<Questions> sourcePool;

        if (difficulty == 1) {
            sourcePool = easyQuestions;
        } else if (difficulty == 2) {
            sourcePool = mediumQuestions;
        } else {
            sourcePool = hardQuestions;
        }

        for (int i = 0; i < totalRooms; i++) {
            if (i < sourcePool.size()){
                currentQuestions.add(sourcePool.get(i));
            } else{
                // If not enough questions, recycle from the start
                currentQuestions.add(sourcePool.get(i % sourcePool.size()));
            }
        }
     }

     public Questions getNextQuestion(){
        if (!currentQuestions.isEmpty()) {
            return currentQuestions.remove(0);
        }
        return null; // No more questions
     }

     public Boss getFinalBoss(){
        return finalBoss;
     }

     public Questions getBossQuestion() {
        if (!bossQuestions.isEmpty()) {
            return bossQuestions.remove(0);
        }
        return null; // No more boss questions
     }
    
}