import game.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestTest {
    private Quest quest;
    @BeforeEach
    public void setUp() {
        quest = new Quest();
    }
    @Test
    public void startGameTest() {
        String message = quest.startGame();
        assertEquals("Вы потеряли память. Примите вызов НЛО?", message);
    }
    @Test
    public void acceptUFOCallTest() {
        quest.startGame();
        String result = quest.processAnswer("Принять вызов НЛО");
        assertEquals("Вы приняли вызов НЛО. Вы собираетесь подняться к капитану?", result);
    }
    @Test
    public void climbToCaptainTest() {
        quest.startGame();
        quest.processAnswer("Принять вызов НЛО");
        String result = quest.processAnswer("Подняться к капитану");
        assertEquals("Вы поднялись к капитану. Кто вы?", result);
    }
    @Test
    public void tellTruthTest() {
        quest.startGame();
        quest.processAnswer("Принять вызов НЛО");
        quest.processAnswer("Подняться к капитану");
        String result = quest.processAnswer("Рассказать правду о себе");
        assertEquals("Тебя вернули домой!", result);
        assertTrue(quest.isVictory());
    }
    @Test
    public void declineUFOCallTest() {
        quest.startGame();
        String result = quest.processAnswer("Отклонить вызов НЛО");
        assertEquals("Вы отклонили вызов НЛО...", result);
        assertTrue(quest.isDefeated());
    }
    @Test
    public void refuseCaptainTest() {
        quest.startGame();
        quest.processAnswer("Принять вызов НЛО");
        String result = quest.processAnswer("Отказаться подниматься к капитану");
        assertEquals("Вы отказались подняться к капитану...", result);
        assertTrue(quest.isDefeated());
    }
    @Test
    public void tellLieTest() {
        quest.startGame();
        quest.processAnswer("Принять вызов НЛО");
        quest.processAnswer("Подняться к капитану");
        String result = quest.processAnswer("Солгать о себе");
        assertEquals("Твоя ложь раскрыта...", result);
        assertTrue(quest.isDefeated());
    }
    @Test
    public void testResetGame() {
        quest.startGame();
        quest.processAnswer("Отклонить вызов НЛО");
        quest.resetGame();
        assertFalse(quest.isDefeated());
        assertFalse(quest.isVictory());
        assertEquals("Вы потеряли память. Примите вызов НЛО?", quest.startGame());
    }
}
