public abstract class Square {
    // Private instance variables
    private String element;
    private boolean flagged, uncovered;

    /**
     * Main square constructor, used by mine squres
     */
    public Square() {
        element = "";
        flagged = false;
        uncovered = false;
    }

    /**
     * Secondary square constructor
     * @param element string, should be char, to represent the value of the square
     * @param flagged bool if the square is flagged
     * @param uncovered is the square uncovered
     */
    public Square(String element, boolean flagged, boolean uncovered) {
        this.element = element;
        this.flagged = flagged;
        this.uncovered = uncovered;
    }

    // Below here is mostly getters and setters
    public boolean isFlagged() {
        return flagged;
    }

    public boolean isUncovered() {
        return uncovered;
    }

    /**
     * Wish I had time to do the exceptions here :(
     */
    public void flagSquare() {
        flagged = !flagged;

        if(flagged) {
            element = "f";
            uncovered = true;
        } else {
            element = "";
            uncovered = false;
        }

    }

    public void setUncovered() {
        uncovered = true;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getElement() {
        return element;
    }

    // Abstract methods
    public abstract boolean uncover();
    public abstract boolean isMine();
}
