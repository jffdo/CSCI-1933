Group members' names and x500: 
    Jeffrey Do, do000043
How to compile and run your program:
    In command prompt, go to the directory of where Game.java is saved.
    Type "javac Game.java" and press enter.
    Type "java Game" and press enter.
Any assumptions:
    Assumes the user inputs the correct character for "(Y/N)" questions.
    Assumes the user inputs 2 ints when asked.
Additional features that you implemented:
    Cell class methods
        boolean equals(Cell other):
            Checks if the the Cell provided matches the row and column
        String toString():
            Outputs the cell as a String
    Boat class methods
        boolean boatStatus():
            Returns true if all cells are hit, else returns false
        boolean contains(Cell other):
            Returns true if Boat contains provided cell, else returns false
        String toString():
            Outputs Boat as a string
    Board class methods
        Board(){}:
            default Board constructer
        int get_turn():
            getter method returning the turn
        void set_turn(int i):
            setter method changing the value of the turn
        Boat findBoat(int x, int y):
            Returns a Boat that contains the provided cell
        int get_remaingBoats():
            getter method returning the remainging amount of boats
        void set_remainingBoats(int i):
            setter method changing the value of the reaming boats
    Game class methods
        void debugDisplay(String s, Board board):
            helper function that changes the display of the board depending checking for debug
Any known bugs and defects:
    No known bugs
Any outside sources consulted for ideas used in the project, in the format:
    No outside sources consulted.
"I certify that the information contained in this README file is complete and accurate. I have both read and followed the course policies in the 'Academic Integrety - Course Policy section of the course syllabus."

// Written by Jeffrey Do, do000043