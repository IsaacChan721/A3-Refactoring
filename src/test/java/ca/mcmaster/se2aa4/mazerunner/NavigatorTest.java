package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class NavigatorTest {

    @Test
    public void testInitialDirection() {
        Navigator navNorth = new Navigator(Directions.NORTH);
        assertEquals(Directions.NORTH, navNorth.getFacing());
        
        Navigator navEast = new Navigator(Directions.EAST);
        assertEquals(Directions.EAST, navEast.getFacing());
        
        Navigator navSouth = new Navigator(Directions.SOUTH);
        assertEquals(Directions.SOUTH, navSouth.getFacing());
        
        Navigator navWest = new Navigator(Directions.WEST);
        assertEquals(Directions.WEST, navWest.getFacing());
    }

    @Test
    public void testTurnRight() {
        Navigator nav = new Navigator(Directions.NORTH);
        MazeAction action = new MazeAction();
        action.setCommand(new TurnRightCommand(nav));

        action.runCommand();
        assertEquals(Directions.EAST, nav.getFacing());
        
        action.runCommand();
        assertEquals(Directions.SOUTH, nav.getFacing());
        
        action.runCommand();
        assertEquals(Directions.WEST, nav.getFacing());
        
        action.runCommand();
        assertEquals(Directions.NORTH, nav.getFacing());
    }

    @Test
    public void testTurnLeft() {
        Navigator nav = new Navigator(Directions.NORTH);
        MazeAction action = new MazeAction();
        action.setCommand(new TurnLeftCommand(nav));

        action.runCommand();
        assertEquals(Directions.WEST, nav.getFacing());
        
        action.runCommand();
        assertEquals(Directions.SOUTH, nav.getFacing());
        
        action.runCommand();
        assertEquals(Directions.EAST, nav.getFacing());
        
        action.runCommand();
        assertEquals(Directions.NORTH, nav.getFacing());
    }

    @Test
    public void testMultipleTurns() {
        Navigator nav = new Navigator(Directions.EAST);
        MazeAction action = new MazeAction();
        
        // Turn right 4 times (full circle)
        action.setCommand(new TurnRightCommand(nav));
        for (int i = 0; i < 4; i++) {
            action.runCommand();
        }
        assertEquals(Directions.EAST, nav.getFacing());
        
        // Turn left 4 times (full circle)
        action.setCommand(new TurnLeftCommand(nav));
        for (int i = 0; i < 4; i++) {
            action.runCommand();
        }
        assertEquals(Directions.EAST, nav.getFacing());
        
        // Mixed turns
        action.setCommand(new TurnRightCommand(nav));
        action.runCommand();
        action.setCommand(new TurnLeftCommand(nav));
        action.runCommand();
        assertEquals(Directions.EAST, nav.getFacing());
        
        action.setCommand(new TurnRightCommand(nav));
        action.runCommand();
        action.runCommand();
        action.setCommand(new TurnLeftCommand(nav));
        action.runCommand();
        assertEquals(Directions.SOUTH, nav.getFacing());
    }
}