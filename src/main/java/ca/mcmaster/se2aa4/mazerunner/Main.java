package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configurator.setLevel("ca.mcmaster.se2aa4.mazerunner.Main", org.apache.logging.log4j.Level.INFO);
        logger.info("** Starting Maze Runner\n");

        // Setup
        FlagReader flagReader = new FlagReader(args);
        String path = flagReader.getPath();
        String file = flagReader.getFile();

        Navigator navigator = new Navigator(Directions.EAST);
        Maze maze = new Maze(file, navigator);
        Algorithm algo = new Algorithm(maze);
        Instructions instructions = new Instructions(path, maze);

        logger.info("**** Computing path");

        if(path == null){ // search for a path that can get home if no path is found
            path = algo.rightHandPath();
            instructions.readInstructions(path);
            logger.info("Canonical path: " + path);
            logger.info("Factorial Path: " + instructions.getFactorial());
        } else { // check if the path is valid
            instructions.readInstructions(path);
            instructions.excecuteInstruction();

            //checks if the path works
            if(maze.getLocation()[0] == maze.getExit()[0] && maze.getLocation()[1] == maze.getExit()[1]){
                logger.info("Factorial Path: " + instructions.getFactorial());
                logger.info("SUCCESS");
            } else {
                logger.info("FAIL");
            }
        }

        logger.info("** End of MazeRunner");
    }
}


// to do:
// enum north south east and west (indexing as well)
// enum for 2d char array
