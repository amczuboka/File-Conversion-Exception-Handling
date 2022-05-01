import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//-------------------------------------------------------
//Assignment 3 
//(Main Class)
//Written by Ann-Marie Czuboka (40209452) and Isabelle Czuboka (40209525)
//For COMP 249 Section (PP) – Winter 2022
//Due date: March 25th by 11:55pm
//--------------------------------------------------------

/**
* This Main class contains the main method to scan and convert a CSV file to an HTML file as well as 
* another method to scan the rest of the file when it hits a missing data.
* @author ann-marieczuboka(40209452)
* @author isabelleczuboka(40209525)
*/
public class Main
{
	/**
	 * Method to scan and convert the rest of the CSV when a data is missing
	 * @param path which directs you to the file to scan and convert
	 * @param hTMLfiles directs you to which file to enter the HTML info
	 * @param start describes when to start printing to the HTML file when scanning
	 */
	public static void ScanTheRest(String path, File hTMLfiles, int start){
		/**
		 * BufferedReader to read the amount of lines in the CSV file
		 */
		BufferedReader newbrline=null;
		/**
		 * BufferedReader to read the CSV file
		 */
		BufferedReader newbr=null;
		/**
		 * String that describes the delimiter for the split method
		 */
		String split = ","; 
		
		/**
		 * Try block
		 */
		try {
		/**
		 * Opening up the CSV file to scan inside for amount of lines
		 */
		 newbrline = new BufferedReader(new FileReader(path));
		 /**
		  * Opening up the CSV file to scan inside 
		 */
		 newbr= new BufferedReader(new FileReader(path)); 
		
		 /**
		  * String Line that holds entire line of CSV file to be split up
		  */
		 String line = "";  
		 
		 /**
		  * String Line that holds entire line of CSV file for our Data part
		  */
		 String fullLine="";
		 
		 
	
		 /**
		  * int linenumber to know the line at beginning of file
		  */
			int linenumber = 0;
			/**
			 * Checking to see if file has ended or not (amount of lines)
			 */
			while(newbrline.readLine() !=null)//checking if it has next line
			{ 	
				linenumber++;
			}
			/**
			 * int knowline useful for Data part
			 */
			int knowline=2;
			/**
			 * int rounds to increment during the reading
			 */
			int rounds=0;
			/**
			 * Checking to see if file has ended or not (conversion)
			 */
			while ((line = newbr.readLine()) != null)
			{	
				/**
				 * array for title
				 */
				String[] caption;
				/**
				 * array for attributes
				 */
				String[] tr;
				/**
				 * array for data
				 */
				String[] info;
				/**
				 * array for end note
				 */
				String[] span;
				/**
				 * printer to write to HTML file
				 */
				PrintWriter newHTML = null;
				/**
				 * printer to write to HTML file opening file 
				 */
				newHTML = new PrintWriter(new FileOutputStream(hTMLfiles,true));
				/**
				 * incrementing rounds during scanning
				 */
				rounds++;
				
				/**
				 * for first line up to line after missing data
				 */
				if(rounds<start) {
					continue;
					}
				/**
				 * For line after missing data	
				 */
				if(rounds>=start && rounds<linenumber) {
					newHTML.append("<tr>\n");
					newHTML.flush();
					info = line.split(split); 
					for(int x=0; x<4 ; x++) {
						newHTML.append("<td>"+info[x]+"</td>\n");
						newHTML.flush();
							}
					newHTML.append("</tr>\n");
					newHTML.flush();
						}
					
				/**
				 * for the last line (end note)	
				 */
				if(rounds==linenumber) {
					newHTML.append("</table>\n");
					newHTML.flush();
					span = line.split(split); 
					for(int x=0; x<span.length ; x++) {
						newHTML.append("<span>"+span[x]+"</span>\n");
						 newHTML.flush();
					}
					newHTML.append("</body>\n");
					newHTML.flush();
					newHTML.append("</html>\n");
					newHTML.flush();
				}
			}
			
		}
		/**
		 * catching FileNotFoundException
		 */
		catch(FileNotFoundException e1) {
			System.out.println("Could not open input file " +path+  " for reading \nPlease check if file exists and is readable."
					+ " This program will terminate after closing any opened files");
		}
		/**
		 * catching IOException
		 */
		catch(IOException e2) {
			System.out.println("Something went wrong");
		}
	}
	/**
	 * Method to scan and convert the rest of the CSV when a data is missing
	 * @param path which directs you to the file to scan and convert
	 * @param HTMLfiles directs you to which file to enter the HTML info
	 * @param in Scanner to know the input of the scanner (which file user wants to convert)
	 * @throws IOException thrown here
	 */
	public static void ConvertCSVtoHTML(String path, File HTMLfiles, Scanner in) throws IOException {
		 /**
		  * String Line that holds entire line of CSV file to be split up
		  */
		String line = "";  
		/**
		 * String that describes the delimiter for the split method
		 */
		String split = ","; 
		/**
		 * Index at which to stop at the end of file name
		 */
		int pathIndex= path.indexOf(".");
		/**
		 * String to hold just the file name
		 */
		String subPath = null;
		/**
		 * Create the name of file when valid 
		 */
		if(pathIndex!=-1)
			subPath = path.substring(0,pathIndex);
		
		//Creating Printer
		/**
		 * PrintWriter to write in log file
		 */
		PrintWriter out = null;
		//Scanner in = null;
		/**
		 * BufferedReader to read the amount of lines in the CSV file
		 */
		BufferedReader brline= null;
		/**
		 * BufferedReader to read the CSV file
		 */
		BufferedReader br=null;
		
		
		//File HTMLfiles = new File(subPath+".html");
		/**
		 * PrintWriter to write to HTML file when converted
		 */
		PrintWriter HTML = null;
		/**
		 * try block
		 */
		try   
		{  
		//parsing a CSV file into BufferedReader class constructor  
		/**
		 * Opening up the CSV file to scan inside for amount of lines
		 */
		brline = new BufferedReader(new FileReader(path));  
		/**
		  * Opening up the CSV file to scan inside 
		 */
		br = new BufferedReader(new FileReader(path)); 
		//Creating Exception log file
		/**
		 * Created new file to log the exceptions
		 */
		File log = new File("Exceptions.log");
		/**
		 * opening up the log file to be writen into
		 */
		out = new PrintWriter(new FileOutputStream(log,true)); 
		//in = new Scanner(new FileReader(log));
		/**
		 * boolean useful when attribute is missing
		 */
		boolean isThrows = true;
		 /**
		  * String Line that holds entire line of CSV file for our Data part
		  */
		String fullLine="";
		/**
		 * array which will hold all attribute names
		 */
		String[] attributeName = new String[4];
		
		//Checking how many lines file has
		/**
		  * int linenumber to know the line at beginning of file
		  */
		int linenumber = 0;
		/**
		 * Checking to see if file has ended or not and if attribute is missing (conversion)
		 */
		while(brline.readLine() !=null && isThrows==true)//checking if it has next line
		{ 	
			linenumber++;
		}
		/**
		 * setting knowline at 2 because of first and second line
		 */
		int knowline=2;
		/**
		 * setting rounds to 0
		 */
		int rounds=0;
		/**
		 * checking to see if there is a next line 
		 */
		while ((line = br.readLine()) != null)
		{	
			/**
			 * array for title
			 */
			String[] caption;
			/**
			 * array for attributes
			 */
			String[] tr;
			/**
			 * array for data
			 */
			String[] info;
			/**
			 * array for end note
			 */
			String[] span;
			/**
			 * printer to write to HTML file
			 */
			HTML = new PrintWriter(new FileOutputStream(HTMLfiles,true));
			/**
			 * incrementing rounds
			 */
			rounds++;
			
		{	
			/**
			 * for line 1
			 */
			if(rounds==1) {
				HTML.append("<!DOCTYPE html>\n<html>\n<style>\ntable {font-family: arial, sans-serif;border-collapse: collapse;}\n"
				+"td, th {border: 1px solid #000000;text-align: left;padding: 8px} \ntr:nth-child(even) {background-color: #dddddd;}\n"
				+"span{font-size: small}\n</style>\n<body>\n");
				HTML.flush();
				HTML.append("<table>\n");
				HTML.flush();
				caption = line.split(split); 
				for(int x=0; x<caption.length ; x++) {
					HTML.append("<caption>"+caption[x]+"</caption>\n");
					HTML.flush();
				}
			}
			/**
			 * for line 2
			 */
			if(rounds==2) {
				tr = line.split(split); 
				/**
				 * going through the second line (attributes)
				 */
				for(int x=0; x<tr.length ; x++) {
					/**
					 * if attribute is missing
					 */
					if(tr[x]=="") 
					{	
						isThrows=false;
						out.write(new CSVAttributeMissing().getMessage(path));
						throw new CSVAttributeMissing();
					}
				}
				/**
				 * if data attribute isnt missing
				 */
				if(isThrows)
				{
					HTML.append("<tr>\n");
					HTML.flush();
					for(int x=0; x<tr.length ; x++) {
						attributeName[x]= tr[x];
						HTML.append("<th>"+tr[x]+"</th>\n");
						HTML.flush();
						
					}
					HTML.append("</tr>\n");
					HTML.flush();
				}
				
			}
			/**
			 * for line after second line and before end note
			 */
			if(rounds>2 && rounds<linenumber) {
				knowline++;
				HTML.append("<tr>\n");
				HTML.flush();
				info = line.split(split); 
				/**
				 * going through the data
				 */
				for(int x=0; x<info.length ; x++) {
					/**
					 * checking to see if data is missing
					 */
					if(info[x]=="")
					{  
						String attribute=attributeName[x];
						out.write(new CSVDataMissing().getMessage(path, knowline, attribute));
						ScanTheRest(path, HTMLfiles, knowline+1);
						throw new CSVDataMissing();
					}
					else
					{
						if(x!=(info.length-1))
							fullLine+= "<td>"+info[x]+"</td>\n";
						else 
						{
					fullLine+= "<td>"+info[x]+"</td>\n";
					HTML.append(fullLine);
					HTML.flush();
					fullLine="";
						}
					
					}
				}
				HTML.append("</tr>\n");
				HTML.flush();
			}
			/**
			 * for the last line (end note)
			 */
			if(rounds==linenumber) {
				HTML.append("</table>\n");
				HTML.flush();
				span = line.split(split); 
				/*
				 * Printing out the one line
				 */
				for(int x=0; x<span.length ; x++) {
					HTML.append("<span>"+span[x]+"</span>\n");
					HTML.flush();
				}
				HTML.append("</body>\n");
				HTML.flush();
				HTML.append("</html>\n");
				HTML.flush();
			}
		}
		}
		
		}
		/**
		 * catching the FileNotFoundException and printing out message
		 */
		catch (FileNotFoundException e1)   
		{  
		System.out.println("Could not open input file " +path+  " for reading \nPlease check if file exists and is readable."
				+ " This program will terminate after closing any opened files");
		 if (HTMLfiles!=null)HTMLfiles.delete();//deleting HTMLFile
		 if (brline!=null)brline.close();//closing brline
		 if (br!=null)br.close();//closing br 
		 if (out!=null)out.close();//closing out
		 if (in!=null)in.close();//closing in
		 System.out.println();
		 System.out.println("Thank you for using this program :)");
		 System.exit(0);
		} 
		/**
		 * catching IOExcpetion and printing message + exiting system
		 */
		catch (IOException e2)   
		{  
		System.out.println("Error was found");
		 if (brline!=null)brline.close();//closing brline
		 if (br!=null)br.close();//closing br 
		 if (out!=null)out.close();//closing out
		 if (in!=null)in.close();//closing in
		System.exit(0);
		}
		/**
		 * catching if an attribute is missing and printing message
		 */
		catch (CSVAttributeMissing e3)   
		{  
		 System.out.println("Error: Input row cannot be parsed due to missing information");
		 if (HTMLfiles!=null)HTMLfiles.delete();//deleting HTML files
		 if (brline!=null)brline.close();//closing brline
		 if (br!=null)br.close();//closing br
		 if (out!=null)out.close();//closing out
		 //if (in!=null)in.close();
		} 
		/**
		 * catching if a data was missing and printing message
		 */
		catch (CSVDataMissing e4)   
		{  
		System.out.println("Missing data in the file! Specific row will be deleted ");
		
		//System.exit(0);
		} 
		/**
		 * executes after all catches (asking user if they want to see content of HTML file)
		 */
		finally 
		{
			/**
			 * try block
			 */
			try {
			if(brline !=null)brline.close();
			if(br !=null)br.close();
			if(out !=null)out.close();
			//if(in !=null)in.close();	
			System.out.print("Please enter the File Name of the HTML file you would like to see the contents of: ");
			Scanner inHTML= new Scanner(System.in);
			String HTMLpath = in.nextLine();
			String HTMLext = HTMLpath+".html";
			System.out.println();
			
			String htmlLine= "";
			BufferedReader HTMLreader= new BufferedReader(new FileReader(HTMLext));
			while ((htmlLine = HTMLreader.readLine()) != null){
				System.out.println(htmlLine);
			}
			System.out.println();
			System.out.println("Thank you for using this program :)");
			}
			/**
			 * catching FileNotFoundException and printing message
			 */
			catch(FileNotFoundException e5) {
				System.out.println("Wrong input try again!");
				/*
				 * try block for after filenotfound and asking user if they want to see content of HTML file
				 */
				try {
					if(brline !=null)brline.close();
					if(br !=null)br.close();
					if(out !=null)out.close();
					//if(in !=null)in.close();	
					System.out.print("Please enter the File Name of the file you would like to see the contents of: ");
					Scanner inHTML= new Scanner(System.in);
					String HTMLpath = in.nextLine();
					String HTMLext = HTMLpath+".html";
					System.out.println();
					
					String htmlLine= "";
					BufferedReader HTMLreader= new BufferedReader(new FileReader(HTMLext));
					while ((htmlLine = HTMLreader.readLine()) != null){
						System.out.println(htmlLine);
					}
					}
				/**
				 * catching FileNotFoundException
				 */
					catch(FileNotFoundException e6) {
						System.out.println("Wrong input! Goodbye");
						System.exit(0);
					}
			}
		}
		
		
	}
	/**
	 * Running the main program
	 * @param args run
	 * @throws Exception to be thrown
	 */
	public static void main (String args[])throws Exception{
		//Displaying welcome message
		System.out.print("Welcome to the Ann-Marie & Isabelle Czuboka CSV to HTML Converter Program!\n-----"
				+ "-------------------------------------------------------------------------"
				+ "\nPlease enter a CSV file you wish to convert to HTML format: ");
		/**
		 * Creating scanner for user input
		 */
		Scanner in = new Scanner(System.in);
		/**
		 * reading the input to know the CSV file to scan
		 */
		String path = in.nextLine();
		System.out.println();
		
		/**
		 * int pathIndex to know where to split
		 */
		int pathIndex= path.indexOf(".");
		/**
		 * String subPath for file name -ext
		 */
		String subPath = null;
		if(pathIndex!=-1)
			subPath = path.substring(0,pathIndex);
		
		/**
		 * Creating Printer
		 */
		PrintWriter out = null;
		/**
		 * Creating BufferedReader to know amount of lines
		 */
		BufferedReader brline= null;
		/**
		 * Creating BufferedReader scan CSV file
		 */
		BufferedReader br=null;
		
		/**
		 * Creating HTML files with same name as CSV file
		 */
		File HTMLfiles = new File(subPath+".html");
		/**
		 * Now we running the program with the main method
		 */
		ConvertCSVtoHTML(path, HTMLfiles, in);
		
	}
	}

