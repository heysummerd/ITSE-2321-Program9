//********************************************************************
//
//Author:        Summer Davis
//
//Program #:     Nine
//
//File Name:     Program9.java
//
//Course:        ITSE 2321 Object-Oriented Programming
//
//Description:   Program9 leverages the HouseHold class to accomplish the following:
//				 - Read data from text file Program9.txt and create arraylist of household objects
//				 - Print data of each household object (using a four-column header)
//					--> ID Number (4 digit integer)
//					--> Annual Income 
//					--> Number of Household Members
//					--> State
//				 - Calculates and prints average household income
//				 - Prints data for each household object that exceeds the average income (using a four-column header)
//				    --> ID Number
//					--> Annual Income
//				    --> Number of Household Members
//				    --> State
//				 - Calculate poverty levels using the following formulas (m = Household Members):
//					--> Hawaii: 22680.00 + 5910.00 * (m - 2)
//					--> Alaska: 24640.00 + 6430.00 * (m - 2)
//				    --> Other: 19720.00 + 5140.00 * (m - 2)
//				 - Print data for each household object that is below its poverty level (using a five-column header)
//				    --> ID Number
//					--> Annual Income
//					--> Poverty Level
//					--> Number of Household Members
//					--> State
//				 - Calculate and print the percentage of households that are below poverty level
//				 - Calculate and print the percentage of households that qualify for Medicaid
//
//********************************************************************

