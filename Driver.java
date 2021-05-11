import java.util.Scanner; // importing scanner to collect user input

/**
 * Class to run minesweeper
 */
public class Driver {
    public static void main(String[] args) {
        boolean playAgain = true; // Bool to control if the user wants to play again

        while(playAgain) {
            Scanner keeb_in = new Scanner(System.in); // Declare scanner object

            // Printing menu
            System.out.println("Welcome to minesweeper, begin by selecting a level.");
            System.out.println("(1) Beginner");
            System.out.println("(2) Intermediate");
            System.out.println("(3) Expert");
            System.out.println("(q) Quit");

            // Start as string, convert to char to use less memory
            String level_choice_og = keeb_in.nextLine();

            if(level_choice_og.length() == 1) { // Input can only be one character
                char level_choice = level_choice_og.charAt(0);

                // Validating level choice
                while(level_choice != '1' && level_choice != '2' && level_choice != '3' && level_choice != 'q' && level_choice != 'Q') {
                    System.out.println("\nInvalid choice, please try again");
                    System.out.println("(1) Beginner");
                    System.out.println("(2) Intermediate");
                    System.out.println("(3) Expert");
                    System.out.println("(q) Quit");
                    level_choice = keeb_in.nextLine().charAt(0);
                }

                if(!(level_choice == 'q') && !(level_choice == 'Q')) {
                    Minesweeper m;

                    switch (level_choice) {
                        case '1' -> m = new Minesweeper(Mode.BEGINNER);
                        case '2' -> m = new Minesweeper(Mode.INTERMEDIATE);
                        case '3' -> m = new Minesweeper(Mode.EXPERT);
                        default -> m = null;
                    }

                    if(m != null) // Not really possible but in case something goes wrong, don't start a null game
                        playAgain = m.play();
                } else {
                    playAgain = false;
                }
            } else {
                System.out.println("Invalid input, please try again\n");
            }
        }
    }
}
