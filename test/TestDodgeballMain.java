import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import org.junit.Assert.*;
import org.junit.jupiter.api.Test;


public class TestDodgeballManager {
    
		// Standard case: (testDodgeballManagerStandardCase) 2 string lists containing names of throwers and names of dodgers, 
		// test if the constructor properly constructs 2 linked lists with names from string list position in the corresponding node. 
		@Test 
		public void testDodgeballManagerStandardCase() {
		
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
			
			assertTrue(throwernode.next.name.equals("Thomas"));
			assertTrue(dodgernode.next.next.next.name.equals("Ape"));
		}
		// Extreme case: (testDodgeballManagerLarge) 2 very long string lists containing names of throwers and names of dodgers,
		// test if the constructor properly constructs 2 linked lists with names from string list position in the corresponding node.
		@Test
		public void testDodgeballManagerLarge() {
			List<String> initialThrowers = Arrays.asList("1Matt", "2Thomas", "3Abhinav", "4John", "5a", "6b", "7c", "8a", "9a", "10d", "11h", "12hi", "13asdf", "14a", "end");
			List<String> initialDodgers = Arrays.asList("1Steve", "2Bob", "3Monkey", "4Ape", "5a", "6a", "7asdf123", "8qwerrty", "9dasda", "10a", "11gggsg", "12a", "13a", "14a", "secondend");
			
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
			assertTrue(throwernode.next.next.next.next.next.next.next.next.next.next.next.next.next.next.name.equals("end"));
			assertTrue(dodgernode.next.next.next.next.next.next.next.next.next.next.next.next.next.next.name.equals("secondend"));
		}
		
		// Extreme case: (testDodgeballManagerOneNode) 2 string lists containing names of throwers and names of dodgers that are only 1 name,
		// test if the constructor properly constructs 2 linked lists both with only 1 node, therefore test if the next node is null.
		@Test
		public void testDodgeballManagerOneNode() {
			List<String> initialThrowers = Arrays.asList("Matt");
			List<String> initialDodgers = Arrays.asList("Steve");
			
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
			
			assertEquals(null, throwernode.next);
			assertEquals(null, dodgernode.next);
		}
		
		// Error case: (testDodgeballManagerEmptyList) 2 string lists that are empty,
		// the program should throw an illegalArguementException as the list cannot be empty.
		@Test (expected = IllegalArgumentException.class)
		public void testDodgeballManagerEmptyList() {
			List<String> initialThrowers = Arrays.asList();
			List<String> initialDodgers = Arrays.asList();
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
		}		
		
		// Error case: (testDodgeballManagerNullList) 2 string lists that are Null,
		// the program should throw an illegalArguementException as the list cannot be null.
		@Test (expected = IllegalArgumentException.class)
		public void testDodgeballManagerNullList() {
			List<String> initialThrowers = null;
			List<String> initialDodgers = null;
			
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
		}				
		
		//Standard case: (testDodgeStandardCase) thrower and dodger names in list, check if score goes up for dodger
		@Test
		public void testDodgeStanardCase() {
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
			
			test.dodge("Thomas", "Steve");
			assertEquals(dodgernode.score, 1);
			
		}	
		
		// Error case: (testDodgeNullName) thrower name is null, therefore
		// an exception should be thrown illegalArguementException as the name cannot be null
		@Test (expected = IllegalArgumentException.class)
		public void testDodgeNullName() {
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			
			test.dodge(null, "Bob");
		}
		
		//Error case: (testDodgeEmptyStringName) thrower name is a blank string, 
		//therefore an exception should be thrown illegalArguementException as the name cannot be a blank string
		@Test (expected = IllegalArgumentException.class)
		public void testDodgeEmptyStringName() {
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			
			test.dodge("", "Bob");
		}
		
		//Error case: (testHitEmptyStringName) thrower name is a blank string, 
		//therefore an exception should be thrown illegalArguementException as the name cannot be a blank string
		@Test (expected = IllegalArgumentException.class)
		public void testHitEmptyStringName() {
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			
			test.hit("", "Bob");
		}
		
		//Error case: (testHitNullName) thrower name is null, therefore
		// an exception should be thrown illegalArguementException as the name cannot be null
		@Test (expected = IllegalArgumentException.class)
		public void testHitEmptyNullName() {
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			
			test.hit(null, "Bob");
		}
		
		// Standard case: Check if score goes up for thrower whos name is now in dodger list
		// and Check if thrower gets swapped to dodger and doger gets swapped to thrower.
		@Test
		public void testHitStanardCase() {
			List<String> initialThrowers = Arrays.asList("Matt", "Thomas", "Abhinav", "John");
			List<String> initialDodgers = Arrays.asList("Steve", "Bob", "Monkey", "Ape");
			
			DodgeballManager test = new DodgeballManager(initialThrowers, initialDodgers);
			DodgeballNode throwernode = test.throwerFirstNode;
			DodgeballNode dodgernode = test.throwerFirstNode;
			
			test.hit("Matt", "Bob");
			assertEquals(dodgernode.score, 1);
			
			assertTrue(dodgernode.next.name.equals("Matt"));
			assertTrue(throwernode.name.equals("Bob"));
    }
}