// imports
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Program9 {
	
	// create Scanner input instance variable
	private Scanner input;
	
	//***************************************************************
	//
	//  Method:       main
	// 
	//  Description:  The main method of the program
	//
	//  Parameters:   String array
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public static void main(String[] args) {
		
		// display developer info
		developerInfo();
		
		// create Program9 object in order to call non-static methods
		Program9 program9 = new Program9();
		
		// create an array list of Household objects
		ArrayList<HouseHold> households = new ArrayList<HouseHold>();

		// read text file (Program9.txt)
		program9.openFile();
		
		// add household data from text file to the array list
		households = program9.getHouseholds(households);
		
		// print record of each household in four-column format
		program9.printAllRecords(households);
		
		// calculate and print the average household income
		program9.printAverageIncome(households);
		
		// print record of each household that exceeds the average income
		// (also in four-column format)
		program9.printExceedsAverageIncome(households);
		
		// print record of each household that is below its poverty level
		program9.printBelowPovertyLevel(households);
		
		// print the percentage of households that are below poverty level
		program9.printPercentageBelowPovertyLevel(households);
		
		// print the percentage of households that qualify for Medicaid
		program9.printPercentageMedicaid(households);
		
	} // end of main
	
	//***************************************************************
	//
	//  Method:       openFile
	// 
	//  Description:  opens an existing file to scan for input
	//
	//  Parameters:   None
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public void openFile()
	   {
	      try
	      {
	         input = new Scanner(Paths.get("Program9.txt")); 
	      } 
	      catch (IOException ioException)
	      {
	         System.err.println("Error opening file. Terminating.");
	         System.exit(1);
	      }
	   } // end of openFile
	
	//***************************************************************
	//
	//  Method:       getHouseholds
	// 
	//  Description:  Collects household objects into an ArrayList
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      ArrayList<HouseHold>
	//
	//**************************************************************
	public ArrayList<HouseHold> getHouseholds(ArrayList<HouseHold> households) {
		try 
	      {
	    	// while there is more to read
	         while (input.hasNext()) 
	         {   // create an object using the Household constructor
	        	 HouseHold household = new HouseHold(
	        			 input.nextInt(),
	        			 input.nextDouble(),
	        			 input.nextInt(),
	        			 // remove whitespace from state input to improve formatting
	        			 input.nextLine().replaceAll("\\s+", ""));
	        	 
	        	 // add new household object to the array list
	        	 households.add(household);
	         }
	         
	      } 
	      // check for exceptions
	      catch (NoSuchElementException elementException)
	      {
	         System.err.println("File improperly formed. Terminating.");
			 System.exit(1); // terminate the program
	      } 
	      catch (IllegalStateException stateException)
	      {
	         System.err.println("Error reading from file. Terminating.");
			 System.exit(1); // terminate the program
	      }
		
		// return array list of household objects
		return households;
	} // end of getHouseholds
	
	//***************************************************************
	//
	//  Method:       printAllRecords
	// 
	//  Description:  Prints data from each ArrayList object using a four-column header
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      N/A
	//
	//**************************************************************
	public void printAllRecords(ArrayList<HouseHold> households) {
		
		try {
			// add output to existing text file (Program9-output.txt)
			FileWriter fw = new FileWriter("Program9-output.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			// create four-column header
			pw.printf("%-15s%-15s%-15s%-15s%n%n",
					"ID Number",
					"Annual Income",
					"Members",
					"State");
			
			// print data for each household object
			for (HouseHold household : households) {
				pw.printf("%-15d", household.getIDNumber());
				pw.printf("$%,-15.02f", household.getAnnualIncome());
				pw.printf("%-15d", household.getMembers());
				pw.printf("%-15s", household.getState());
				pw.println();
			}
			
			// close file
			pw.close();
			
		}
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
		}
	} // end of printAllRecords
	
	//***************************************************************
	//
	//  Method:       printAverageIncome
	// 
	//  Description:  Prints average income from array list data
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      N/A
	//
	//**************************************************************
	public void printAverageIncome(ArrayList<HouseHold> households) {
		
		try {
			// add output to existing text file (Program9-output.txt)
			FileWriter fw = new FileWriter("Program9-output.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			// calculate average income by calling calculateAverageIncome method
			double averageIncome = calculateAverageIncome(households);
			
			// print average income
			pw.printf("%n%s: $%,.02f %n", "Average Household Income", averageIncome);
			
			// close file
			pw.close();
			
		}
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
		}
	} // end of printAverageIncome
	
	//***************************************************************
	//
	//  Method:       printExceedsAverageIncome
	// 
	//  Description:  Prints data from each ArrayList object that exceeds the
	//				  average income (uses a four-column header)
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      N/A
	//
	//**************************************************************
	public void printExceedsAverageIncome(ArrayList<HouseHold> households) {
		
		try {
			// add output to existing text file (Program9-output.txt)
			FileWriter fw = new FileWriter("Program9-output.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			// create four-column header
			pw.printf("%n%s%n------------------------------------%n", "Households Exceeding Average Income");
			pw.printf("%-15s%-15s%-15s%-15s%n%n",
					"ID Number",
					"Annual Income",
					"Members",
					"State");
			
			// collect average income
			double averageIncome = calculateAverageIncome(households);
			
			// print data for each household that exceeds the average income
			for (HouseHold household : households) {
				if (household.getAnnualIncome() > averageIncome) {
					pw.printf("%-15d", household.getIDNumber());
					pw.printf("$%,-15.02f", household.getAnnualIncome());
					pw.printf("%-15d", household.getMembers());
					pw.printf("%-15s", household.getState());
					pw.println();
				}
			}
			
			// close file
			pw.close();
			
		}
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
		}
	} // end of printExceedsAverageIncome
	
	
	//***************************************************************
	//
	//  Method:       printBelowPovertyLevel
	// 
	//  Description:  Prints data from each ArrayList object that is below poverty level
	//			      (uses a five-column header)
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      N/A
	//
	//**************************************************************
	public void printBelowPovertyLevel(ArrayList<HouseHold> households) {
		
		try {
			// add output to existing text file (Program9-output.txt)
			FileWriter fw = new FileWriter("Program9-output.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			// create five-column header
			pw.printf("%n%s%n-------------------------------%n", "Households Below Poverty Level");
			pw.printf("%-15s%-15s%-15s%-15s%-15s%n%n",
					"ID Number",
					"Annual Income",
					"Poverty Level",
					"Members",
					"State");
			
			// print data for each household that is below its poverty level
			for (HouseHold household : households) {
				// calculate possible poverty levels
				double povertyLevelHawaii = 22680.00 + 5910.00 * (household.getMembers() - 2);
				double povertyLevelAlaska = 24640.00 + 6430.00 * (household.getMembers() - 2);
				double povertyLevelOther = 19720.00 + 5140.00 * (household.getMembers() - 2);
				
				// check if state is Hawaii & below poverty level
				if (household.getState().equals("Hawaii") && household.getAnnualIncome() < povertyLevelHawaii) {
					pw.printf("%-15d", household.getIDNumber());
					pw.printf("$%,-15.02f", household.getAnnualIncome());
					pw.printf("$%,-15.02f", povertyLevelHawaii);
					pw.printf("%-15d", household.getMembers());
					pw.printf("%-15s", household.getState());
					pw.println();
				} // check if state is Alaska & below poverty level
				else if (household.getState().equals("Alaska") && household.getAnnualIncome() < povertyLevelAlaska) {
					pw.printf("%-15d", household.getIDNumber());
					pw.printf("$%,-15.02f", household.getAnnualIncome());
					pw.printf("$%,-15.02f", povertyLevelAlaska);
					pw.printf("%-15d", household.getMembers());
					pw.printf("%-15s", household.getState());
					pw.println();
				} // check is other state is below poverty level
				else if (household.getAnnualIncome() < povertyLevelOther) {
					pw.printf("%-15d", household.getIDNumber());
					pw.printf("$%,-15.02f", household.getAnnualIncome());
					pw.printf("$%,-15.02f", povertyLevelOther);
					pw.printf("%-15d", household.getMembers());
					pw.printf("%-15s", household.getState());
					pw.println();
				}
			}
			
			// close file
			pw.close();
			
		}
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
		}
	} // end of printBelowPovertyLevel
	
	
	//***************************************************************
	//
	//  Method:       printPercentageBelowPovertyLevel
	// 
	//  Description:  Prints the percentage of total household objects that are below poverty level
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      N/A
	//
	//**************************************************************
	public void printPercentageBelowPovertyLevel(ArrayList<HouseHold> households) {
		
		try {
			// add output to existing text file (Program9-output.txt)
			FileWriter fw = new FileWriter("Program9-output.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			// declare count variable
			int countBelowPovertyLevel = 0;
			
			// count the number of households below poverty level
			for (HouseHold household : households) {
				// calculate possible poverty levels
				double povertyLevelHawaii = 22680.00 + 5910.00 * (household.getMembers() - 2);
				double povertyLevelAlaska = 24640.00 + 6430.00 * (household.getMembers() - 2);
				double povertyLevelOther = 19720.00 + 5140.00 * (household.getMembers() - 2);
				
				// check if state is Hawaii & below poverty level
				if (household.getState().equals("Hawaii") && household.getAnnualIncome() < povertyLevelHawaii) {
					++countBelowPovertyLevel;
				} // check if state is Alaska & below poverty level
				else if (household.getState().equals("Alaska") && household.getAnnualIncome() < povertyLevelAlaska) {
					++countBelowPovertyLevel;
				} // check is other state is below poverty level
				else if (household.getAnnualIncome() < povertyLevelOther) {
					++countBelowPovertyLevel;
				}
			}
			
			// calculate percentage below poverty level
			double percentageBelowPovertyLevel = 1.0 * countBelowPovertyLevel / households.size(); 
			
			// print percentage below poverty level
			pw.printf("%n%nPercentage of Households Below Poverty Level: %.01f%% %n", percentageBelowPovertyLevel * 100);
			
			// close file
			pw.close();
			
		}
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
		}
	} // end of printPercentageBelowPovertyLevel
	
	//***************************************************************
	//
	//  Method:       printPercentageMedicaid
	// 
	//  Description:  Prints the percentage of total household objects that qualify for Medicaid
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      N/A
	//
	//**************************************************************	
	public void printPercentageMedicaid(ArrayList<HouseHold> households) {
		
		try {
			// add output to existing text file (Program9-output.txt)
			FileWriter fw = new FileWriter("Program9-output.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			// declare count variable
			int countMedicaid = 0;
			
			// count the number of households that qualify for Medicaid
			for (HouseHold household : households) {
				// calculate possible poverty levels
				double povertyLevelHawaii = 22680.00 + 5910.00 * (household.getMembers() - 2);
				double povertyLevelAlaska = 24640.00 + 6430.00 * (household.getMembers() - 2);
				double povertyLevelOther = 19720.00 + 5140.00 * (household.getMembers() - 2);
				
				// check if state is Hawaii & qualifies for medicaid
				if (household.getState().equals("Hawaii") && household.getAnnualIncome() <= (povertyLevelHawaii * 1.38)) {
					++countMedicaid;
				} // check if state is Alaska & qualifies for medicaid
				else if (household.getState().equals("Alaska") && household.getAnnualIncome() <= (povertyLevelAlaska * 1.38)) {
					++countMedicaid;
				} // check is other state qualifies for medicaid
				else if (household.getAnnualIncome() <= (povertyLevelOther * 1.38)) {
					++countMedicaid;
				}
			} // end printPercentageMedicaid
			
			// calculate percentage below poverty level
			double percentageMedicaid = 1.0 * countMedicaid / households.size(); 
			
			// print percentage below poverty level
			pw.printf("%n%nPercentage of Households that Qualify for Medicaid: %.01f%% %n", percentageMedicaid * 100);
			
			// close file
			pw.close();
			
		}
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
		}
	} // end of printPercentageMedicaid
	
	
	//***************************************************************
	//
	//  Method:       calculateAverageIncome
	// 
	//  Description:  Calculates and returns the average income of total household objects
	//
	//  Parameters:   ArrayList<HouseHold>
	//
	//  Returns:      double
	//
	//**************************************************************
	public double calculateAverageIncome(ArrayList<HouseHold> households) {
		
		// declare total income
		double totalIncome = 0;
					
					
		// calculate total income
		for (HouseHold household : households) {
			totalIncome += household.getAnnualIncome();
		}
					
		// calculate average income
		double averageIncome = totalIncome / households.size();
		
		return averageIncome;
	} // end of calculateAverageIncome
	
	
	
	//***************************************************************
	//
	//  Method:       developerInfo
	// 
	//  Description:  The developer information method of the program
	//
	//  Parameters:   None
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public static void developerInfo()
	   {
		try {
			// Generate and print to new text file (Program9-output.txt)
			File file = new File("Program9-output.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
						
		    pw.println("Name:    Summer Davis");
		    pw.println("Course:  ITSE 2321 Object-Oriented Programming");
		    pw.println("Program: Nine \n");
			
		    pw.close();
		      
		}
		
		catch(IOException ioException) {
			System.err.println("Error generating output. Terminating.");
	        System.exit(1);
	   }
	   } // End of developerInfo

}
