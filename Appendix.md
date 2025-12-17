## Appendix: Team Evil Computer


## 1. Team Contribution


| Team Member | What They Focused On | Key Contributions |
| :--- | :--- | :--- |
| **Christian Dell'Edera** | Lead Dev (The Architect) | Designed the core structure (all the OOP classes: `Questions`, `Player`, etc.). Built the main brain of the game, `Game.java`, controlling the intro, levels, and boss fight. |
| **Rachel Lo** | Business Analyst | Took ownership of the combat side by creating the `Enemy.java` and `Boss.java` classes. Also polished the entire game script (narrative/dialogue). |
| **Jordan Lee** | Tester | Was the QA Tester. Created all the JUnit tests (`GameTest.java`) to break and fix the code. Also helped write some of the Java quiz questions. |

## 2. Concepts Applied 


* **OOP Fundamentals:** We used classes for the player, enemies, and questions.
* **Abstraction & Polymorphism:** This is the core engine. We created a master blueprint (`Questions.java`) so the game could ask any type of question (like `MultipleChoice`) without caring about the specifics. Also allows for expansion for other types of questions like codesnippet questions.
* **Encapsulation:** We kept all the important variables (like the correct answer) private, only letting other classes access them through official getter methods.
* **Finite State Machine (FSM):** The `Game.java` file runs on a State based system, transitioning between `STATE_START`, `STATE_IN_DUNGEON`, or `STATE_BOSS_FIGHT`. Allows for clarity when developing.
* **Collections:** We used lists and shuffling (`Collections.shuffle`) to make sure every dungeon run was unique and randomized.
* **Unit Testing (JUnit):** We wrote separate tests to prove the damage and answer-checking math was always right.

## 3. Challenges & Solutions


* **The Content Problem (Running Out of Questions):** We realized we needed many more questions than just the 3-room count, especially since wrong answers consume a question but don't defeat the enemy.
    * **Solution:** We dramatically **over-provisioned** the question list (`buildRun()` loop set to 100) and used a "recycling" method (modulo operator) to ensure we never ran out of content.
* **The OOP Access:** Our subclasses couldn't "see" variables in the master `Questions` class, causing errors.
    * **Solution:** We changed the variable access from `private` to `protected` in the master class, fixing the inheritance issue cleanly.
* **Difficulty Scaling:** Jumps from Easy to Medium were sudden and confusing for the player.
    * **Solution:** We added explicit constant checks (using `DIFFICULTY_MEDIUM`) in the script and inserted "Setup Wizard" dialogue to formally announce the difficulty change.
* **AI Help (Citation Note):** We used AI tools (like ChatGPT) to **format** the large pool of raw questions quickly into the correct `MultipleChoice` constructor format and to help **debug** tricky parts of the game logic.

## 4. Timeline & Collaboration


| Week | What We Focused On | Team Process |
| :--- | :--- | :--- |
| **Week 1** | Core Structure & Combat Loop | Established GitHub, built all main classes (`Player`, `Questions`), and got the basic room-to-room combat running. |
| **Week 2** | Polish, Content & Testing | Finished `Dungeon` content management, finalized `Boss`/`Enemy` combat mechanics, created the full JUnit test, and polished the user experience (like adding the correct answer feedback). |

## 5. Viability Testing


* **User Feedback:** Testers said the game was fun, but getting an answer wrong felt unfair because they didn't learn anything.
* **The Solution:** We immediately updated the code to **display the correct answer** when the player makes a mistake. This turned punishment into an educational moment.
* **Proof:** The successful completion of the JUnit test suite validates that our core combat and question systems are robust and ready for scaling.

## 6. Scalability Roadmap


* **Phase 2 (0-6 Months):** Focus on reaching 1,000 users, adding GUI/Art, and integrating the planned ad system.
* **Phase 3 (7-12 Months):** Expand to 10,000 users, implement quality-of-life features like a **Save System** and more advanced content packs (DLCs).
* **Long-Term:** Release a fully polished Mobile/PC edition with story branching, expanded Java topics, and monetization through paid editions and content packs.

## 7. Sources

* **Educational Research:** Market reports confirm the programming language learning market is expected to nearly double by 2035 and validate our target student market.
* **Technical References:** Code structure (e.g., constants for the **Finite State Machine**) was implemented using established software development patterns.
* **AI Tools:** Used to quickly format data into our `MultipleChoice` constructor and to assist with debugging complex code segments.

## 8. Reflections & Lessons Learned


| Reflection Area | The Big Takeaway | The Growth Moment |
| :--- | :--- | :--- |
| **OOP Design** | The initial choice of a base class name was critical—a simple error there cascaded through the entire project. | **Discipline:** Learned to enforce strict naming conventions and define the core abstract classes early to prevent structural ambiguity. |
| **Project Planning** | Estimating the time required for tricky logic (polymorphism, bug fixing) is harder than estimating simple feature coding. | **Mitigation:** Learned to build in significant buffer time for "critical bug fixing" beyond the initial coding estimates. |
| **Testing** | Unit testing is indispensable, especially in games where one math error can break the whole combat system. | **Quality:** The JUnit tests became the primary driver for identifying and fixing most logic errors before they ever affected the main game loop. |
| **Game Design** | Player feedback in an educational game must be immediate. | **Product Enhancement:** The final addition of showing the correct answer after a mistake transformed the game from a punitive quiz into a genuine learning tool. |
