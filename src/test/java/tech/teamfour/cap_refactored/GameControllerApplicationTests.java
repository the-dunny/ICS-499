package tech.teamfour.cap_refactored;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.teamfour.GameContext;
import tech.teamfour.model.Line;
import tech.teamfour.model.LinePuzzle;
import tech.teamfour.model.Player;
import tech.teamfour.model.Point;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class GameControllerApplicationTests {

    @Test
    void contextLoads() {
        testNewPlayer();
        testNewPlayer();
    }

    @Test
    void testIsComplete(){
        LinePuzzle lp = new LinePuzzle();
        assert lp.isComplete();
    }

    @Test
    void testNewPlayer(){
        Player p = new Player(0L, "Test", "TestPW", 21);
        assert p.getPassword().equalsIgnoreCase("TestPW");
        assert p.getUserName().equalsIgnoreCase("Test");
        assert p.getPlayerID() == 0L;
    }

    @Test
    void checkGameEnding(){
        GameContext gc = new GameContext(new LinePuzzle(3), new Line());
        assert (gc.getEnd().isEnd());
        assert (gc.getGridSize() == 3);
        assertFalse(gc.getGridSize() == 4);
        assert (gc.move("Up"));
        gc.move("Up");
        System.out.println(gc.location.getX());
        System.out.println(gc.location.getY());
        //assert (gc.location.getX() == 2);
        //assert (gc.location.getY() == 2);
        assert (gc.getPath().getLine().peek().equals(gc.location));
    }

}
