package game;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static game.consts.Consts.MESSAGE;
import static game.consts.Consts.OPTIONS;

@WebServlet("/game")
public class QuestServlet extends HttpServlet {
    private Quest quest;

    @Override
    public void init() throws ServletException {
        super.init();
        quest = new Quest();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String initialMessage = quest.startGame();
        request.setAttribute(MESSAGE, initialMessage);
        request.setAttribute(OPTIONS, quest.getCurrentOptions());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String answer = request.getParameter("answer");

        if ("true".equals(request.getParameter("reset"))) {
            quest = new Quest();
            answer = null;
        }

        if (answer != null) {
            String nextQuestionOrResult = quest.processAnswer(answer);
            request.setAttribute(MESSAGE, nextQuestionOrResult);
            List<String> options = quest.getCurrentOptions();
            request.setAttribute(OPTIONS, options);

            if (quest.isGameOver()) {
                request.setAttribute("showRestartButton", true);
                if (quest.isDefeated()) {
                    request.setAttribute("defeatImage", request.getContextPath() + "/images/defeat.jpg");
                    quest.resetGame();
                } else if (quest.isVictory()) {
                    request.setAttribute("victoryImage", request.getContextPath() + "/images/win.jpg");
                    quest.resetGame();
                }
            }
        } else {
            request.setAttribute(MESSAGE, "Вы потеряли память. Примите вызов НЛО?");
            List<String> options = quest.getCurrentOptions();
            request.setAttribute(OPTIONS, options);
        }

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}
