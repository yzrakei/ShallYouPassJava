import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// We decided that using an ArrayList would be the most efficient way to store and manage the questions for the dungeon. Based on the difficulty level selected by the player at the start of the game, we can easily shuffle and select questions from the appropriate list. This allows for dynamic question selection and ensures that players face questions that match their chosen difficulty level as they progress through the dungeon.
public class Dungeon {

    private List<Questions> easyQuestions;
    private List<Questions> mediumQuestions;
    private List<Questions> hardQuestions;
    private List<Questions> bossQuestions;

    private List<Questions> currentQuestions;

    

    private Boss finalBoss;
    // Constructor for Dungeon class, initializes question lists and loads questions based on difficulty level.
    public Dungeon(int difficulty, int totalRooms) {
    
       
        this.easyQuestions = new ArrayList<>();
        this.mediumQuestions = new ArrayList<>();
        this.hardQuestions = new ArrayList<>();
        this.currentQuestions = new ArrayList<>();
        this.bossQuestions = new ArrayList<>();

        loadQuestions();

        // Randomly shuffle and select questions based on difficulty. This ensures a varied experience each time the dungeon is created akin to a roguelike game.

        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);

        buildRun(difficulty);

        if(difficulty == 3) {
            this.finalBoss = new Boss();
        }
    }
    //Used AI to efficiently load questions we found or created.
    private void loadQuestions() {
        //-----Easy Difficuly Questions-----
        easyQuestions.add(new MultipleChoice("What is the keyword to define a class in Java?", "class", 1, new String[]{"class", "def", "struct", "object"}));
        easyQuestions.add(new MultipleChoice("What data type is used to represent true/false values in Java?", "boolean", 3, new String[]{"int", "String", "boolean", "boolean"}));
        easyQuestions.add(new MultipleChoice("Which is the correct declaration of an integer variable?", "int x = 10;", 1, new String[]{"int x = 10;", "x = 2", "5 = int", "int y = 5"}));
        easyQuestions.add(new MultipleChoice("Which is the correct declaration of a String variable?", "String s = \"Hello\";", 3, new String[]{"string s = \"Hello\"", "s = \"Hello\"", "String s = \"Hello\";", "\"Hello\" = str"}));
        easyQuestions.add(new MultipleChoice("Which is a valid conditional statement in Java?", "if (x > 5) {}", 1, new String[]{"if (x > 5) {}", "when (x > 5) {}", "check (x > 5) {}", "case (x > 5) {}"}));  
        easyQuestions.add(new MultipleChoice("What is the correct syntax to output \"Hello, World!\" in Java?", "System.out.println(\"Hello, World!\");", 3, new String[]{"print(\"Hello, World!\");", "echo \"Hello, World!\";", "System.out.println(\"Hello, World!\");", "cout << \"Hello, World!\";"}));
        easyQuestions.add(new MultipleChoice("How do you insert comments in Java?", "// This is a comment", 1, new String[]{"// This is a comment", "/* This is a comment */", "# This is a comment", "<!-- This is a comment -->"}));
        easyQuestions.add(new MultipleChoice("What data type is used for storing whole numbers in Java?", "int", 1, new String[]{"int", "char", "String", "boolean"}));
        easyQuestions.add(new MultipleChoice("How do you create a variable with the number 2.84? ","double num = 2.84;", 1, new String[]{"double num = 2.84;", "int num = 2.84;", "float num = 2.84;", "num = 2.84;"}));
        easyQuestions.add(new MultipleChoice("Which method can be used to find the length of a String in Java?", "length()", 1, new String[]{"length()", "size()", "count()", "getLength()"}));
        easyQuestions.add(new MultipleChoice("Which symbol is used to terminate statements in Java?", ";", 1, new String[]{";", ".", ":", ","}));
        easyQuestions.add(new MultipleChoice("What is the correct way to declare an array in Java?", "int[] arr = new int[5];", 2, new String[]{"int arr[] = new int[5];", "int[] arr = new int[5];", "array arr = new int[5];", "int arr = new int[5];"}));
        //-----Medium Difficulty Questions-----
        mediumQuestions.add(new MultipleChoice("Which of the following is NOT a Java access modifier?", "protectedish", 3, new String[]{"public", "private", "protectedish", "protected"}));
        mediumQuestions.add(new MultipleChoice("What is the size of an int data type in Java?", "4 bytes", 2, new String[]{"2 bytes", "4 bytes", "8 bytes", "16 bytes"}));
        mediumQuestions.add(new MultipleChoice("Which of these is NOT a valid loop in Java?", "repeat-until loop", 4, new String[]{"for loop", "while loop", "do-while loop", "repeat-until loop"}));
        mediumQuestions.add(new MultipleChoice("Which of the following statements are true for inheritance in Java?", "Final classes in java cannot be inherited", 1, new String[]{"Final classes in java cannot be inherited", "All classes can be inherited", "Inheritance is not supported in Java", "Only abstract classes can be inherited"}));
        mediumQuestions.add(new MultipleChoice("What is the proper operator for adding two values together?", "+", 1, new String[]{"+", "-", "*", "/"}));
        mediumQuestions.add(new MultipleChoice("Which loop is best when the number of iterations is known?", "for loop", 1, new String[]{"for loop", "while loop", "do-while loop", "fruit loop"}));
        mediumQuestions.add(new MultipleChoice("Which exception is thrown when dividing by zero?", "ArithmeticException", 3, new String[]{"NullPointerException", "ArrayIndexOutOfBoundsException", "ArithmeticException", "ThatsNotAllowedException"}));
        mediumQuestions.add(new MultipleChoice("Which collection is does not allow duplicate elements?", "Set", 3, new String[]{"List", "Map", "Set", "Queue "}));
        mediumQuestions.add(new MultipleChoice("Which keyword is used to inherit a class in Java?", "extends", 1, new String[]{"extends", "implements", "inherits", "super"}));
        mediumQuestions.add(new MultipleChoice("Which of these is NOT one of the Four Pillars of OOP?", "Jafafication", 4, new String[]{"Encapsulation", "Inheritance", "Polymorphism", "Jafafication"}));
        mediumQuestions.add(new MultipleChoice("What is the output? System.out.print(5 + 3 * 2);", "11", 2, new String[]{"16", "11", "21", "Error"}));
        mediumQuestions.add(new MultipleChoice("Which of these is NOT a primitive data type in Java?", "String", 1, new String[]{"int", "boolean", "char", "String"}));

        //-----Hard Difficulty Questions-----
        hardQuestions.add(new MultipleChoice("What kind of loop is guaranteed to execute at least once?", "do-while loop", 3, new String[]{"for loop", "while loop", "do-while loop", "enhanced for loop"}));
        hardQuestions.add(new MultipleChoice("Interfaces in Java can contain which of the following?", "All of the above", 4, new String[]{"Abstract methods", "Default methods", "Static methods", "All of the above"}));
        hardQuestions.add(new MultipleChoice("Output? Integer a=128, b=128; a==b", "false", 2, new String[]{"true", "false", "i dont know", "i wanna go home"}));
        hardQuestions.add(new MultipleChoice("Output? System.out.print(-0.0 == 0.0)", "true", 1, new String[]{"true", "false", "NaN", "Compilation Error"}));
        hardQuestions.add(new MultipleChoice("How many types of primitive data in Java are present?", "8", 2, new String[]{"7", "8", "10", "5"}));
        hardQuestions.add(new MultipleChoice("Which access is the most restrictive?", "private", 4, new String[]{"public", "protected", "default", "private"}));
        hardQuestions.add(new MultipleChoice("Which is checked at compile time? (Must be caught/declared)", "IOException", 1, new String[]{"IOException", "NullPointerException", "ArithmeticException", "Error"}));
        hardQuestions.add(new MultipleChoice("Which is true about Hashmap?","Hashmap keys must be unique", 1, new String[]{"Hashmap keys must be unique", "Hashmap allows duplicate keys", "Hashmap is synchronized", "Hashmap maintains insertion order"}));
        hardQuestions.add(new MultipleChoice("Which of these is NOT an error in Java?", "Logical Error", 3, new String[]{"Syntax Error", "Runtime Error", "Logical Error", "Compilation Error"}));
        hardQuestions.add(new MultipleChoice("What is the default value of a boolean variable in Java?", "false", 2, new String[]{"true", "false", "0", "null"}));
        hardQuestions.add (new MultipleChoice("Why are getters and setters important in OOP?","They provide controlled access to class attributes", 2, new String[]{"They provide controlled access to class attributes", "They make code run faster", "They are not important", "They allow direct access to private variables"}));
        hardQuestions.add(new MultipleChoice("What does the 'super' keyword do in Java?", "Calls the parent class constructor or method", 1, new String[]{"Calls the parent class constructor or method", "Creates a new object", "Accesses static variables", "Defines a new class"}));
        hardQuestions.add(new MultipleChoice("Which of the following correctly describes method overloading?", "Multiple methods with same name but different parameters", 2, new String[]{"Methods with different names and same parameters", "Multiple methods with same name but different parameters", "Methods that override parent class methods", "Static methods with different return types"}));
        hardQuestions.add(new MultipleChoice("What is the purpose of the 'instanceof' operator?", "To check if an object is an instance of a class", 3, new String[]{"To create a new instance", "To compare two objects for equality", "To check if an object is an instance of a class", "To cast an object to a different type"}));
        hardQuestions.add(new MultipleChoice("Which collection maintains insertion order and allows duplicates?", "ArrayList", 1, new String[]{"ArrayList", "HashSet", "HashMap", "TreeSet"}));
        hardQuestions.add(new MultipleChoice("What is the output? int[] arr = {1,2,3}; System.out.print(arr[5]);", "ArrayIndexOutOfBoundsException", 4, new String[]{"null", "0", "IndexError", "ArrayIndexOutOfBoundsException"}));
        hardQuestions.add(new MultipleChoice("Which statement about abstract classes is true?", "Abstract classes cannot be instantiated", 2, new String[]{"Abstract classes must have abstract methods", "Abstract classes cannot be instantiated", "All methods in abstract classes must be abstract", "Abstract classes cannot have constructors"}));
        hardQuestions.add(new MultipleChoice("What is the difference between '==' and '.equals()' for Strings?", "'==' compares references, .equals() compares content", 3, new String[]{"Both do the same thing", "'==' is faster", "'==' compares references, .equals() compares content", ".equals() only works with Strings"}));
        hardQuestions.add(new MultipleChoice("Which is a functional interface in Java?", "An interface with exactly one abstract method", 1, new String[]{"An interface with exactly one abstract method", "An interface with multiple abstract methods", "An interface that extends another interface", "An interface implemented by a class"}));
        hardQuestions.add(new MultipleChoice("What is abstraction in OOP?", "Hiding complex implementation details and showing only essential features", 2, new String[]{"Hiding complex implementation details and showing only essential features", "Creating multiple methods with the same name", "Deriving new classes from existing ones", "Wrapping data and methods in a single unit"}));
        hardQuestions.add(new MultipleChoice("What is the difference between a checked and unchecked exception?", "Checked exceptions must be declared or caught, unchecked do not", 3, new String[]{"Checked exceptions are more severe", "Unchecked exceptions must be declared or caught, checked do not", "Checked exceptions must be declared or caught, unchecked do not", "There is no difference"}));
        hardQuestions.add(new MultipleChoice("What is the output? String s1 = new String(\"test\"); String s2 = \"test\"; System.out.print(s1 == s2);", "false", 4, new String[]{"true", "false", "Compilation Error", "Runtime Error"}));


        //-----Boss Questions-----
        bossQuestions.add(new MultipleChoice("What is the output of the following code snippet?\n\npublic class Test {\n    public static void main(String[] args) {\n        int x = 5;\n        System.out.println(x++ + ++x);\n    }\n}", "12", 2, new String[]{"11", "12", "10", "Error"}));
        bossQuestions.add(new MultipleChoice("What is Encapsulation in Java?", "It is a process of wrapping data and methods in a single unit.", 3, new String[]{"It is a process of defining multiple interfaces.", "It is a process of creating objects from classes.", "It is a process of wrapping data and methods in a single unit.", "It is a process of establishing relationships between classes."}));
        bossQuestions.add(new MultipleChoice("Which of the following is NOT a valid Java identifier?", "3DPoint", 1, new String[]{"3DPoint", "_myVar", "myVar2", "$value"}));
        bossQuestions.add(new MultipleChoice("Which keyword is used to create a constant variable?", "final", 2, new String[]{"static", "final", "private", "protected"}));
        bossQuestions.add(new MultipleChoice("Which of the following is not an example of encapsulation in Java?", "Using static variables and methods to access data without instantiating an object.", 1, new String[]{"Using static variables and methods to access data without instantiating an object.", "Grouping related variables and methods within a class.", "Using private variables in a class and providing public methods to access them.", "Using access modifiers to control the visibility of variables and methods."}));
        bossQuestions.add(new MultipleChoice("Which keyword is used to create a getter method in Java?", "getVariableName()", 4, new String[]{"get()", "return()", "get", "getVariableName()"}));
        bossQuestions.add(new MultipleChoice("What will be the result of the following code?\n\nString str = null;\nSystem.out.println(str.length());", "Throws NullPointerException", 3, new String[]{"Prints 0", "Prints null", "Throws NullPointerException", "Compilation error"}));
        bossQuestions.add(new MultipleChoice("Which of these keywords is used to prevent a method from being overridden in Java?", "final", 2, new String[]{"static", "final", "private", "protected"}));
        }
    // Builds the current run of questions based on the selected difficulty level. Choosing option 1 causes the player to face hard difficulty enemies, while Option 2 leads to easy difficulty enemies, then increases in difficulty as the player progresses.
    private void buildRun(int difficulty) {
        List<Questions> sourcePool;

        if (difficulty == 1) {
            sourcePool = easyQuestions;
        } else if (difficulty == 2) {
            sourcePool = mediumQuestions;
        } else {
            sourcePool = hardQuestions;
        }
        // Fills the currentQuestions list with a total of 100 questions, recycling from the source pool if necessary. This is to ensure that the dungeon has enough questions for the player to face as they progress through the rooms.
        for (int i = 0; i < 100; i++) {
            if (i < sourcePool.size()){
                currentQuestions.add(sourcePool.get(i)); 
            } else{
                // recycles from the start if not enough questions
                currentQuestions.add(sourcePool.get(i % sourcePool.size()));
            }
        }
     }
    // Retrieves the next question from the currentQuestions list. It then removes the question from the list to avoid repetition. Very important to prevent the same question from appearing multiple times in a single run.
     public Questions getNextQuestion(){
        if (!currentQuestions.isEmpty()) {
            return currentQuestions.remove(0);
        }
        return null; // No more questions
     }
     // Retrieves the final boss of the dungeon. When in the Boss fight state, this method is called to get the boss instance for the final challenge.
     public Boss getFinalBoss(){
        return finalBoss;
     }
    // Retrieves the next boss question from the bossQuestions list. Similar to getNextQuestion, it removes the question from the list to ensure uniqueness.
     public Questions getBossQuestion() {
        if (!bossQuestions.isEmpty()) {
            return bossQuestions.remove(0);
        }
        return null; // No more boss questions
     }
    
}