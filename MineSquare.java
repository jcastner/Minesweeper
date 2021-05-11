public class MineSquare extends Square {
    /**
     * Uncovers the given mine
     * @return bool if the mine was uncovered or not
     */
    @Override
    public boolean uncover() { // Didn't really use the bool implementation and also set the element of the square elsewhere
        if(!isFlagged()) {
            setUncovered();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isMine() {
        return true;
    } // A mine square is always a mine
}
