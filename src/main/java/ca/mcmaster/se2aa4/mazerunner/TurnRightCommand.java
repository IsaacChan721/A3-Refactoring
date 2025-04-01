package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements Command{
    Navigator navigator;

    public TurnRightCommand(Navigator navigator){
        this.navigator = navigator;
    }

    @Override
    public void execute(){
        Directions[] directions = Directions.values();
        for(int i=0; i<directions.length; i++){
            if(directions[i] == navigator.getFacing()){
                navigator.setFacing(directions[(i+1)%directions.length]);
                return;
            }
        }
    }
}
