//-------------------------------------------------------
//Assignment 3 
//(CSVDataMissing Class)
//Written by Ann-Marie Czuboka (40209452) and Isabelle Czuboka (40209525)
//For COMP 249 Section (PP) – Winter 2022
//Due date: March 25th by 11:55pm
//--------------------------------------------------------

/**
* This CSVDataMissing class contains its constructor and the getMessage 
* Method for the log when class is called.
* @author ann-marieczuboka(40209452)
* @author isabelleczuboka(40209525)
*/
public class CSVDataMissing extends Exception
{
	/**
	 * Default constructor
	 */
	public CSVDataMissing() {
		
	}
	/**
	Method to print to the log file when data is missing
	@param path to know which CSV file 
	@param line to know which line attribute is missing in
	@param attribute to know which attribute is missing
	@return String that lets us know which file has a missing data at which line
	*/
	public String getMessage(String path, int line, String attribute) {
		return("In file " +path+ ". Line "+line+ " is not converted into HTML : Missing data : "+attribute+". \n");
		//have to add the line it messed up at
	}

}
