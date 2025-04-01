package ca.mcmaster.se2aa4.mazerunner;

public class MazeAction {
    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    public void runCommand(){
        command.execute();
    }
}
