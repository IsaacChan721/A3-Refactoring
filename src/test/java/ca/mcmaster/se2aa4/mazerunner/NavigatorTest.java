package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        
        nav.turnRight();
        assertEquals(Directions.EAST, nav.getFacing());
        
        nav.turnRight();
        assertEquals(Directions.SOUTH, nav.getFacing());
        
        nav.turnRight();
        assertEquals(Directions.WEST, nav.getFacing());
        
        nav.turnRight();
        assertEquals(Directions.NORTH, nav.getFacing());
    }

    @Test
    public void testTurnLeft() {
        Navigator nav = new Navigator(Directions.NORTH);
        
        nav.turnLeft();
        assertEquals(Directions.WEST, nav.getFacing());
        
        nav.turnLeft();
        assertEquals(Directions.SOUTH, nav.getFacing());
        
        nav.turnLeft();
        assertEquals(Directions.EAST, nav.getFacing());
        
        nav.turnLeft();
        assertEquals(Directions.NORTH, nav.getFacing());
    }

    @Test
    public void testMultipleTurns() {
        Navigator nav = new Navigator(Directions.EAST);
        
        // Turn right 4 times (full circle)
        for (int i = 0; i < 4; i++) {
            nav.turnRight();
        }
        assertEquals(Directions.EAST, nav.getFacing());
        
        // Turn left 4 times (full circle)
        for (int i = 0; i < 4; i++) {
            nav.turnLeft();
        }
        assertEquals(Directions.EAST, nav.getFacing());
        
        // Mixed turns
        nav.turnRight();
        nav.turnLeft();
        assertEquals(Directions.EAST, nav.getFacing());
        
        nav.turnRight();
        nav.turnRight();
        nav.turnLeft();
        assertEquals(Directions.SOUTH, nav.getFacing());
    }
}