import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

// JUnit tests created by Jordan. Covers Player, MultipleChoice, Dungeon, and Boss classes. We wanted to ensure core functionalities work as expected, including health management, answer checking, question retrieval, and boss mechanics.


public class GameTest {

    // Test Player class. Tests initialization, taking damage, and increasing max health.
    @Test
    void testPlayerInitialization() {
        Player player = new Player("TestPlayer");
        assertEquals("TestPlayer", player.getPlayerName());
        assertEquals(3, player.getHealth());
        assertTrue(player.isAlive());
        
    }

    @Test //Tests for taking damage. Handles edge cases like health not going negative.
    void testPlayerTakeDamage() {
        Player player = new Player("TestPlayer");
        player.takeDamage(1);
        assertEquals(2, player.getHealth());
        assertTrue(player.isAlive());

        player.takeDamage(2);
        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());

        // Ensure health doesn't go negative
        player.takeDamage(1);
        assertEquals(0, player.getHealth());
    }

    @Test// Handles increasing max health
    void testPlayerIncreaseMaxHealth() {
        Player player = new Player("TestPlayer");
        player.increaseMaxHealth(2);
        assertEquals(5, player.getHealth()); // Health should reset to new max
    }

    // Test MultipleChoice class (extends Questions) Very important for answer checking. 
    @Test
    void testMultipleChoiceCheckAnswerByText() {
        String[] options = {"class", "def", "struct", "object"};
        MultipleChoice mc = new MultipleChoice("What is the keyword to define a class in Java?", "class", 1, options);

        assertTrue(mc.checkAnswer("class"));
        assertTrue(mc.checkAnswer("CLASS")); // Case insensitive
        assertFalse(mc.checkAnswer("def"));
        assertFalse(mc.checkAnswer(""));
    }

    @Test // Tests answer checking by option number. This is for user convenience. Starting out answers were only being accepted by text, from this Christian made the improvement to allow number input as well.
    void testMultipleChoiceCheckAnswerByNumber() {
        String[] options = {"class", "def", "struct", "object"};
        MultipleChoice mc = new MultipleChoice("What is the keyword to define a class in Java?", "class", 1, options);

        assertTrue(mc.checkAnswer("1")); // Corresponds to "class"
        assertFalse(mc.checkAnswer("2")); // "def"
        assertFalse(mc.checkAnswer("0")); // Invalid index
        assertFalse(mc.checkAnswer("5")); // Out of bounds
        assertFalse(mc.checkAnswer("abc")); // Not a number
    }

    @Test // Tests the getOptions method. Should return the array of options provided during initialization.
    void testMultipleChoiceGetOptions() {
        String[] options = {"a", "b", "c"};
        MultipleChoice mc = new MultipleChoice("Test?", "a", 1, options);
        assertArrayEquals(options, mc.getOptions());
    }

    // Test Dungeon class. Will test initialization, question retrieval, and boss question retrieval.
    @Test
    void testDungeonInitializationEasy() {
        Dungeon dungeon = new Dungeon(1, 10);
        assertNotNull(dungeon.getNextQuestion()); // Should have questions loaded
    }

    @Test
    void testDungeonGetNextQuestion() {
        Dungeon dungeon = new Dungeon(1, 2); // Small number for testing
        Questions q1 = dungeon.getNextQuestion();
        assertNotNull(q1);
        Questions q2 = dungeon.getNextQuestion();
        assertNotNull(q2);
    }

    @Test
    void testDungeonBossQuestions() {
        Dungeon dungeon = new Dungeon(3, 10); // Hard mode for boss
        assertNotNull(dungeon.getFinalBoss());

        Questions bq = dungeon.getBossQuestion();
        assertNotNull(bq);
        // Assuming at least 4 boss questions as per code
        assertNotNull(dungeon.getBossQuestion());
        assertNotNull(dungeon.getBossQuestion());
        assertNotNull(dungeon.getBossQuestion());
    }

    // Test Boss class. Edge cases for taking damage and attacking player like ensuring health doesn't go negative.
    @Test
    void testBossInitialization() {
        Boss boss = new Boss();
        assertEquals("The Encapsulator, Lord of POJOs", boss.getName());
        assertEquals(3, boss.getHealth());
        assertEquals(3, boss.getMaxHealth());
        assertFalse(boss.isDefeated());
    }

    @Test
    void testBossTakeDamage() {
        Boss boss = new Boss();
        boss.takeDamage(1);
        assertEquals(2, boss.getHealth());
        assertFalse(boss.isDefeated());

        boss.takeDamage(2);
        assertEquals(0, boss.getHealth());
        assertTrue(boss.isDefeated());

        // Ensure health doesn't go negative
        boss.takeDamage(1);
        assertEquals(0, boss.getHealth());
    }

    @Test // Tests boss attacking player. Ensures player health decreases appropriately.
    void testBossAttacks() {
        Boss boss = new Boss();
        Player player = new Player("TestPlayer");
        boss.attacks(player);
        assertEquals(2, player.getHealth()); // Should deal 1 damage
    }

    @Test // Tests getting an attack line from the boss. Ensures the line contains the boss's name.
    void testBossGetAttackLine() {
        Boss boss = new Boss();
        String line = boss.getAttackLine();
        assertNotNull(line);
        assertTrue(line.contains(boss.getName()));
    }

    // Test Game class (partial, as full game requires input simulation)
    @Test
    void testGamePlayerNameDefault() {
        String input = "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Player player = new Player("John");
        

        assertEquals("John", player.getPlayerName());

        System.setIn(System.in);
    }
}