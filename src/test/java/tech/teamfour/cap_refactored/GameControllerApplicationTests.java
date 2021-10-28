package tech.teamfour.cap_refactored;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tech.teamfour.model.LinePuzzle;
import tech.teamfour.model.Player;

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

}
