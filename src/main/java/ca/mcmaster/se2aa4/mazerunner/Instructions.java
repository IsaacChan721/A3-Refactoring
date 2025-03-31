package ca.mcmaster.se2aa4.mazerunner;

public class Instructions {
    private String instructions;
    private Maze maze;

    public Instructions(String instructions, Maze maze){
        this.instructions = instructions;
        this.maze = maze;
    }

    public void excecuteInstruction(){
        //maze.printMaze();
        for(int i = 0; i < instructions.length(); i++){
            if(instructions.charAt(i) == 'F') maze.moveNavigatorForward();
            else if(instructions.charAt(i) == 'R') maze.getNavigator().turnRight();
            else if(instructions.charAt(i) == 'L') maze.getNavigator().turnLeft();
            //maze.printMaze();
        }
    }

    public void readInstructions(String path){
        int multiplier = 1;
        String newInstructions = "";
        boolean prevNumber = false;
        
        // iterates through the path
        for(int i = 0; i < path.length(); i++){
            char instruction = path.charAt(i);
            // checks if the read instruction is a digit
            if(Character.isDigit(instruction)){
                if(prevNumber){ // if the previous instruction character was already a number, add onto the multiplier
                    multiplier = multiplier * 10 + Character.getNumericValue(instruction);
                } else { // change the multiplier to the digit value
                    multiplier = Character.getNumericValue(instruction);
                    prevNumber = true;
                }
            // If it matches any of the character inputs then do the corresponding navigator movements
            } else {
                if (instruction == 'F' || instruction == 'R' || instruction == 'L') { // moving forward, turning right, or turning left
                    for(int j = 0; j < multiplier; j++){
                        newInstructions += instruction;
                    }
                }
                multiplier = 1;
                prevNumber = false;
            }
        }

        //reassigns instructions into canonical form
        this.instructions = newInstructions;
    }

    public String getFactorial(){
        if(instructions.isEmpty()) return "";
        String factorial = "";
        int counter = 1;
        char currChar = instructions.charAt(0);
        for(int i = 1; i < instructions.length(); i++){
            char newChar = instructions.charAt(i);
            if(currChar == newChar){
                counter++;
                if(i == instructions.length() - 1) factorial += counter + "" + currChar;
            } else if (counter == 1){
                factorial += currChar + " ";
                currChar = newChar;
                if(i == instructions.length() - 1) factorial += newChar;
            } else {    
                factorial += counter + "" + currChar + " ";
                currChar = newChar;
                counter = 1;
            }
        }
        return factorial;
    }

    public String getCanonical(){
        return instructions;
    }
}