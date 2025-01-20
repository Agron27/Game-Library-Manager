import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void testGameConstructorAndGetters() {
        String testName = "Test Game";
        String testPublisher = "Test Publisher";
        int testYear = 2024;
        Genre testGenre = Genre.ACTION;

        Game game = new Game(testName, testPublisher, testYear, testGenre);

        assertEquals(testName, game.getName());
        assertEquals(testPublisher, game.getPublisher());
        assertEquals(testYear, game.getYear());
        assertEquals(testGenre, game.getGenre());
    }

    @Test
    public void testSetPublisher() {
        Game game = new Game("Test Game", "Test Publisher", 2024, Genre.ACTION);

        String newPublisher = "New Publisher";
        game.setPublisher(newPublisher);

        assertEquals(newPublisher, game.getPublisher());
    }
}