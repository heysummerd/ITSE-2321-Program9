//********************************************************************
//
//Author:        Summer Davis
//
//Program #:     Nine
//
//File Name:     HouseHold.java
//
//Course:        ITSE 2321 Object-Oriented Programming
//
//Description:  HouseHold class will manage four instance variables
//				for each object created:
//				- ID Number (4 digits)
//				- Annual Income (cannot be negative)
//				- Members (must be greater than 0)
//				- State
//
//********************************************************************

public class HouseHold {

	// declare instance variables
	private int idNumber;
	private double annualIncome;
	private int members;
	private String state;
	
	// declare constructor
	
	//***************************************************************
	//
	//  Method:       HouseHold
	// 
	//  Description:  The constructor of the class
	//				  - checks that annual income is not negative
	//				  - checks that members is greater than 0
	//
	//  Parameters:   int, double, int, String
	//
	//  Returns:      N/A
	//
	//**************************************************************
	public HouseHold(int idNumber, double annualIncome, int members, String state) {
		this.idNumber = idNumber;
		// validate that annual income is not negative
		if (annualIncome >= 0) {
			this.annualIncome = annualIncome;
		}
		// validate that household members is greater than 0
		if (members > 0) {
			this.members = members;
		}
		this.state = state;
	} // end of constructor 
	
	// Setters and Getters
	
	//***************************************************************
	//
	//  Method:       setIDNumber
	// 
	//  Description:  The setter for idNumber instance variable
	//
	//  Parameters:   int
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public void setIDNumber(int idNumber) {
		this.idNumber = idNumber;
	} // end of setIDNumber
	
	//***************************************************************
	//
	//  Method:       getIDNumber
	// 
	//  Description:  The getter for getIDNumber instance variable
	//
	//  Parameters:   N/A
	//
	//  Returns:      int 
	//
	//**************************************************************
	public int getIDNumber() {
		return idNumber;
	} // end of getIDNumber
	
	//***************************************************************
	//
	//  Method:       setAnnualIncome
	// 
	//  Description:  The setter for annualIncome instance variable
	//				  - checks that value is not negative
	//
	//  Parameters:   double
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public void setAnnualIncome(double annualIncome) {
		if (annualIncome >= 0) {
			this.annualIncome = annualIncome;
		}
	} // end of setAnnualIncome
	
	//***************************************************************
	//
	//  Method:       getAnnualIncome
	// 
	//  Description:  The getter for annualIncome instance variable
	//
	//  Parameters:   N/A
	//
	//  Returns:      double 
	//
	//**************************************************************
	public double getAnnualIncome() {
		return annualIncome;
	} // end of getAnnualIncome
	
	//***************************************************************
	//
	//  Method:       setMembers
	// 
	//  Description:  The setter for members instance variable
	//				  - checks that value is greater than 0
	//
	//  Parameters:   int
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public void setMembers(int members) {
		if (members > 0) {
			this.members = members;
		}
	} // end of setMembers
	
	//***************************************************************
	//
	//  Method:       getMembers
	// 
	//  Description:  The getter for members instance variable
	//
	//  Parameters:   N/A
	//
	//  Returns:      int 
	//
	//**************************************************************
	public int getMembers() {
		return members;
	} // end of getMembers
	
	//***************************************************************
	//
	//  Method:       setState
	// 
	//  Description:  The setter for state instance variable
	//
	//  Parameters:   String
	//
	//  Returns:      N/A 
	//
	//**************************************************************
	public void setState(String state) {
		this.state = state;
	} // end of setState
	
	//***************************************************************
	//
	//  Method:       getState
	// 
	//  Description:  The getter for state instance variable
	//
	//  Parameters:   N/A
	//
	//  Returns:      String 
	//
	//**************************************************************
	public String getState() {
		return state;
	} // end of getState
}
