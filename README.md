# MSCI 240 - Data Structures & Algorithms: Dodgeball Assignment

This project utilizes linked lists to create an interactive game of Dodgeball.

## Introduction:
The purpose of this report is to explain the testing strategy and implementation of the methods created in this the DodgeballManager class. This report will also discuss any issues that were uncovered in completing the project. 
The entire program is tested through the DodgeballMain class to ensure basic functionality of the program as a whole. A high number of JUnit tests for DodgeballManager constructor, dodge method, and the hit method are used to test the standard, extreme, and error cases of each method/constructor individually. This will be outlined using an input and expected output format. This is to cover all behaviour of the methods and make sure it works fluently with all types of cases. This section will also describe generally how the other methods were tested to ensure functionality. The JUnit tests will also follow a particular naming convention to provide linearity between tests. The code for these JUnit tests will be submitted. 
The implementation section of this report will discuss the implementation of the methods and constructor that were created to make the DodgeballManager class. This will explain the DodgeballManager constructor, dodge method, and the hit method and will consist of outlining the logic used when creating these methods and constructors, and explaining the syntax of them. The implementation is tested by the testing plan to ensure its functionality. 

## The Test Plan: 
### Strategy:
This plan outlines the exact cases used to test the constructor and each method. The test plan tests the constructor and methods of the DodgeballManager class using standard, extreme, and error cases. The error cases will test when Exceptions should be thrown. These normal, extreme, and error cases ensure the functionality required. 
The DodgeballManager constructor, dodge method, and hit method will be tested individually using JUnit test cases. Here is the breakdown of how they will be tested per method/constructor:
•	JUnit test: Standard case
•	JUnit test: Extreme cases (can be both edge cases)
•	JUnit tests: Error cases (when Exceptions should be thrown)
These JUnit tests will be fully explained for what the case is testing, and the input and expected output will be outlined. The code for these JUnit tests will be submitted.
There is a naming convention used for these JUnit tests to provide linearity between tests. This will be “test{method}{short name for what case if being tested}” With {method} being the method that is being testing and with {short name for what case if being tested} being whatever case is being tested.
The methods in the DodgeballManager class (including the DodgeballManager constructor, dodge method, and hit method) are all tested through the DodgeballMain class to ensure basic functionality of the program as a whole. 

### JUnit Tests:
#### DodgeballManager constructor:
- Standard case: (testDodgeballManagerStandardCase) 2 string lists containing names of throwers and names of dodgers, test if the constructor properly constructs 2 linked lists with names from string list position in the corresponding node. 
- Extreme case: (testDodgeballManagerLarge) 2 very long string lists containing names of throwers and names of dodgers, test if the constructor properly constructs 2 linked lists with names from string list position in the corresponding node.
- Extreme case: (testDodgeballManagerOneNode) 2 string lists containing names of throwers and names of dodgers that are only 1 name, test if the constructor properly constructs 2 linked lists both with only 1 node, therefore test if the next node is null.
- Error case: (testDodgeballManagerEmptyList) 2 string lists that are empty, the program should throw an illegalArguementException as the list cannot be empty.
- Error case: (testDodgeballManagerNullList) 2 string lists that are Null, the program should throw an illegalArguementException as the list cannot be null.
#### Dodge method:
- Standard case: (testDodgeStandardCase) thrower and dodger names in list, check if score goes up for dodger
- Error case: (testDodgeNullName) thrower name is null, therefore an exception should be thrown illegalArguementException as the name cannot be null
- Error case: (testDodgeEmptyStringName) thrower name is a blank string, therefore an exception should be thrown illegalArguementException as the name cannot be a blank string
#### Hit method:
- Standard case: Check if score goes up for thrower whos name is now in dodger list and Check if thrower gets swapped to dodger and doger gets swapped to thrower.
- Error case: (testHitNullName) thrower name is null, therefore an exception should be thrown illegalArguementException as the name cannot be null
- Error case: (testHitEmptyStringName) thrower name is a blank string, therefore an exception should be thrown illegalArguementException as the name cannot be a blank string
#### A short section describing testing strategy at a high level for other methods:
The other methods were tested through using the DodgeballMain class and running the DodgeballManager through that to see the code properly runs and is completely functional. For example, we can see that the Printer Throwers and Print Dodgers methods are fully functional and work completely through the scoreboard is displayed that shows each score per name and in different positions, and that changes every time a hit or dodge happens in the game. We can also see that the get maximum method is functional as it is able to see that the maximum has been achieved and the print winner method works as it displays the player who has achieved the maximum score and won the game, as well as the throwers contains and dodgers contains methods. 

