import java.util.Scanner;

public class Game {
    public static void debugDisplay(String s, Board board){ // new function debug that changes the display
        switch(s){
            case "y":
            case "Y":
                board.print();
                break;
            case "n":
            case "N":
                board.display();
                break;             
        }
    }
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print("Debug? (Y/N): ");
        String debug = s.nextLine();
        if (!(debug.equals("Y") || debug.equals("y") || debug.equals("N") || debug.equals("n"))){
            System.out.println("Incorrect Character. Defaulting to N");
            debug = "N";
        } 
        System.out.println("----------------------");
        System.out.println("Welcome to BattleBoats");
        System.out.println("----------------------");
        System.out.println("Select Difficulty");
        System.out.println("1. Easy 3 x 3");
        System.out.println("2. Intermediate 6 x 6");
        System.out.println("3. Expert 9 x 9");
        System.out.println("----------------------");
        System.out.print("Type the number to select difficulty: ");

        int difficulty = s.nextInt();
        s.nextLine();

        Board board =  new Board();
        boolean difficultyChosen = false;
        int powerPoints = 0;
        
        while(!difficultyChosen){
            switch(difficulty){
                case 1:
                    board = new Board(1);
                    difficultyChosen = true;
                    powerPoints = 1;
                    break;
                case 2:
                    board = new Board(2);
                    difficultyChosen = true;
                    powerPoints = 3;
                    break;
                case 3:
                    board = new Board(3);
                    difficultyChosen = true;
                    powerPoints = 5;
                    break;
                default:
                    System.out.println("Incorrect diffuculty number");
                    System.out.print("Type the number to select difficulty: ");
                    difficulty = s.nextInt();
                    s.nextLine();

            }
        }
        board.placeBoats();
        board.set_turn(1);
        
        System.out.println("-----------------------");
        System.out.println("         Rules         ");
        System.out.println("-----------------------");
        System.out.println("Sink all boats on the grid in the fewest amount of turns to win!");
        System.out.println("Missile, Drone, and Submarine cost 1 power point");
        System.out.println("Missile: Fire in a 3x3 grid in selected coordinate");
        System.out.println("Drone: Scans a random row or column and prints out number targets found");
        System.out.println("Submarine: Sinks the whole boat");
        System.out.println("-----------------------");

        System.out.print("Ready? (Y/N): ");
        String ready = s.nextLine();
        while (!(ready.equals("Y") || ready.equals("y") || ready.equals("N") || ready.equals("n"))){
            System.out.println("Incorrect Character.");
            System.out.print("Ready? (Y/N): ");
            ready = s.nextLine();
        }
        System.out.println("-----------------------");

        int x, y;
        String action = "";
        int remainingBoats = board.get_remaingBoats();

        if(ready.equals("Y") || ready.equals("y")){
            while(!action.equals("quit") && remainingBoats != 0){ // temporary stop quit 
                debugDisplay(debug,board);
                System.out.println("Remaining Boats: "+board.get_remaingBoats());
                System.out.println("Actions: fire, missile, drone, submarine, quit");
                System.out.println("Power Points: "+powerPoints);
                System.out.print("Turn "+ board.get_turn() +": Type action: ");
                action = s.nextLine();
                switch (action) {
                    case "fire":
                        System.out.print("Type cell to fire: ");
                        x = s.nextInt();
                        y = s.nextInt();
                        s.nextLine();
                        board.fire(x, y);
                        board.set_turn(board.get_turn()+1);
                        break;
                    case "quit":
                        break;
                    case "missile": 
                        if (powerPoints>0){
                            System.out.print("Type cell to fire missile: ");
                            x = s.nextInt();
                            y = s.nextInt();
                            s.nextLine();
                            boolean missilelocation = board.missile(x, y);
                            while(!missilelocation){
                                System.out.println("Incorrect missile location");
                                System.out.print("Type cell to fire missile:" );
                                x = s.nextInt();
                                y = s.nextInt();
                                s.nextLine();
                                missilelocation = board.missile(x,y);
                            }
                            board.set_turn(board.get_turn()+1);
                            powerPoints--;
                        } else {
                            System.out.println("Missile has been used the max amount of times");
                        }
                        break;
                    case "drone":
                        if (powerPoints>0){
                            board.drone();
                            board.set_turn(board.get_turn()+1);
                            powerPoints--;
                        } else {
                            System.out.println("Drone has been used the max amount of times");
                        }
                        break;
                    case "submarine":
                        if (powerPoints>0){
                            System.out.print("Type cell to fire submarine: ");
                            x = s.nextInt();
                            y = s.nextInt();
                            s.nextLine();
                            boolean sublocation = board.submarine(x, y);
                            while(!sublocation){
                                System.out.println("Incorrect submarine location");
                                System.out.print("Type cell to fire submarine:" );
                                x = s.nextInt();
                                y = s.nextInt();
                                s.nextLine();
                                sublocation = board.submarine(x,y);
                            }
                            board.set_turn(board.get_turn()+1);
                            powerPoints--;
                        } else {
                            System.out.println("Submarine has been used the max amount of times");
                        }
                        break;

                }
                remainingBoats = board.get_remaingBoats();

            }
            debugDisplay(debug,board);
            System.out.println("-----------------------");
            System.out.println("       Game Over       ");
            System.out.println("-----------------------");
            if (remainingBoats == 0){
                System.out.println("All boats sunked in "+(board.get_turn()-1)+" turn(s).");
            }
        }
        System.out.println("Game exited");
        s.close();
    }
}

// Written by Jeffrey Do, do000043