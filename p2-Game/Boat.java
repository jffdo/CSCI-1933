public class Boat {
    private int size;
    private boolean orientation;
    private Cell[] boat;

    public Boat(int size, boolean orientation){
        this.size = size;
        this.orientation = orientation;
        boat = new Cell[size];
    }

    public int get_size(){
        return size;
    }

    public void set_size(int size){
        this.size = size;
    }

    public boolean get_orientation(){
        return orientation;
    }

    public void set_orientation(boolean orientation){
        this.orientation = orientation;
    }

    public Cell[] get_boat(){
        return boat;
    }

    public void set_boat(int[] location){
        if (orientation){ // true = horizontal
            for(int i = 0; i < size; i++){
                boat[i] = new Cell(location[0], location[1]+i, 'B');
            }
        } else { // false = vertical
            for(int i = 0; i < size; i++){
                boat[i] = new Cell(location[0]+i, location[1], 'B');
            }
        }
    }

    public boolean boat_status(){ // new helper function checks if boat is sunk
        boolean sunk = true; // true = sunk
        for (Cell pos: boat){
            if (pos.get_status() == 'B'){
                sunk = false; // false = not sunk
            }
        }
        return sunk;
    }

    public boolean contains(Cell other){ // new helper function checks if boat contains cell
        boolean contains = false; // false = does not contain cell
        for (Cell pos: boat){
            if (pos.equals(other)){
                contains = true; // true = contains cell
            }
        }
        return contains;
    }

    public String toString(){ // new helper toString function
        String s = "{";
        for(int i = 0; i < boat.length; i++){
            if (i == boat.length-1){
                s += boat[i] +"}";
            } else {
                s += boat[i] +", ";
            }
        }
        s += " Sunked? "+boat_status();
        return s;
    }
}

// Written by Jeffrey Do, do000043
