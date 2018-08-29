/*  Program name: Eliza.java
    Author: Jeremy Oliver
    Date: January 20th 2018
    
    Description: Eliza converses with a user in terms similar to a psychologist.
    
    Input: user from the command line, or file
    Output: screen (console), or file

*/

import java.util.*;  // ArrayList, Random
import java.io.*;    // Scanner

public class Eliza {
 
	private ArrayList<String> generalAnswers,  // AL of "unintelligent" answers
                              memory,          // AL of future notes from prior answers
							 yesAnswers,		  // AL of "yes" answers
							 noAnswers,       // AL of "no" answers
							 youAnswers;      // AL of "you" answers
	private Scanner   keyboard;                // Source of userInput
	private String    userName,                // User's name
	                  userAnswer,              // String that holds user's answer
		              ElizaAnswer;             // String that hold's Eliza's answer
	private Random    randomGenerator;         // Invokes Random method
	private int       answerIndex;             // random selector for Eliza responses
	
/* Method Eliza */
	public Eliza () {

	  // Instantiate ArrayLists
	  generalAnswers = new ArrayList<String>(25);
      memory         = new ArrayList<String>(25);
      yesAnswers     = new ArrayList<String>(5);
      noAnswers      = new ArrayList<String>(5);
      youAnswers     = new ArrayList<String>(5);

       // Calls method to fill general answers
	   fillGeneralAnswers();
	   
	   // **Calls method to fill yes answers (Array List)
	   fillYesAnswers();
	   
	   // **Calls method to fill no answers (Array List)
	   fillNoAnswers();
	   
	   // **Calls method to fill you answers (Array List
	   fillYouAnswers();
	   

      keyboard = new Scanner (System.in);
	  randomGenerator = new Random ();
   
	}  // end default constructor
	
	// Fills generalAnswers array. For when Eliza cannot pick a specific response.
	public void fillGeneralAnswers () {
	   generalAnswers.add("That's interesting.");
	   generalAnswers.add("Tell me more about that.");
	   generalAnswers.add("Really?");
	   generalAnswers.add("I think you should elaborate");
	   generalAnswers.add("That is slightly concerning");
	   generalAnswers.add("How about them Broncos?");
	   generalAnswers.add("I wish I understood what you are thinking");
	   generalAnswers.add("I think ribbon is far superior to lace");
	   generalAnswers.add("Tell me about your fears.");
	   generalAnswers.add("Wow, I never thought about that.");
	   generalAnswers.add("Can you explain that?");
	   generalAnswers.add("How does that make you feel?");
	   generalAnswers.add("How is your relationship with your father?");
	   generalAnswers.add("What do you like to do for fun?");
	   generalAnswers.add("I think this is just a random selection of answers.");
	   generalAnswers.add("I'm afraid that I'm just a computer");
	   generalAnswers.add("Can you share some more about yourself?");
	   generalAnswers.add("I think you are spectacular!");
	   generalAnswers.add("You are a beautiful soul.");
	   generalAnswers.add("Don't let anybody tell you that you aren't a Princess");
	   generalAnswers.add("Hmm... That's boring... *yawn*");
	   generalAnswers.add("Next topic please.");
	   generalAnswers.add("You're a strong, beautiful person");
	   generalAnswers.add("Keep fighting the good fight!");
	   generalAnswers.add("I wish you the best in all your endeavors");
	} // End fill generalAnswers
	
	// Fills yesAnswers array for when a user says "yes" in their answer
	public void fillYesAnswers () {
		yesAnswers.add("Yes? Me Too!");
		yesAnswers.add("Yay! That's great");
		yesAnswers.add("I like positivity");
		yesAnswers.add("I'm a yes woman myself");
		yesAnswers.add("I like your attitude");
	} // End fill yesAnswers

	// Fills noAnswers array foe when a user says "no" in their answer
	public void fillNoAnswers() {
		noAnswers.add("I don't like your attitude");
		noAnswers.add("Do you kiss your Mother with that mouth?");
		noAnswers.add("you should try being more positive");
		noAnswers.add("Why do you say no?");
		noAnswers.add("I like to look on the positive side of life.");
	} // End fill noAnswers array
	
	// Fills youAnswers array. For use when the use says "you" in their answer.
	public void fillYouAnswers() {
		youAnswers.add("I am Eliza");
		youAnswers.add("I like talking to you");
		youAnswers.add("Me? How kind of you to mention me");
		youAnswers.add("I never really thought about that before");
		youAnswers.add("I'm flattered");
	} // End fill youAnswers array.

