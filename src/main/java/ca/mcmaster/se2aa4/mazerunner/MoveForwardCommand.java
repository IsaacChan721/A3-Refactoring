package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Arrays;

public class MoveForwardCommand implements Command, MazeNotifier{
    Maze maze;
    private ArrayList<MazeObserver> observers = new ArrayList<>();

    public MoveForwardCommand(Maze maze){
        this.maze = maze;
    }

    @Override
    public void execute(){
        Directions facing = maze.getNavigator().getFacing();
        int[] location = maze.getLocation();
        ArrayList<MazeFeatures[]> mazeObject = maze.getMazeObject();

        int mazeWidth = mazeObject.get(0).length;
        int mazeHeight = mazeObject.size();

        if(facing == Directions.NORTH && location[1] != 0 && mazeObject.get(location[1]-1)[location[0]] == MazeFeatures.SPACE) location[1] -= 1;
        else if(facing == Directions.EAST && location[0] != mazeWidth-1 && mazeObject.get(location[1])[location[0]+1] == MazeFeatures.SPACE) location[0] += 1;
        else if(facing == Directions.SOUTH && location[1] != mazeHeight-1 && mazeObject.get(location[1]+1)[location[0]] == MazeFeatures.SPACE) location[1] += 1;
        else if(facing == Directions.WEST && location[0] != 0 && mazeObject.get(location[1])[location[0]-1] == MazeFeatures.SPACE) location[0] -= 1;
        else {
            maze.setForwardEmpty(false);
            return;
        }
        maze.setLocation(location);
        maze.setForwardEmpty(true);

        if(Arrays.equals(location, maze.getExit())) notifyObservers();
    }

    @Override
    public void notifyObservers(){
        for(MazeObserver o: observers){
            o.update();
        }
    }

    @Override
    public void addMazeObserver(MazeObserver o){
        observers.add(o);
    }

    @Override
    public void removeMazeObserver(MazeObserver o){
        observers.remove(o);
    }
}
