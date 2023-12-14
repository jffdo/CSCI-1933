// Names: Jeffrey Do
// x500s: do000043

import java.util.Random;
import java.util.Scanner;

public class MyMaze{
    Cell[][] maze;
    int startRow;
    int endRow;

    public MyMaze(int rows, int cols, int startRow, int endRow) {
        maze = new Cell[rows][cols];
        this.startRow = startRow;
        this.endRow = endRow;

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maze[i][j] = new Cell();
            }
        }
    }

    /* TODO: Create a new maze using the algorithm found in the writeup. */
    public static MyMaze makeMaze(int level) {
        int rows = 0;
        int cols = 0;
        switch (level){
            case 1:
                rows = 5;
                cols = 5;
                break;
            case 2:
                rows = 5;
                cols = 20;
                break;
            case 3:
                rows = 20;
                cols = 20;
                break;
        }
        Random rand = new Random();
        int startRow = rand.nextInt(rows);
        int endRow = rand.nextInt(rows);
        MyMaze grid = new MyMaze(rows,cols,startRow,endRow);

        Stack1Gen<int[]> stack = new Stack1Gen<>();
        stack.push(new int[] {startRow, 0});
        grid.maze[startRow][0].setVisited(true);

        while(!stack.isEmpty()){
            int[] pos = stack.pop();
            int row = pos[0];
            int col = pos[1];
            stack.push(pos);

            boolean visited = true;
            while(visited){
                int direction = rand.nextInt(4);
                switch (direction){
                    case 0: // up
                        if((0 <= row-1 && row-1 <= rows-1) && !grid.maze[row-1][col].getVisited()){
                            stack.push(new int[] {row-1,col});
                            grid.maze[row-1][col].setVisited(true);
                            grid.maze[row-1][col].setBottom(false);
                            visited = false;
                            break;
                        }
                    case 1: // left
                        if((0 <= col-1 && col-1 <= cols-1) && !grid.maze[row][col-1].getVisited()){
                            stack.push(new int[] {row,col-1});
                            grid.maze[row][col-1].setVisited(true);
                            grid.maze[row][col-1].setRight(false);
                            visited = false;
                            break;
                        }
                    case 2: // down
                        if((0 <= row+1 && row+1 <= rows-1) && !grid.maze[row+1][col].getVisited()){
                            stack.push(new int[] {row+1,col});
                            grid.maze[row+1][col].setVisited(true);
                            grid.maze[row][col].setBottom(false);
                            visited = false;
                            break;
                        }
                    case 3: // right
                        if((0 <= col+1 && col+1 <= cols-1) && !grid.maze[row][col+1].getVisited()){
                            stack.push(new int[] {row,col+1});
                            grid.maze[row][col+1].setVisited(true);
                            grid.maze[row][col].setRight(false);
                            visited = false;
                            break;
                        }
                        if(direction == 0){
                            visited = false;
                            stack.pop();
                            break;
                        }
                }
            }

        }

        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                grid.maze[i][j].setVisited(false);
            }
        }

        return grid;
    }

    /* TODO: Print a representation of the maze to the terminal */
    public void printMaze() {
        String s = "";
        maze[endRow][maze[endRow].length-1].setRight(false);

        for(int x = 0; x < maze[startRow].length; x++){
            s += "|---";
        }
        System.out.println(s+"|");

        for (int i = 0; i < maze.length; i++) {
            if (i == startRow) {
                System.out.print(" ");
            } else{
                System.out.print("|");
            }
            for (int j = 0; j < maze[i].length; j++) {
                char v = ' ';
                char r = ' ';
                if (maze[i][j].getVisited()) {
                    v = '*';
                }
                if (maze[i][j].getRight()) {
                    r = '|';
                }
                System.out.print(" " + v + " " + r);
            }
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < maze[i].length; j++) {
                String b = "   ";
                if (maze[i][j].getBottom()) {
                    b = "---";
                }
                System.out.print(b +"|");
            }
            System.out.println();
        }
        System.out.println();
    }

    /* TODO: Solve the maze using the algorithm found in the writeup. */
    public void solveMaze() {
        Q1Gen<int[]> q = new Q1Gen<>();
        q.add(new int[] {startRow, 0});

        while(q.length() != 0){
            int[] pos = q.remove();
            int row = pos[0];
            int col = pos[1];
            maze[row][col].setVisited(true);
            if (row == endRow && col == maze[row].length - 1) {
                break;
            }
            if (0 <= row-1 && row-1 <= maze.length-1 && !(maze[row-1][col].getBottom()) && !maze[row-1][col].getVisited()) { // up
                q.add(new int[]{row - 1, col});
            }
            if (row+1 <= maze.length-1 && !(maze[row][col].getBottom()) && !maze[row+1][col].getVisited()) { // down
                q.add(new int[]{row + 1, col});
            }
            if (0 <= col-1 && col-1 <= maze[row].length-1 && !(maze[row][col-1].getRight()) && !maze[row][col-1].getVisited()) { // left
                q.add(new int[]{row, col - 1});
            }
            if (col+1 <= maze[row].length-1 && !(maze[row][col].getRight()) && !maze[row][col+1].getVisited()) { // right
                q.add(new int[]{row, col + 1});
            }
        }
    }

    public static void main(String[] args){
        /* Use scanner to get user input for maze level, then make and solve maze */
        Scanner s = new Scanner(System.in);
        System.out.println("Select Level of Maze");
        System.out.println("- Level 1: 5x5");
        System.out.println("- Level 2: 5x20");
        System.out.println("- Level 3: 20x20");
        MyMaze mz = null;
        boolean levelChosen = false;
        while(!levelChosen){
            System.out.print("Generate Level: ");
            int level = s.nextInt();
            s.nextLine();
            switch (level){
                case 1:
                    mz = makeMaze(1);
                    levelChosen = true;
                    break;
                case 2:
                    mz = makeMaze(2);
                    levelChosen = true;
                    break;
                case 3:
                    mz = makeMaze(3);
                    levelChosen = true;
                    break;
                default:
                    System.out.println("Invalid Level Number");
                    break;
                    
            }
        }
        System.out.println("Generated Maze");
        mz.printMaze();
        mz.solveMaze();
        System.out.println("Solved Maze");
        mz.printMaze();
    }
}

// Written by Jeffrey Do, do000043