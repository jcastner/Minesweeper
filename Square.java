public abstract class Square {
    private String element;
    private boolean flagged, uncovered;

    public Square() {
        element = "";
        flagged = false;
        uncovered = false;
    }

    public Square(String element, boolean flagged, boolean uncovered) {
        this.element = element;
        this.flagged = flagged;
        this.uncovered = uncovered;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public boolean isUncovered() {
        return uncovered;
    }

    public void flagSquare() {
        flagged = true;
    }

    public void setUncovered() {
        uncovered = true;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public abstract boolean uncover();
    public abstract boolean isMine();
}
