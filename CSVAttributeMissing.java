//-------------------------------------------------------
//Assignment 3 
//(CSVAttributeMissing Class)
//Written by Ann-Marie Czuboka (40209452) and Isabelle Czuboka (40209525)
//For COMP 249 Section (PP) – Winter 2022
//Due date: March 25th by 11:55pm
//--------------------------------------------------------

/**
* This CSVAttribute class contains its constructor and the getMessage 
* Method for the log when class is called.
* @author ann-marieczuboka(40209452)
* @author isabelleczuboka(40209525)
*/
public class CSVAttributeMissing extends Exception
{
	/**
	Default Constructor
	*/
	public CSVAttributeMissing() {
		
	}
	/**
	Method to print to the log file when attribute is missing
	@param path to know which file there's an error in
	@return string that tells us in which file the attribute is missing
	*/
	public String getMessage(String path) {
		return("ERROR: in file " +path+ ". Missing attribute. File not converted to HTML \n");
		
	}
}
