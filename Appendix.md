## Appendix: Shall You Pass Java?

## 1. Team Contribution


| Team Member | Proposal Role | Key Coding & Testing Contributions | Estimated % of Codebase |
| :--- | :--- | :--- | :--- |
| **Christian Dell'Edera** | Lead Dev & Researcher | Designed and implemented the core Object-Oriented structure (`Questions`, `MultipleChoice`, `Player`, `Boss`, `Enemy`). Coded the Finite State Machine in `Game.java` to control flow (Intro, Dungeon, Boss Fight). | 45% |
| **Jordan Lee** | Tester| Developed the JUnit Test system (`GameTest.java`). Ensured proper functionality of `checkAnswer` and combat mechanics. Contributed to question loading in `Dungeon.java`. | 22% |
| **Rachel Lo** | Business Analyst | Refined the baseline code and contributed to the design and implementation of the `Dungeon.java` content  management system (shuffling, run building). Developed the Boss and Enemy mechanics and dialogue. | 33% |

## 2. Java Concepts Applied


| Concept | Application in Code |
| :--- | :--- |
| **Object-Oriented Programming (OOP)** | The entire game is built around classes representing real-world entities (`Player`, `Boss`, `Question`). |
| **Abstraction & Inheritance** | `Questions.java` is an `abstract` base class that defines the core contract (`checkAnswer`). `MultipleChoice.java` extends this base class. [Image of class inheritance diagram in Java] |
| **Polymorphism** | The `Game.java` and `Dungeon.java` classes treat all questions generically as `Questions` objects, allowing them to call `currentQuestion.checkAnswer()` regardless of the specific subclass type. |
| **Encapsulation** | Attributes are protected/private (`protected String correctAnswer` in `Questions.java`) and accessed only through public getters (`getQuestionText()`). |
| **Finite State Machine** | The `handleGameState()` method in `Game.java` uses constants (`STATE_START`, `STATE_IN_DUNGEON`, etc.) to manage complex game flow logic. |
| **Collections Framework** | `Dungeon.java` uses `ArrayList` (implemented as `List<Questions>`) and `Collections.shuffle()` to manage and randomize question pools. |
| **Input/Output** | The standard `Scanner` class (`java.util.Scanner`) is used for reading user input and answers. |

## 3. Challenges & Solutions

| Challenge | Impact on MVP Development | Solution Implemented |
| :--- | :--- | :--- |
| **Inconsistent Naming** | Frequent `Type Mismatch` errors across `Game.java` and `Dungeon.java` due to mixing `Question` (singular) and `Questions` (plural) references. | Global search-and-replace to enforce consistency, ultimately using the plural `Questions` as the base class to match `Dungeon.java` list declarations. |
| **Variable Question Consumption** | The initial `Dungeon` design prepared a fixed number of questions, which was insufficient because incorrect answers consume a question but do not defeat the enemy. | **Over-Provisioning:** The `buildRun()` loop size was increased to `100` with wrap-around logic (`i % size`). This ensures the game never runs out of content before the player is defeated or victorious. |
| **Access Modifiers (Visibility)** | `The field Question.correctAnswer is not visible` error in `MultipleChoice.java`. The subclass could not access inherited fields. | Changed access modifiers in `Questions.java` from `private` to `protected` for core fields (`correctAnswer`, `questionText`), enabling safe inheritance. |
| **Difficulty Logic & Progression** | Difficulty logic was confusing, especially advancing from Medium to Hard. | Implemented explicit conditional checks using constants (`if (currentDifficulty == DIFFICULTY_MEDIUM)`) in `handleGameState()` for clean, traceable progression. |

## 4. Project Timeline

The project followed the two-week schedule outlined in the proposal, with minor adjustments to dedicate more time to debugging the OOP structure.

| Week | Planned Activities | Actual Accomplishments |
| :--- | :--- | :--- |
| **Week 1** | Start GitHub Repo + test GUI; Start Coding (x2) | Established core OOP structure (`Questions`, `MultipleChoice`, `Player`). Implemented `Game.java` loop and states. Completed initial question loading and combat simulation (MVP core). |
| **Week 2** | Finish GUI, connect code, testing; Coding + Testing (x2) | Fully implemented `Dungeon` content management (shuffling, run building). Developed `Boss` and `Enemy` classes. Completed `GameTest.java` (JUnit). Polished game flow, story progression, and feedback (displaying correct answer on error). |

