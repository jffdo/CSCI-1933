import java.util.Random;
public class Board{
    private Cell[][] grid;
    private Boat[] fleet;
    private int turn;
    private int remainingBoats;
    public Board(int difficulty){
        Random random = new Random();
        switch(difficulty){
            case 1:
                grid = new Cell[3][3];
                fleet = new Boat[1];
                fleet[0] = new Boat(2, random.nextBoolean());
                set_remainingBoats(fleet.length);
                break;
            case 2:
                grid = new Cell[6][6];
                fleet = new Boat[3];
                fleet[0] = new Boat(2, random.nextBoolean());
                fleet[1] = new Boat(3, random.nextBoolean());
                fleet[2] = new Boat(4, random.nextBoolean());
                set_remainingBoats(fleet.length);
                break;
            case 3:
                grid = new Cell[9][9];
                fleet = new Boat[5];
                fleet[0] = new Boat(2, random.nextBoolean());
                fleet[1] = new Boat(3, random.nextBoolean());
                fleet[2] = new Boat(3, random.nextBoolean());
                fleet[3] = new Boat(4, random.nextBoolean());
                fleet[4] = new Boat(5, random.nextBoolean());
                set_remainingBoats(fleet.length);
                break;
                
        }
    }

    public Board(){} // new function default constructer

