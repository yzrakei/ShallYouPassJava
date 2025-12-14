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
        easyQuestions.add(new MultipleChoice("Which is the correct declaration of an integer variable?", "int x = 10;", 1, new String[]{"int x = 10;", "x = 2", "5 = int", "int y = 5"}));
        easyQuestions.add(new MultipleChoice("Which is the correct declaration of a String variable?", "String s = \"Hello\";", 1, new String[]{"string s = \"Hello\"", "s = \"Hello\"", "String s = \"Hello\";", "\"Hello\" = str"}));
        easyQuestions.add(new MultipleChoice("Which is a valid conditional statement?", "", 1, new String[]{"", "", "", ""}));
        easyQuestions.add(new MultipleChoice("", "", 1, new String[]{"", "", "", ""}));
        easyQuestions.add(new MultipleChoice("", "", 1, new String[]{"", "", "", ""}));
        easyQuestions.add(new MultipleChoice("", "", 1, new String[]{"", "", "", ""}));
        easyQuestions.add(new MultipleChoice("", "", 1, new String[]{"", "", "", ""}));
        easyQuestions.add(new MultipleChoice("", "", 1, new String[]{"", "", "", ""}));


        //-----Medium Difficulty Questions-----
        mediumQuestions.add(new MultipleChoice("Which of the following is NOT a Java access modifier?", "protectedish", 2, new String[]{"public", "private", "protectedish", "protected"}));
        mediumQuestions.add(new MultipleChoice("What is the size of an int data type in Java?", "4 bytes", 2, new String[]{"2 bytes", "4 bytes", "8 bytes", "16 bytes"}));
        mediumQuestions.add(new MultipleChoice("Which of these is NOT a valid loop in Java?", "repeat-until loop", 2, new String[]{"for loop", "while loop", "do-while loop", "repeat-until loop"}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        mediumQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));

        //-----Hard Difficulty Questions-----
        hardQuestions.add(new MultipleChoice("What kind of loop is guaranteed to execute at least once?", "do-while loop", 3, new String[]{"for loop", "while loop", "do-while loop", "enhanced for loop"}));
        hardQuestions.add(new MultipleChoice("Interfaces in Java can contain which of the following?", "All of the above", 3, new String[]{"Abstract methods", "Default methods", "Static methods", "All of the above"}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));
        hardQuestions.add(new MultipleChoice("", "", 2, new String[]{"", "", "", ""}));

        //-----Boss Questions-----
        bossQuestions.add(new MultipleChoice("What is the output of the following code snippet?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++ + ++x);\n    }\n}", "12", 3, new String[]{"11", "12", "10", "Error"}));
        bossQuestions.add(new MultipleChoice("What is Encapsulation in Java?", "It is a process of wrapping data and methods in a single unit.", 3, new String[]{"It is a process of defining multiple interfaces.", "It is a process of creating objects from classes.", "It is a process of wrapping data and methods in a single unit.", "It is a process of establishing relationships between classes."}));
        bossQuestions.add(new MultipleChoice("Which of the following is NOT a valid Java identifier?", "3DPoint", 3, new String[]{"_myVar", "myVar2", "3DPoint", "$value"}));
        bossQuestions.add(new MultipleChoice("Which keyword is used to create a constant variable?", "final", 3, new String[]{"static", "final", "private", "protected"}));
        bossQuestions.add(new MultipleChoice("Which of the following is not an example of encapsulation in Java?", "Using static variables and methods to access data without instantiating an object.", 3, new String[]{"Using static variables and methods to access data without instantiating an object.", "Grouping related variables and methods within a class.", "Using private variables in a class and providing public methods to access them.", "Using access modifiers to control the visibility of variables and methods."}));
        bossQuestions.add(new MultipleChoice("Which keyword is used to create a getter method in Java?", "getVariableName()", 3, new String[]{"get()", "return()", "get", "getVariableName()"}));
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

        for (int i = 0; i < 100/*please change this later*/; i++) {
            if (i < sourcePool.size()){
                currentQuestions.add(sourcePool.get(i));
            } else{
                // recycles from the start if not enough questions
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