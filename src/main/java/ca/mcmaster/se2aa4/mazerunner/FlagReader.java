package ca.mcmaster.se2aa4.mazerunner;

public class FlagReader{
    String file;
    String path;

    public FlagReader(String[] args){
        for(int i = 0; i < args.length - 1; i++){
            if(args[i].equals("-i") && args[i+1] != null){
                file = args[i+1];
            } else if (args[i].equals("-p") && args[i+1] != null){
                path = args[i+1];
            }
        }
    }

    public String getFile(){
        return file;
    }

    public String getPath(){
        return path;
    }
}