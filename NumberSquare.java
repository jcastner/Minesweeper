public class NumberSquare extends Square {
    private int neighborMines, row, col;

    public NumberSquare(int neighborMines, int row, int col) {
        this.neighborMines = neighborMines;
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean uncover() {
        if(!isFlagged()) {
            setUncovered();
            setElement(); // Max #?
        }
    }

    public int getNeighborMines() {
        return neighborMines;
    }

    @Override
    public boolean isMine() {
        return false;
    }
}
