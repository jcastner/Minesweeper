public class NumberSquare extends Square {
    private int neighborMines, row, col;

    /**
     * Main number square constructor
     * @param neighborMines Amount of adjacent mines
     * @param row y coord of the square
     * @param col x coord of the square
     */
    public NumberSquare(int neighborMines, int row, int col) {
        super((neighborMines == 0 ? "-" : Integer.toString(neighborMines)), false, false);
        this.neighborMines = neighborMines;
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean uncover() {
        if(!isFlagged()) {
            setUncovered();
            return true;
        } else {
            return false;
        }
    }

    public int getNeighborMines() {
        return neighborMines;
    }

    @Override
    public boolean isMine() {
        return false; // Number square is never a mine
    }
}