## 5. How We Collaborated

The team employed a structured collaboration model:

* **Version Control:** GitHub was the single source of truth for the codebase, facilitating parallel development and merging changes from all team members.
* **Role Specialization:** Development was split by class responsibility (Lead Dev handled structure; Tester focused on `GameTest.java` and validation).
* **Daily Sync-ups:** Short, informal meetings (digital) were held to identify the daily coding goal, flag structural errors, and ensure all changes merged cleanly into the main branch.
* **Testing-Driven Debugging:** The Tester (Rachel) created Unit Tests before the full game was complete, allowing the Lead Dev and Primary Role to catch errors related to polymorphism and combat math immediately, rather than waiting for a full game run.

## 6. Viability Testing (New Section)

Viability testing validated the *fun* and *educational* claims of the MVP.

| Aspect | Validation Process and Findings | Changes Made |
| :--- | :--- | :--- |
| **Educational Feedback** | User testers (CS peers) noted that the game was too unforgiving when an answer was wrong. | **Change:** Modified `Game.java` in both `processRoom()` and `bossFight()` to explicitly display the correct answer immediately after an incorrect input (e.g., "Incorrect! The answer was: [blank]"). |
| **Fun/Engagement** | Testers found the difficulty increase too sudden and lacked clear feedback. | **Change:** Added explicit text using the Setup Wizard character in `handleGameState()` to announce the transition to Medium and Hard difficulty levels, making the progression feel earned. |
| **Usability/Clarity** | Early testing revealed confusion over which questions related to the options displayed. | **Change:** Introduced the `selectMCQuestion()` helper method in `Game.java` and ensured question text and options were displayed together clearly. |
| **Proof of Viability** | The successful execution of the full `GameTest.java` suite (especially the `testMultipleChoiceCheckAnswerByNumber` and `testDungeonBossQuestions` tests) serves as **proof** that the core features promised in the MVP (basic level, question system, and combat mechanics) are fully implemented and functional, supporting the project's viability claims. | N/A |

## 7. Citations & External Resources (REQUIRED)

All references used for design, research, and coding assistance:

* Game-based Learning Market Size, Share, Growth, Latest Trends.
* Verified Market Research. Online coding learning platform market size, scope and forecast (Report No. 449196).
* Hanson, M. College enrollment & student demographic statistics.
* **Stack Overflow / Tutorials:** Referenced for troubleshooting `Scanner` input validation, parsing string to integer (`NumberFormatException`), and implementing the `instanceof` operator for downcasting polymorphic objects.
* **AI Models (Claude/ChatGPT):** Used to efficiently generate and verify the volume of Java-specific questions for the `Dungeon.loadQuestions()` method.

## 8. Reflections & Lessons Learned

| Reflection Area | Lesson Learned | Growth Achieved |
| :--- | :--- | :--- |
| **OOP Design** | The initial choice of a base class name (`Question` vs. `Questions`) has downstream effects across the entire project, leading to cascading errors (`Type Mismatch`). | **Discipline:** Learned to enforce strict naming conventions and define the core abstract classes early to prevent structural ambiguity. |
| **Project Planning** | Estimating the time required for non-trivial tasks (like debugging polymorphism and fixing inconsistent class access) is harder than estimating code volume. | **Mitigation:** Built in buffer time for "critical bug fixing" beyond the initial coding estimates. |
| **Testing** | Unit testing is indispensable, particularly in games with complex internal math (Enemy HP, damage calculation, complex answer checking). | **Quality:** The JUnit tests in `GameTest.java` became the primary driver for identifying and fixing most logic errors before they affected the main game loop. |
| **Game Design** | Player feedback in an educational game must be immediate and corrective. Simply punishing a wrong answer is insufficient. | **Product Enhancement:** The final addition of showing the correct answer after a mistake transformed the game from a punitive quiz into a genuine learning tool. |

 GUI; Start Coding (x2) | Established core OOP structure (`Questions`, `MultipleChoice`, `Player`). Implemented `Game.java` loop and states. Completed initial question loading and combat simulation (MVP core). |
| **Week
