import java.util.List;
import java.io.PrintStream;

/*
Matthew Erxleben

MSCI 240 Assignment 2

2021-11-02

This program manages the game of dodgeball, where this is a string list of throwers and a string list of dodgers.
This program constructs linkedlists for both of these lists and has other methods to find values and utilize this list.
These methods include a maximum score method, a method to see if a name exists on either team, and a method to print the scores and names in their respective areas.
The program also tracks the score of through the hit and dodge method, which is the functionallity of how the dodgeball game is conducted.

There is no input to this program as it is an object class
There is no output to this program as it is an object class

*/

public class DodgeballManager {
	DodgeballNode throwerFirstNode = null;
	DodgeballNode dodgerFirstNode = null;

	public void linkedListCreator(DodgeballNode node, List<String> list) {

		// fencepost setting last node equal to last in string list
		DodgeballNode LastNode = new DodgeballNode(list.get(list.size() - 1));
		// point first node to last node
		node.next = LastNode;
		// for loop that goes backwards through the string list, size - 2 because:
		// e.g size = 4, need to go from index (4 - 2 = 2) to 0
		for (int i = (list.size() - 2); i > 0; i--) {
			// create new node at index and point new node to the last middle (or on first
			// iteration to the last node)
			DodgeballNode mid = new DodgeballNode(list.get(i), node.next);
			// point the first node to the 2nd node
			node.next = mid;
		}
	}

	public DodgeballManager(List<String> initialThrowers, List<String> initialDodgers) {
		// TODO method stub
		// throw an IllegalArgumentException if either list is null or has a size of 0
		if (initialThrowers.size() == 0 || initialDodgers.size() == 0) {
			throw new IllegalArgumentException("Size is 0 or list is Null for either the inital throwers or dodgers");
			// multiple throwers or dodgers in string list
		} else {

			throwerFirstNode = new DodgeballNode(initialThrowers.get(0));
			dodgerFirstNode = new DodgeballNode(initialDodgers.get(0));

			// if there is more than 1 element in string list
			if (initialThrowers.size() > 1) {
				linkedListCreator(throwerFirstNode, initialThrowers);
			}
			if (initialDodgers.size() > 1) {
				linkedListCreator(dodgerFirstNode, initialDodgers);
			}
		}
	}

	public void DodgeballPrint(DodgeballNode node, PrintStream stream) {
		// method to print out the elements in the linked list
		// pointer to the first node
		DodgeballNode current = node;
		while (current != null) {
			stream.print(current.name + " " + current.score);
			if (current.next != null) {
				// puts , between each name
				stream.print(", ");
			}
			current = current.next;
		}
	}

	public void printThrowers(PrintStream stream) {
		DodgeballPrint(throwerFirstNode, stream);
	}

	public void printDodgers(PrintStream stream) {
		DodgeballPrint(dodgerFirstNode, stream);
	}

	public int max(DodgeballNode node) {
		// method to find max in a linked list
		// pointer at first thrower node
		DodgeballNode current = node;
		// intialize max to 0
		int max = 0;

		// loop to iterate through the linkedlist and save the top score, compare it to
		// the last one and if higher replace it
		while (current != null) {
			if (current.score > max) {
				max = current.score;
			}
			current = current.next;
		}
		return max;
	}

	public int getMaximumScore() {
		int maxthrower = max(throwerFirstNode);
		int maxdodger = max(dodgerFirstNode);

		// if thrower max is highest or they are equal then return that number as max
		if (maxthrower >= maxdodger) {
			return maxthrower;
			// return maxdodger
		} else {
			return maxdodger;
		}
	}

	public int numberOfWinners(DodgeballNode node, int max, int winners) {
		// pointer at first node
		DodgeballNode current = node;
		while (current != null) {
			// if the persons score is the max
			if (current.score == max) {
				// if theres already a winner
				if (winners == 1) {
					throw new IllegalStateException("There is more than 1 person with the maximum score!");
					// no winner yet
				} else {
					// increase counter
					winners++;
				}
			}
			current = current.next;
		}
		return winners;
	}

	public String winnerName(DodgeballNode node, int max) {
		// if not a winner than name = empty string
		String name = "";
		DodgeballNode current = node;
		while (current != null) {
			// if a winner
			if (current.score == max) {
				// save the name of the winner
				name = current.name;
			}
			current = current.next;
		}
		return name;
	}

