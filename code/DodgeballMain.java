// Instructor-provided testing program.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class DodgeballMain is the main client program for
 * dodgeball management. It reads names from a file
 * dodgeball.txt, allocates them into throwers and dodgers, and uses them to start the
 * game. The user is asked for a thrower name, a dodger name,
 *  then whether the throw was a hit (h) or a dodge (d) until the game ends
 *  with a certain score
 */
public class DodgeballMain {
    /** input file name from which to read data */
    public static final String INPUT_FILENAME = "dodgeball.txt";

    /**
     * true for different results every run; false for
     * predictable results
     */
    public static final boolean RANDOM = false;

    /**
     * If not random, use this value to guide the sequence
     * of numbers that will be generated by the Random
     * object.
     */
    public static final int SEED = 99;
    
    /**
     * The score to play to in the game
     */
    public static final int N_SCORE = 3;
    
    /**
     * the number of throwers in the game (determined randomly)
     */
    public static int nThrowers;

    public static void main(String[] args)
            throws FileNotFoundException {
    	//open input file
    	File inputFile = new File(INPUT_FILENAME);
        if (!inputFile.canRead()) {
            System.out.println(
                    "Required input file not found; exiting.\n"
                            + inputFile.getAbsolutePath());
            System.exit(1);
        }
        Scanner input = new Scanner(inputFile);
        
        //read in the names and store into a list
        List<String> names = new LinkedList<String>();
        while (input.hasNextLine()) {
        	names.add(input.nextLine().strip());
        }
        input.close();

        //randomly choose how many people to put into throwers
        Random rand = RANDOM ? new Random()
                : new Random(SEED);
        
        nThrowers = rand.nextInt(names.size()-2) +1;
        List<String> throwers = new LinkedList<String>();
        List<String> dodgers = new LinkedList<String>();

        //partition list into throwers and dodgers
        int i = 0;
        for(String s : names) {
        	if (i < nThrowers) {
        		throwers.add(s);
        	} else {
        		dodgers.add(s);
        	}
        	i++;
        }
        
        
        //setup the Dodgeball Manager and start running the game
        DodgeballManager manager = new DodgeballManager(throwers, dodgers);

        // prompt the user for actions until the target score is reached
        Scanner console = new Scanner(System.in);
        while (manager.getMaximumScore() < N_SCORE) {
            oneThrow(console, manager);
        }

        // report the winner
        System.out.println(
                "Winner:");
        manager.printWinner(System.out);
        System.out.println();
        
        // report sorted scores (OPTIONAL BONUS)
        System.out.println();
        System.out.println("All scores, in order:");
        manager.printSortedScores(System.out);
    }

   
	/**
     * Handles the details of handling one throw.
     * Shows the current dodgeball positions,
     * prompts the user for a thrower name,
     * prompts the user for a target,
     * then prompts the user for whether the throw was a hit or a dodge
     */
    public static void oneThrow(Scanner console,
            DodgeballManager manager) {
        // print both linked lists
        System.out.print("Throwers: ");
        manager.printThrowers(System.out);
        System.out.println();
        System.out.print("Dodgers: ");
        manager.printDodgers(System.out);
        
        // prompt for thrower
        System.out.println();
        System.out.print("Thrower name? ");
        String throwerName = console.nextLine().trim();
        while(!manager.throwersContains(throwerName))
        {
        	System.out.println("Not a thrower.");
        	System.out.println();
            System.out.print("Thrower name? ");
            throwerName = console.nextLine().trim();
        }
        
        //prompt for Dodger
        System.out.println();
        System.out.print("Dodger name? ");
        String dodgerName = console.nextLine().trim();
        while(!manager.dodgersContains(dodgerName))
        {
        	System.out.println("Not a dodger.");
        	System.out.println();
            System.out.print("Dodger name? ");
            dodgerName = console.nextLine().trim();
        }
       
        //prompt for hit/dodge
        System.out.println();
        System.out.print("Was it a hit (h) or dodge (d)? ");
        String choice = console.nextLine().trim().toLowerCase();
        
        //handle the hit or dodge
        if (choice.startsWith("h")) {
            manager.hit(throwerName, dodgerName);
        } else if (choice.startsWith("d")) {
            manager.dodge(throwerName, dodgerName);
        } else {
        	System.out.println("Could not recognize action '" + choice + "'" );
        }
        System.out.println();
    }

}