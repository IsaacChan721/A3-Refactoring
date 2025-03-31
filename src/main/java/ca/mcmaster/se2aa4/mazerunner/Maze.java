package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private BufferedReader reader;

    private ArrayList<MazeFeatures[]> mazeObject;
    private Navigator navigator;
    private int[] location = new int[2]; //coordinate pair

    private int mazeWidth, mazeHeight;

    public Maze(String file, Navigator navigator){
        mazeObject = new ArrayList();
        this.navigator = navigator;
        
        //Creating a file reader
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error(e);
        }

        try {
            MazeFeatures[] lineChars;
            int lineNum = 0;
            String line;
            // creates the maze object from the file
            while ((line = reader.readLine()) != null) {
                lineChars = new MazeFeatures[line.length()];
                for (int idx=0; idx<line.length(); idx++) {
                    if(line.charAt(idx) == '#') lineChars[idx] = MazeFeatures.WALL;
                    else lineChars[idx] = MazeFeatures.SPACE;
                }
                mazeObject.add(lineChars);
                lineNum++;
            }
            mazeWidth = mazeObject.get(0).length;
            mazeHeight = mazeObject.size();
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        //Searches for the entrance and sets the location there
        location = getEntrance();
    }

    public int[] getEntrance(){
        for(int i = 0; i < mazeHeight; i++){
            if(mazeObject.get(i)[0] == MazeFeatures.SPACE) return new int[]{0, i};
        }
        return null;
    }

    public int[] getExit(){
        for(int i = 0; i < mazeHeight; i++){
            if(mazeObject.get(i)[mazeWidth-1] == MazeFeatures.SPACE) return new int[]{mazeWidth-1, i};
        }
        return null;
    }

    public int[] getLocation(){
        return location;
    }

    public void setLocation(int[] coordinate){
        location = coordinate;
    }

    public boolean moveNavigatorForward(){
        Directions facing = navigator.getFacing();

        if(facing == Directions.NORTH && location[1] != 0 && mazeObject.get(location[1]-1)[location[0]] == MazeFeatures.SPACE) location[1] -= 1;
        else if(facing == Directions.EAST && location[0] != mazeWidth-1 && mazeObject.get(location[1])[location[0]+1] == MazeFeatures.SPACE) location[0] += 1;
        else if(facing == Directions.SOUTH && location[1] != mazeHeight-1 && mazeObject.get(location[1]+1)[location[0]] == MazeFeatures.SPACE) location[1] += 1;
        else if(facing == Directions.WEST && location[0] != 0 && mazeObject.get(location[1])[location[0]-1] == MazeFeatures.SPACE) location[0] -= 1;
        else return false;
        return true;
    }

    // for troubleshooting purposes only
    public void printMaze(){
        Directions facing = navigator.getFacing();
        String line = "";
        for (int y = 0; y < mazeObject.size(); y++) {
            for (int x = 0; x < mazeObject.get(0).length; x++) {
                if(location[0] == x && location[1] == y){
                    if(facing == Directions.NORTH) line += '^';
                    else if(facing == Directions.EAST) line += '>';
                    else if(facing == Directions.SOUTH) line += 'v';
                    else if(facing == Directions.WEST) line += '<';
                } else {
                    if(mazeObject.get(y)[x] == MazeFeatures.SPACE) line += ' ';
                    else line += '#';
                    //line += mazeObject.get(y)[x] + "\t";
                }
            }
            System.out.println(line);
            line = "";
        }
        System.out.println();
    }

    public Navigator getNavigator(){
        return navigator;
    }
}
