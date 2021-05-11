import java.util.Scanner;

/**
 * Main container for the minesweeper game
 */
public class Minesweeper {
    public Grid g; // Every game has a grid

    /**
     * Just one constuctor here to determine the gamemode
     * @param m Enum, mode to determine which difficulty to play
     */
    public Minesweeper(Mode m) {
        switch (m) {
            case EXPERT -> g = new Grid(16, 20, 50);
            case INTERMEDIATE -> g = new Grid();
            case BEGINNER -> g = new Grid(8, 8, 8);
        }
    }

    /**
     * Plays the given instance of minesweeper
     * @return false when the user wants to quit, or true to play again
     */
    public boolean play() {
        Scanner s = new Scanner(System.in);

        while(true) {
            displayGrid(); // Always display grid at beginning of loop

            // Present options
            System.out.println("What next?");
            System.out.println("Options: (U)ncover r c, (F)lag r c, (Q)uit"); // Can be lower or uppercase

            String[] optionString = s.nextLine().split(" ");
            if(optionString[0].length() == 1) {
                char option = optionString[0].charAt(0);

                if(option == 'U' || option == 'u') {
                    if(optionString.length == 3) { // 3 parameters = length of 3, no more no less
                        int row = Integer.parseInt(optionString[1]);
                        int col = Integer.parseInt(optionString[2]); // out of bounds exceptions are caught by a return status of null

                        Status uncoverStatus = g.uncoverSquare(row, col);

                        if(uncoverStatus != null && uncoverStatus.equals(Status.MINE)) { // Mine = game over
                            g.uncoverMines();
                            displayGrid();
                            System.out.println("Better luck next time!\n");

                            System.out.println("Play again (Y)es, (N)o?");
                            char nextOption = s.nextLine().charAt(0);

                            return nextOption == ('Y') || nextOption == ('y');
                        } else if (uncoverStatus != null && uncoverStatus.equals(Status.WIN)) { // Winner!
                            g.uncoverMines();
                            displayGrid();
                            System.out.println("Congrats you win!\n");

                            System.out.println("Play again (Y)es, (N)o?");
                            String nextOption = s.nextLine();

                            return nextOption.charAt(0) == 'Y';
                        } else {
                            if(uncoverStatus == null) // Method will return null if the option is out of bounds
                                System.out.println("\nOption out of bounds");
                        }
                    } else {
                        System.out.println("\nOption incorrectly formatted");
                    }
                } else if(option == 'F' || option == 'f') {
                    if(optionString.length == 3) {
                        int row = Integer.parseInt(optionString[1]);
                        int col = Integer.parseInt(optionString[2]);

                        if((row >= 0 && row < g.getHeight()) && (col >= 0 && col < g.getWidth())) // Square must be in bounds
                            g.flagSquare(row, col);
                        else
                            System.out.println("\nOption out of bounds, try again.");
                    } else {
                        System.out.println("\nOption incorrectly formatted");
                    }
                } else if(option == 'Q' || option == 'q') {
                    return false;
                } else {
                    System.out.println("\nInvalid option, try again");
                }
            } else {
                System.out.println("\nInvalid option, try again");
            }

        }
    }

    /**
     * Displays the grid to the screen
     */
    public void displayGrid() {
        // Loop through height and width
        for (int i = -1; i < g.getHeight(); i++) {
            for (int j = -1; j < g.getWidth(); j++) {
                // Lots of shananigans going on here, tweaked until perfect
                if(i == -1) {
                    if(j == -1) {
                        System.out.print("   ");
                    } else {
                        if(j == g.getWidth()-1) {
                            System.out.print(j + "\n");
                        } else {
                            if(j >= 9)
                                System.out.print(j + " ");
                            else
                                System.out.print(j + "  ");
                        }
                    }
                } else if(j == -1) {
                    if(i <= 9)
                        System.out.print(i + "  ");
                    else
                        System.out.print(i + " ");
                } else {
                    if(j == g.getWidth()-1) {
                        if(g.getGrid()[i][j].isUncovered())
                            System.out.print(g.getGrid()[i][j].getElement() + "\n");
                        else
                            System.out.print("x\n");
                    } else {
                        if(g.getGrid()[i][j].isUncovered())
                            System.out.print(g.getGrid()[i][j].getElement() + "  ");
                        else
                            System.out.print("x  ");
                    }
                }
            }
        }
    }
}
