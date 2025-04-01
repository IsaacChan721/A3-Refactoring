package ca.mcmaster.se2aa4.mazerunner;

public class Navigator {
    private final Directions[] directions = Directions.values();
    private Directions facing;

    public Navigator(Directions facing){
        this.facing = facing;
    }

    public Directions getFacing(){
        return facing;
    }

    public void setFacing(Directions direction){
        this.facing = direction;
    }
}
