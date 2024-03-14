package game;

import lombok.Getter;
import java.util.*;

import static game.consts.Consts.*;

@Getter
public class Quest {
    private Map<String, String> questions;
    private String nextQuestion;
    private List<String> currentOptions;
    private boolean defeated;
    private boolean victory;

    public Quest() {
        initializeQuestions();
        startGame();
    }

    public void initializeQuestions() {
        questions = new HashMap<>();
        questions.put(GAME_START, "Вы потеряли память. Примите вызов НЛО?");
        questions.put(ACCEPT_UFO_CALL, "Вы приняли вызов НЛО. Вы собираетесь подняться к капитану?");
        questions.put(CLIMB_TO_CAPTAIN, "Вы поднялись к капитану. Кто вы?");
        questions.put(DECLINE_UFO_CALL, "Вы отклонили вызов НЛО...");
        questions.put(REFUSE_CAPTAIN, "Вы отказались подняться к капитану...");
        questions.put(TELL_LIE, "Твоя ложь раскрыта...");
        questions.put(TELL_TRUTH, "Тебя вернули домой!");

    }
    public String startGame() {
        nextQuestion = GAME_START;
        return questions.get(nextQuestion);
    }

    public List<String> getCurrentOptions() {
        currentOptions = new ArrayList<>();

        switch (nextQuestion) {
            case GAME_START :
                currentOptions.add("Принять вызов НЛО");
                currentOptions.add("Отклонить вызов НЛО");
                break;
            case ACCEPT_UFO_CALL :
                currentOptions.add("Подняться к капитану");
                currentOptions.add("Отказаться подниматься к капитану");
                break;
            case CLIMB_TO_CAPTAIN :
                currentOptions.add("Рассказать правду о себе");
                currentOptions.add("Солгать о себе");
                break;
        }
        return currentOptions;
    }


    public String processAnswer(String answer) {
        if (answer == null) {
            return questions.get(GAME_START);
        }
        switch (nextQuestion) {
            case GAME_START:
                if (answer.equalsIgnoreCase("Принять вызов НЛО")) {
                    nextQuestion = ACCEPT_UFO_CALL;
                } else {
                    nextQuestion = DECLINE_UFO_CALL;
                    defeated = true;
                }
                break;
            case ACCEPT_UFO_CALL:
                if (answer.equalsIgnoreCase("Подняться к капитану")) {
                    nextQuestion = CLIMB_TO_CAPTAIN;
                } else {
                    nextQuestion = REFUSE_CAPTAIN;
                    defeated = true;
                }
                break;
            case CLIMB_TO_CAPTAIN:
                if (answer.equalsIgnoreCase("Рассказать правду о себе")) {
                    nextQuestion = TELL_TRUTH;
                    victory = true;
                } else {
                    nextQuestion = TELL_LIE;
                    defeated = true;
                }
                break;
        }
        return questions.get(nextQuestion);
    }
    public void resetGame() {
        nextQuestion = GAME_START;
        defeated = false;
        victory = false;
    }
    public boolean isGameOver() {
        return nextQuestion.equals(TELL_TRUTH)
                || nextQuestion.equals(DECLINE_UFO_CALL)
                || nextQuestion.equals(REFUSE_CAPTAIN)
                || nextQuestion.equals(TELL_LIE);
    }
}
