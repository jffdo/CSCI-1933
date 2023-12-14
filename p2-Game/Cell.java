public class Cell {
    private int row; 
    private int col; 
    private char status; 

    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public char get_status(){
        return status;
    }

    public void set_status(char c){
        status = c;
    }

    public int get_row(){
        return row;
    }

    public void set_row(int row){
        this.row = row;
    }
    
    public int get_col(){
        return col;
    }

    public void set_col(int col){
        this.col = col;
    }

    public boolean equals(Cell other){ // new helper equals function
        return ((row == other.get_row()) && (col == other.get_col()));
    }

    public String toString(){ // new helper toString function
        return "{("+get_row()+","+get_col()+") Status: "+get_status()+"}";
    }
}

// Written by Jeffrey Do, do000043
