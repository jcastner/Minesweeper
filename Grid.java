import java.util.Random; // Importing random class for random coords

/**
 * Represents the 2d array where the minesweeper board lies
 */
public class Grid {
    // Private instance variables
    private Square[][] grid; // 2d array of Squares
    private int width, height, numMines, numSquaresUncovered;

    /**
     * Default constructor, for intermediate difficulty
     */
    public Grid() {
        height = 10;
        width = 12;
        numMines = 10;
        numSquaresUncovered = 0;
        grid = new Square[height][width];

        generateGrid(); // Put the grid generation in its own private function because we have to do the same thing in the next constructor
    }

    /**
     * Grid constructor taking 3 parameters
     * @param width number of columns
     * @param height number of rows
     * @param numMines amount of mines on the board
     */
    public Grid(int width, int height, int numMines) {
        this.width = width;
        this.height = height;
        this.numMines = numMines;
        grid = new Square[height][width];

        generateGrid();
    }

    /**
     * Generates the grid based on the given constructor/params
     */
    private void generateGrid() {
        Random rand = new Random();
        int randX, randY;

        // Generate however many mines numMines represents (default 10)
        for (int i = 0; i < numMines; i++) {
            // Generate a random x and y with 0 to width/height as bounds (exclusive)
            randX = rand.nextInt(width);
            randY = rand.nextInt(height);

            // Grid array accessed as follows -> grid[row][col]
            while(grid[randY][randX] != null) { // Make sure grid space is not already occupied
                randX = rand.nextInt(width);
                randY = rand.nextInt(height);
            }

            grid[randY][randX] = new MineSquare(); // Mines represented in a mine square
            System.out.println(randY + " " + randX);
        }

        // Fill in rest of grid with NumSquares
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(grid[i][j] == null || !grid[i][j].isMine()) // If the square is empty and not a mines...
                    grid[i][j] = new NumberSquare(getNeighbors(i, j), i, j); // Create a new number square
            }
        }
    }

    /**
     * Gives the number of adjacent mines on the given coord
     * @param r row/y coord
     * @param c col/x coord
     * @return adjacent mines
     */
    public int getNeighbors(int r, int c) {
        int mines = 0;

        // Check in all directions with a double for loop
        for (int i = r-1; i <= r + 1; i++) {
            for (int j = c-1; j <= c + 1; j++) {
                // Don't check your own square, make sure coords are in bounds, square must not be null if it is a mine!
                if(!(i == r && j == c) && (i >= 0 && i < height) && (j >= 0 && j < width) && !(grid[i][j] == null) && grid[i][j].isMine())
                    ++mines;
            }
        }

        return mines;
    }

    /**
     * Recursive method to uncover a number square, area of empty squares, mine, or win the game
     * @param r row of square
     * @param c col of square
     * @return Status enum to indicate status of the game
     */
    public Status uncoverSquare(int r, int c) {
        if(r < 0 || r >= height || c < 0 || c >= width) // If the square is out of the grid, return
            return null;

        if(grid[r][c].isMine()) // Mine, return mine
            return Status.MINE;

        NumberSquare n = (NumberSquare) grid[r][c]; // Cast as a number square to access neighbor mines

        if(n.getNeighborMines() > 0 && !n.isUncovered()) { // If the mine has a neighbor and is not uncovered...
            n.uncover();
            ++numSquaresUncovered;
        } else { // Otherwise we have a blank square
            if(!n.isUncovered()) { // is the square uncovered yet
                n.uncover();
                ++numSquaresUncovered;

                // Loop through and try to uncover surrounding squares
                for (int i = r-1; i <= r+1; i++) {
                    for (int j = c-1; j <= c+1; j++) { // Try to uncover square in all directions
                        if((i >= 0 && i < height) && (j >= 0 && j < width))
                            uncoverSquare(i, j);
                    }
                }
            }
        }

        if(numSquaresUncovered == (width * height) - numMines) // Width * height - numMines = total squares to be uncovered
            return Status.WIN;

        return Status.OK; // If all goes normally, return OK
    }

    /**
     * Uncovers all the mines on the grid
     */
    public void uncoverMines() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(grid[i][j].isMine()) {
                    grid[i][j].setElement("*");
                    grid[i][j].uncover();
                    System.out.println("Mine uncovered @ " + i + " " + j);
                }
            }
        }
    }

    /**
     * Flags the square at the given row and column
     * @param r row/y of square
     * @param c col/x of the square
     */
    public void flagSquare(int r, int c) {
        grid[r][c].flagSquare();
    }

    /**
     * @return width of grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return Height of the grid
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The grid!
     */
    public Square[][] getGrid() {
        return grid;
    }

    public int getNumSquaresUncovered() {
        return numSquaresUncovered;
    }
}
