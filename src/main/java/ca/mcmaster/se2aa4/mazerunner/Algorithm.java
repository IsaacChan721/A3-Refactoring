package ca.mcmaster.se2aa4.mazerunner;

public class Algorithm {
    private Maze maze;

    public Algorithm (Maze maze){
        this.maze = maze;
    }

    public String rightHandPath(){
        String path = "";
        Navigator navigator = maze.getNavigator();

        while(maze.getLocation()[0] != maze.getExit()[0] || maze.getLocation()[1] != maze.getExit()[1]){
            navigator.turnRight();
            if(maze.moveNavigatorForward()){
                path += "RF";
            } else {
                navigator.turnLeft();
                if(maze.moveNavigatorForward()){
                    path += "F";
                } else {
                    navigator.turnLeft();
                    if(maze.moveNavigatorForward()){
                        path += "LF";
                    } else {
                        navigator.turnLeft();
                        maze.moveNavigatorForward();
                        path += "LLF";
                    }
                }
            }
            //maze.printMaze();
        }
        return path;
    }
}