## Implementation:
The overall implementation of this class was through breaking down the logic of linked lists and using helper methods to reduce redundant code and simplify the process (as majority of the methods have to be run for both the throwers and the dodgers, therefore making the program 2 times more efficient)
### DodgeballManager Constructor:
The DodgeballManager Constructor takes in parameters of 2 string lists and creates a linked list version of these lists at the respective nodes for the index in the list. This was implemented using a helper method called the linkedListCreator. The Dodgeball Managaer constructor first checks if the size is 0 or Null for either the initial throwers or dodgers as if it is it must throw an exception. If not, the constructor moves onto creating the first node of both lists with that node being equal to the first index string in the lists. Then it checks if the lists have a size greater than 1, as if it does not then the linkedlist is already finished in construction. If it is, then the constructor call the helper method linkedListCreator which takes in the first node of the linked list and the string list. This is called for both throwers and dodgers. The method sets up a fence post by creating the last node first at index of the last element in string list. The method then points the first node to the last node to set up the linked list. There is then a for loop that iterates backwards through the string list without the first or last element. It starts at the 2nd last element and creates a new node with this term, pointing it towards what the first node is pointing at. Then the first node is pointed at the new node just created to create a chain first  new node  last. The for loop repeats this process to keep adding new nodes in the middle of the list (pointing at what the first is pointing to, then point the first to the new node) and creates a full linked list. The linked list is now constructed.
### Dodge method:
The dodge method takes in parameters of 2 strings, 1 is the string name of the thrower, and the other is the string name of the dodger. This method increases the dodgers score as they have dodged the throwers throw.  The method first has an if statement that checks if the parameters are not in there respective linked lists by using the contains method or if either parameter name is empty string or null. If any of this is true, the method throws an illegalarguementexception because the thrower name is not in the thrower list, or the dodger name is not in the dodger list, or either name is empty or null. If this is not true, the method continues to setting a pointer to the first node and using a while loop to iterate through the dodger linkedlist. The method checks if the name inputted is equal to the parameter string name for the dodger, and if it is, it increases that current nodes score. 
### Hit method:
The hit method takes in 2 parameters of 2 strings, 1 is the string name of the thrower, and the other is the string name of the dodger. This methods purpose is to increase the score of the thrower, and swap the throwers position with the dodger. This method does functionally work completely, however unfortunately it does this the incorrect way, by changing the name and score fields instead of changing the node pointers and references. The method does this by using 3 helper methods, the swapNumber, swap, and scoreIncrease methods. The hit method first has an if statement that checks if the parameters are not in there respective linked lists by using the contains method or if either parameter name is empty string or null. If any of this is true, the method throws an illegalarguementexception because the thrower name is not in the thrower list, or the dodger name is not in the dodger list, or either name is empty or null. If not, then the method calls the swapNumber helper method with parameters the first node and the name. This is called for both throwers and dodgers to find at what index is that player name in the respective linked list. The method iterates through throwers and dodgers, finds when parameter name = name in linked list and have a counter adding up as it goes through the linkedlist. These numbers are returned back the the hit method and then the next helper method swap that takes in the parameters the first node, the name from the opposite list and the number from the swapNumber method (thrower node takes dodger name and thrower swap number). In that helper method swap theres a while loop with a new counter to iterate through the linked list, if newcounter == oldcounter, make name = oppositename (dodger name in the thrower list), and score = opposite score (dodger score in the thrower list). The program then calls the the helper method increaseScore that brings in the dodger first node and the name of the thrower that hit, and increases their score by iterating through the dodger linked list and finding where the name is equal to the original throwers name (because now the thrower has moved to the dodger list). 

## Issues Encountered:
The largest issue I encountered while creating this project was the hit method. This is because the hit method that I created is incorrect. Although the method does functionally work completely, however it does this the incorrect way, by changing the name and score fields instead of changing the node pointers and references. I understand that this is the incorrect way to create this method, as I know I cannot modify the name field outside of the constructor, however I felt I did not have enough time to be able to create hit method in the way that the course wants. This was due to my poor time management skills, however I wanted to create a method that still functionally works and is still using the ideas from this course. 
Another issue I encountered was continually making redundant code, as I usually have to make the same code for both throwers and dodgers. This got very confusing, and was poor coding practice. To solve this problem, I created helper methods can take in the parameters of the either thrower or dodgers and reduce the code by 50%. 
The last issue I encountered was that the DodgeballManager Constructor was difficult write the code for. I found it hard to think of the logic for this problem, so I wrote it out on paper to solve this problem which made it much easier to solve. 
