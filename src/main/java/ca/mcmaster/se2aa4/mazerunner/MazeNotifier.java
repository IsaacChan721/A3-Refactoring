package ca.mcmaster.se2aa4.mazerunner;

public interface MazeNotifier {
    public void notifyObservers();
    public void addMazeObserver(MazeObserver observer);
    public void removeMazeObserver(MazeObserver observer);
}