	public void printWinner(PrintStream stream) {
		// bring in winners as int = 0 as paramater into count winners simplifier method
		// count up winners for each winner, throw expection if over 1
		// returns # of winners, uses that as a paramater for when you call it for
		// dodgers as the WINNERS
		// use other method to get max score
		int max = getMaximumScore();
		int winners = 0;
		// run number of winners method on throwers, that returns the winners found in
		// throwers then run that as the winners parameter for dodgers
		winners = numberOfWinners(dodgerFirstNode, max, (numberOfWinners(throwerFirstNode, max, winners)));
		String name = winnerName(throwerFirstNode, max);
		String name2 = winnerName(dodgerFirstNode, max);
		stream.println("The winner is " + name + name2 + " with " + max + " points");
	}

	public boolean contains(DodgeballNode node, String name) {
		// bring inputted name to all lower case to compare
		// iterate through and compare (put each node name to lower case, then compare
		// it to the new lowercased input name)

		// put inputted name to lowercase
		name = name.toLowerCase();
		DodgeballNode current = node;
		while (current != null) {
			// put name in linked list to lowercase
			String dodgeballname = current.name.toLowerCase();

			// compare the 2 names
			if (dodgeballname.equals(name)) {
				// name is in the linked list
				return true;
			}
			current = current.next;
		}
		// name is not in linked list
		return false;
	}

	public boolean throwersContains(String name) {
		// call method to find if name is in linked list
		return contains(throwerFirstNode, name);
	}

	public boolean dodgersContains(String name) {
		// call method to find if name is in linked list
		return contains(dodgerFirstNode, name);
	}

	public void dodge(String thrower, String dodger) {
		// use contains method to see if thrower is in thrower list and if dodger is in
		// dodger list
		if (contains(throwerFirstNode, thrower) == false || contains(dodgerFirstNode, dodger) == false
				|| thrower == null || thrower == "" || dodger == null || dodger == "") {
			throw new IllegalArgumentException(
					"Thrower name is not in the thrower list, or the dodger name is not in the dodger list, or either name is empty or null.");
		}

		DodgeballNode current = dodgerFirstNode;

		while (current != null) {
			if (current.name.equals(dodger)) {
				current.score += 1;
				break;
			}
			current = current.next;
		}
	}

	public int swapNumber(DodgeballNode node, String name) {
		// finds number to swap to
		int counter = 0;
		DodgeballNode current = node;

		while (current != null) {
			// increase counter each node
			counter++;
			// if the name at this node is equal to the given name
			if (current.name.equals(name)) {
				// then return the number it happened at in the linked list
				return counter;
			}
			current = current.next;
		}
		return counter;
	}

	public void swap(DodgeballNode node, String swapToName, int swapToNum) {
		// initialize counter to 0
		int counter = 0;
		// pointer to first node
		DodgeballNode current = node;

		while (current != null) {
			counter++;
			// if the counter is at the counter number to swap to, swap the names
			if (counter == swapToNum) {
				// swap the names
				current.name = swapToName;
			}
			current = current.next;
		}
	}

	// go through the dodger list and find where the throwers name is, add a point
	// to their score in the dodger list
	public void scoreIncrease(DodgeballNode node, String name) {
		DodgeballNode current = node;

		while (current != null) {
			// if the throwers name is in the dodger list
			if (current.name.equals(name)) {
				// add 1 score to their score
				current.score += 1;
				break;
			}
			current = current.next;
		}
	}

	public void hit(String thrower, String dodger) {
		// iterate through throwers, find when name = name in linked list. Have a
		// counter adding up as it goes through the linkedlist.
		// iterate through dodgers, find when name = name in linked list. Have a counter
		// adding up as it goes through the linkedlist.
		// seperate while loop for both thrower and dodger , and create a new counter,
		// if newcounter == oldcounter, make name = old name, and score = old score

		if (contains(throwerFirstNode, thrower) == false || contains(dodgerFirstNode, dodger) == false
				|| thrower == null || thrower == "" || dodger == null || dodger == "") {
			throw new IllegalArgumentException(
					"Thrower name is not in the thrower list, or the dodger name is not in the dodger list, or either name is empty or null.");
		}
		// call methods to find index at which to swap to
		int throwerSwapNum = swapNumber(throwerFirstNode, thrower);
		int dodgerSwapNum = swapNumber(dodgerFirstNode, dodger);

		// call methods to swap names
		swap(throwerFirstNode, dodger, throwerSwapNum);
		swap(dodgerFirstNode, thrower, dodgerSwapNum);
		// call method to add score to the thrower who hits score
		scoreIncrease(dodgerFirstNode, thrower);

	}

	public void printSortedScores(PrintStream stream) {

	}
}