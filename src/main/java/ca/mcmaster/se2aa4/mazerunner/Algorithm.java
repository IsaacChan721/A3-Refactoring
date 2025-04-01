package ca.mcmaster.se2aa4.mazerunner;

public class Algorithm implements MazeObserver{
    private Maze maze;
    private boolean mazeSolved;
    MazeAction action = new MazeAction();
    
    MoveForwardCommand mfc;
    TurnRightCommand trc;
    TurnLeftCommand tlc;

    public Algorithm (Maze maze){
        this.maze = maze;
        this.mazeSolved = false;

        mfc = new MoveForwardCommand(maze);
        trc = new TurnRightCommand(maze.getNavigator());
        tlc = new TurnLeftCommand(maze.getNavigator());
    }

    public String rightHandPath(){
        mfc.addMazeObserver(this);
        String path = "";
        while(!mazeSolved){
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
        action.setCommand(mfc);
        action.runCommand();
        return maze.isForwardEmpty();
    }

    private void checkRight(){
        action.setCommand(trc);
        action.runCommand();
    }

    private void checkLeft(){
        action.setCommand(tlc);
        action.runCommand();
    }

    @Override
    public void update(){
        mazeSolved = true;
    }
}