	public void talk () {
	   
	   // intro to user
	   System.out.println ("Welcome to Eliza - your talking computer friend.");
	   System.out.print ("\nWhat's your name?\n>> ");
		
	   userName = keyboard.nextLine ();
	   System.out.print ("Hi, " + userName + ", " +
	                     "let's chat. \nHow are you today?\n>> ");
										
	   // main conversation loop 
	   
	   do {

		   // user speaks
		   userAnswer = keyboard.nextLine();
		   // System.out.println ("Input: " + userAnswer);  // debugging

		   // check for memory words related to family and remember category
		   if (userAnswer.contains("mother") ||
               userAnswer.contains("father") ||
		       userAnswer.contains("sister") ||
               userAnswer.contains("brother")  ) {
		       memory.add("family");
		   }  // end check for family words

           // check for memory words related to work and remember category
		   if (userAnswer.contains("work") ||
			   userAnswer.contains("job") ||
			   userAnswer.contains("office") ||
			   userAnswer.contains("boss") ||
		       userAnswer.contains("profession")) {
		       memory.add("work");
		   }

          // check for memory words related to school
		   if (userAnswer.contains("school") ||
				   userAnswer.contains("homework") ||
				   userAnswer.contains("assignment") ||
				   userAnswer.contains("professor") ||
			       userAnswer.contains("essay")) {
			       memory.add("school");
		   }
		   
         // Eliza speaks - choose a specific answer if possible, otherwise give a generic answer

		   if (userAnswer.length() < 3 && !userAnswer.substring(0, 2).equalsIgnoreCase("no")) {
	   			  System.out.print("I don't understand. ");
		   }
		    // did the user say "bye"? program ends
		   else if (userAnswer.equalsIgnoreCase("Bye")) {
   			ElizaAnswer = "It was nice talking to you, " +
   		                  userName + ".  Have a nice day.";
   		}  // end Bye

   		// did the user say "I am" something?
   		  else if (userAnswer.length() > 3 && userAnswer.substring(0,4).equalsIgnoreCase("I am")
   				  || userAnswer.contains("I am")) {
   			  ElizaAnswer = "Why are you " + userAnswer.substring(5) + "?";
   		}  // end I am

		// did the user say "I think" something?
   		  else if (userAnswer.length() > 6 && userAnswer.substring(0,7).equalsIgnoreCase("I think")
   				  || userAnswer.contains("I think")) {
   			  ElizaAnswer = "Why are you thinking? It's dangerous";
   		  } //end I think
   		
		// did the user say "I feel" something?
   		  else if (userAnswer.length() > 5 && userAnswer.substring(0,6).equalsIgnoreCase("I feel") 
   				  || userAnswer.contains("I feel")) {
   			  ElizaAnswer = "Feelings can be a blessing and a curse";
   		  } // End I feel

		// did the user say "no"?
           else if (userAnswer.substring(0,2).equalsIgnoreCase("no") || userAnswer.contains("no")) {
            	   answerIndex = randomGenerator.nextInt (noAnswers.size());
            	   ElizaAnswer = noAnswers.get(answerIndex);
   		}  // end negative 

   		// did the user say "yes"?
           else if (userAnswer.substring(0, 3).equalsIgnoreCase("yes") || userAnswer.contains("yes")) {
        	   answerIndex = randomGenerator.nextInt (yesAnswers.size());
        	   ElizaAnswer = yesAnswers.get(answerIndex);
           } // end positive

   		// did the user say "you"?
           else if (userAnswer.substring(0,3).equalsIgnoreCase("you") || userAnswer.contains("you")) {
        	   answerIndex = randomGenerator.nextInt(youAnswers.size());
        	   ElizaAnswer = youAnswers.get(answerIndex);
           } // end you

   		// did Eliza remember something from an earlier answer?
   		  else if (memory.size() > 0) {
   		   ElizaAnswer = "Tell me more about " + memory.get(0);
   		   memory.remove(0);		   
   		} // end check memory
   
   		// else random answer
   		  else {  
        	   answerIndex = randomGenerator.nextInt (generalAnswers.size());
            ElizaAnswer = generalAnswers.get(answerIndex);  
   		}  // end random answer
		 
        

   		System.out.print (ElizaAnswer + "\n>> ");	
	   
      } while (!userAnswer.equalsIgnoreCase("Bye")); // loop until "bye"

	}  // end talk

   public static void main (String args[]) {
      Eliza talkingEliza = new Eliza();
      talkingEliza.talk();
   }  // main
	
}  // end Eliza

