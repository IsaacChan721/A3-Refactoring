package ca.mcmaster.se2aa4.mazerunner;

public class Algorithm {
    private Maze maze;
    MazeAction action = new MazeAction();

    public Algorithm (Maze maze){
        this.maze = maze;
    }

    public String rightHandPath(){
        String path = "";

        while(maze.getLocation()[0] != maze.getExit()[0] || maze.getLocation()[1] != maze.getExit()[1]){
            checkRight();
            if(checkForward()){
                path += "RF";
            } else {
                checkLeft();
                if(checkForward()){
                    path += "F";
                } else {
                    checkLeft();
                    if(checkForward()){
                        path += "LF";
                    } else {
                        checkLeft();
                        checkForward();
                        path += "LLF";
                    }
                }
            }
        }
        return path;
    }

    private boolean checkForward(){
        action.setCommand(new MoveForwardCommand(maze));
        action.runCommand();
        return maze.isForwardEmpty();
    }

    private void checkRight(){
        action.setCommand(new TurnRightCommand(maze.getNavigator()));
        action.runCommand();
    }

    private void checkLeft(){
        action.setCommand(new TurnLeftCommand(maze.getNavigator()));
        action.runCommand();
    }
}
