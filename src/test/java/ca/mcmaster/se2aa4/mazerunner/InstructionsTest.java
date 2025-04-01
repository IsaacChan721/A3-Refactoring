package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class InstructionsTest {
    private Maze mockMaze;

    @BeforeEach
    public void setUp() {
        mockMaze = mock(Maze.class);
    }

    @Test
    public void testReadInstructions_Simple() {
        Instructions instructions = new Instructions("", mockMaze);
        instructions.readInstructions("FFRRLL");
        assertEquals("FFRRLL", instructions.getCanonical());
    }

    @Test
    public void testReadInstructions_SingleDigitNumbers() {
        Instructions instructions = new Instructions("", mockMaze);
        instructions.readInstructions("3F 2R 4L");
        assertEquals("FFFRRLLLL", instructions.getCanonical());
    }

    @Test
    public void testReadInstructions_MultiDigitNumbers() {
        Instructions instructions = new Instructions("", mockMaze);
        instructions.readInstructions("10F 5R 2L");
        assertEquals("FFFFFFFFFFRRRRRLL", instructions.getCanonical());
    }

    @Test
    public void testReadInstructions_InvalidChars() {
        Instructions instructions = new Instructions("", mockMaze);
        instructions.readInstructions("FX3RYL2Z");
        assertEquals("FRRRL", instructions.getCanonical());
    }

    @Test
    public void testGetFactorial_Mixed() {
        Instructions instructions = new Instructions("FFRLLLFF", mockMaze);
        assertEquals("2F R 3L 2F", instructions.getFactorial());
    }

    @Test
    public void testGetFactorial_Empty() {
        Instructions instructions = new Instructions("", mockMaze);
        assertEquals("", instructions.getFactorial());
    }

    @Test
    public void testGetCanonical() {
        Instructions instructions = new Instructions("FFRRLL", mockMaze);
        assertEquals("FFRRLL", instructions.getCanonical());
    }
}