    public void placeBoats(){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                grid[row][col] = new Cell(row, col, '-');
            }
        }
        int placedBoats = 0;
        while(placedBoats < fleet.length){
            int x = (int) Math.floor(Math.random() * (grid.length));
            int y = (int) Math.floor(Math.random() * (grid.length));
            int[] location = {x,y};

            fleet[placedBoats].set_boat(location);
            Cell[] ship = fleet[placedBoats].get_boat();

            boolean open = true;
            for(Cell pos: ship){
                int xpos = pos.get_row();
                int ypos = pos.get_col();

                if ((xpos > grid.length-1) || (ypos > grid.length-1)){
                    open = false;
                } else {
                    char status = grid[xpos][ypos].get_status();
                    if (status != '-'){
                        open = false;
                    }
                }
            }

            if (open){
                for(Cell pos:ship){
                    int xpos = pos.get_row();
                    int ypos = pos.get_col(); 
                    grid[xpos][ypos] = pos;
                }
                placedBoats++;
            }

        }


    }

    public void print(){
        System.out.println("-----------------------");
        System.out.println("         Debug         ");
        System.out.println("-----------------------");
        String s = "";
        String num = "";
        for(int i = 0; i < (2*grid.length)+2; i++){
            if (i%2 == 0){
                s += "-";
            } else {
                s += "+";
            }
        }
        for(int i = 0; i < (2*grid.length)+2; i++){
            if(i == 0){
                num +=" ";
            } else if (i == (2*grid.length)+1){
                num += "|\n";
            } else if (i%2 == 0){
                num += (i/2)-1;
            } else {
                num += "|";
            }
        }

        System.out.print(num);
        for(int row = 0; row < grid.length; row++){
            System.out.println(s);
            for(int col = 0; col < grid[row].length; col++){
                if (col == grid[row].length - 1){
                    System.out.print("|" + grid[row][col].get_status() + "|\n");
                } else if (col == 0){
                    System.out.print(row +"|" + grid[row][col].get_status());
                } else {
                    System.out.print("|" + grid[row][col].get_status());
                }

            }
        }
        System.out.println(s);
        System.out.println("-----------------------");
        for (int i = 0; i < fleet.length; i++){
            System.out.println("Boat"+i+": "+fleet[i]);
        }
        System.out.println("-----------------------");
    }

    public void display(){
        String s = "";
        String num = "";
        for(int i = 0; i < (2*grid.length)+2; i++){
            if (i%2 == 0){
                s += "-";
            } else {
                s += "+";
            }
        }
        for(int i = 0; i < (2*grid.length)+2; i++){
            if(i == 0){
                num +=" ";
            } else if (i == (2*grid.length)+1){
                num += "|\n";
            } else if (i%2 == 0){
                num += (i/2)-1;
            } else {
                num += "|";
            }
        }

        System.out.print(num);
        for(int row = 0; row < grid.length; row++){
            System.out.println(s);
            for(int col = 0; col < grid[row].length; col++){
                char status = grid[row][col].get_status();
                switch(status){
                    case '-':
                    case 'B':
                        status = ' ';
                        break;
                    case 'H':
                        status = 'X';
                        break;
                    case 'M':
                        status = '*';
                        break;
                }
                if (col == grid[row].length - 1){
                    System.out.print("|" + status + "|\n");
                } else if (col == 0){
                    System.out.print(row +"|" + status);
                } else {
                    System.out.print("|" + status);
                }

            }
        }
        System.out.println(s);
    }



    public void fire(int x, int y){
        if((x < 0) || (y < 0) || (x >= grid.length) || (y >= grid.length)){
            System.out.println("-----------------------");
            System.out.println("        Penalty!       ");
            System.out.println("-----------------------");
            turn++;
            System.out.println("Turn "+turn+": Skipped.");
        } else {
            char status = grid[x][y].get_status();
            switch(status){
                case '-':
                    grid[x][y].set_status('M');
                    System.out.println("-----------------------");
                    System.out.println("         Miss!         ");
                    System.out.println("-----------------------");
                    break;
                case 'B':
                    Boat boatHit = findBoat(x, y);
                    grid[x][y].set_status('H');
                    if (boatHit.boat_status()){ // Check if boat is 'sunk'
                        System.out.println("-----------------------");
                        System.out.println("         Sunk!         ");
                        System.out.println("-----------------------");
                        remainingBoats--;
                    } else {
                        System.out.println("-----------------------");
                        System.out.println("          Hit!         ");
                        System.out.println("-----------------------");
                    }
                    break;
                default:
                    System.out.println("-----------------------");
                    System.out.println("        Penalty!       ");
                    System.out.println("-----------------------");
                    turn++;
                    System.out.println("Turn "+turn+": Skipped.");
                    break;
            }
        }
    }

    public int get_turn(){ // new function that returns the turn
        return turn;
    }

    public void set_turn(int i){ // new function that sets the turn
        turn = i;
    }

    public Boat findBoat(int x, int y){ // new function that finds the boat
        Boat boatHit = fleet[0];
        for(Boat ship : fleet){
            if (ship.contains(grid[x][y])){
                boatHit = ship;
            }
        }
        return boatHit;
    }

    public int get_remaingBoats(){ // new function that returns the remaing boats
        return remainingBoats;
    }

    public void set_remainingBoats(int i){ // new function that sets the remaining boats
        remainingBoats = i;
    }

    public boolean missile(int x, int y){
        int boatSunked = 0;
        if ((x < 0) || (x >= grid.length) || (y < 0) || (y >= grid.length)){
            return false;
        }
        int[][] location = {{x-1,y-1},{x,y-1},{x+1,y-1},{x-1,y},{x,y},{x+1,y},{x-1,y+1},{x,y+1},{x+1,y+1}};
        for(int[] pos : location){
            if ((0<=pos[0]) && (pos[0]<grid.length) && (0<=pos[1]) && (pos[1]<grid.length)){
                char status = grid[pos[0]][pos[1]].get_status();
                switch(status){
                    case '-':
                        grid[pos[0]][pos[1]].set_status('M');
                        break;
                    case 'B':
                        Boat boatHit = findBoat(pos[0], pos[1]);
                        grid[pos[0]][pos[1]].set_status('H');
                        if(boatHit.boat_status()){
                            remainingBoats--;
                            boatSunked++;
                        }
                        break;
                }
            }
        }
        System.out.println("-----------------------");
        System.out.println("   "+boatSunked+" boat(s) Sunked!");
        System.out.println("-----------------------");
        return true;
    }

    public void drone(){
        Random random = new Random();
        boolean section = random.nextBoolean();
        int num = (int) Math.floor(Math.random() * (grid.length));
        int target = 0;
        if (section){ // true = row
            for(int i = 0; i < grid.length; i++){
                if ((grid[num][i].get_status() == 'B') || (grid[num][i].get_status() == 'H')){
                    target++;
                }
            }
            System.out.println("-----------------------");
            System.out.println("Drone has scanned "+ target + " target(s) in row "+num);
            System.out.println("-----------------------");
        } else { // false column
            for(int i = 0; i < grid.length; i++){
                if ((grid[i][num].get_status() == 'B') || (grid[i][num].get_status() == 'H')){
                    target++;
                }
            }
            System.out.println("-----------------------");
            System.out.println("Drone has scanned "+ target + " target(s) in column "+num);
            System.out.println("-----------------------");
        }
        
    }

    public boolean submarine(int x, int y){
        if ((x < 0) || (x >= grid.length) || (y < 0) || (y >= grid.length)){
            return false;
        }
        char status = grid[x][y].get_status();
        if ((status == 'B') || (status == 'H')){
            Boat boatHit = findBoat(x, y);
            grid[x][y].set_status('H');

            for(int i = 0; i < boatHit.get_boat().length; i++){
                int xpos = boatHit.get_boat()[i].get_row();
                int ypos = boatHit.get_boat()[i].get_col();
                grid[xpos][ypos].set_status('H');
            }
            System.out.println("-----------------------");
            System.out.println("         Sunk!         ");
            System.out.println("-----------------------");
            remainingBoats--;
            
        } else {
            grid[x][y].set_status('M');
            System.out.println("-----------------------");
            System.out.println("         Miss!         ");
            System.out.println("-----------------------");
        }
        return true;
    }


}

// Written by Jeffrey Do, do000043