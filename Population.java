import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *	Population - It reads a textfile and stores the information in the
 * 				 textfile into an array list of City type. It then sorts
 * 				 the cities according to user input and prints the 
 *               result to the screen.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Ryan Hau
 *	@since	January 10th, 2022
 */
public class Population
{
	private List<City> cities; // List of cities
	private List<City> temp; // Temporary list
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	long startMillisec; // Start time for sorting
	long endMillisec; // End time for sorting
	
	/*Constructor() initializes the above field varables*/
	public Population()
	{
		cities = new ArrayList<City>();
		temp = new ArrayList<City>();
		startMillisec = 0;
		endMillisec = 0;
	}
	
	/**
	 * Main method makes an instance of Population class,
	 * and uses that instance to call start().
	 * 
	 * @return void
	 */
	public static void main(String []args)
	{
		Population pop = new Population();
		pop.start();
	}

	/**
	 * start() calls printIntroduction(), 
	 * printMenu(), initCity(), promptUser(),
	 * printMessage(), and printTime(). It uses
	 * a while loop to determine when to terminate 
	 * itself and if statements to determine when to
	 * print certain messages.
	 * 
	 * @return void
	 */
	public void start()
	{
		String userInput = "1";

		printIntroduction();

		while (!userInput.equals("9"))
		{
			cities = new ArrayList<City>();

			startMillisec = 0;
			endMillisec = 0;

			printMenu();
			initCity();
			userInput = promptUser();

			if (!userInput.equals("5")
					&& !userInput.equals("6"))
				printMessage(userInput);

			if (!userInput.equals("9") && !userInput.equals("5")
					&& !userInput.equals("6"))
				printTime();
		}
	} 

	/**
	 * Prints the introduction to Population 
	 * 
	 * @return void
	 */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();

		System.out.println("31765 cities in database\n");
	}
	
	/**
	 * Print out the choices for population sorting 
	 * 
	 * @return void
	 */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**
	 * initCity() initializes the cities Arraylist by
	 * using FileUtils class to read the text file. A
	 * while loop is used to read through the file and 
	 * fill the cities Arraylist accordingly. A 
	 * new City object is created for each spot in the 
	 * Arraylist and the City class' constructor is called.  
	 * 
	 * @return void
	 */
	public void initCity()
	{
		int index = 0;
		
		Scanner inFile = FileUtils.openToRead(DATA_FILE);
		inFile.useDelimiter("[\t\n]");
		
		String cityName = new String("");
		String stateName = new String("");
		String cityType = new String("");
		int population = 0;
		
		while (inFile.hasNext())
		{
			stateName = inFile.next();
			cityName = inFile.next();
			cityType = inFile.next();
			population = Integer.parseInt(inFile.next());
			
			cities.add(new City(stateName, cityName, cityType, population));
		}
	}
	
	/**
	 * promptUser() prompts the user, and using a 
	 * switch statements, determines which method to call 
	 * or whether to print an error statement.
	 * 
	 * @return The user input as a String
	 */
	public String promptUser()
	{
		boolean isValid = false;
		String num = new String("");

		while (!isValid)
		{
			num = Prompt.getString("\nEnter Selection");
			System.out.println();
			
			switch(num)
			{
				case("1"):
					sortAscendPop();
					isValid = true;
					break;
				case("2"):
					sortDescendPop();
					isValid = true;
					break;
				case("3"):
					sortAscendName();
					isValid = true;
					break;	
				case("4"):
					sortDescendName();
					isValid = true;
					break;
				case("5"):
					sortPrintStateAscendPop();
					isValid = true;
					break;
				case("6"):
					sortPrintAllMatch();
					isValid = true;
					break;
				case("9"):
					isValid = true;
					break;
				default:
					isValid = false;
					System.out.println("ERROR: Invalid Input.");
			}
		}

		return num;
	}

	/**
	 * sortAscendPop() calls SSort to begin
	 * the sorting process using Selection Sort.
	 * It records the start and end times for the
	 * sort, and then prints out what will be displayed.
	 * This method is in charge the 1st option.
	 * 
	 * @return void
	 */
	public void sortAscendPop()
	{
		startMillisec = System.currentTimeMillis();
		SSort();
		endMillisec = System.currentTimeMillis();

		System.out.println("Fifty least populous cities");
	}

	/**
	 * sortDescendPop() calls RSort to begin
	 * the sorting process using Merge Sort.
	 * It records the start and end times for the
	 * sort, and then prints out what will be displayed
	 * This method is in charge of the 2nd option.
	 * It passes in the type into RSort so that
	 * it will know what to do later on.
	 * 
	 * @return void
	 */
	public void sortDescendPop()
	{
		int type = 0;

		startMillisec = System.currentTimeMillis();
		RSort(type);
		endMillisec = System.currentTimeMillis();

		System.out.println("Fifty most populous cities");
	}
	
	/**
	 * sortAscendName() calls ISort to begin
	 * the sorting process using Insertion Sort.
	 * It records the start and end times for the
	 * sort, and then prints out what will be displayed.
	 * This method is in charge the 3rd option.
	 * 
	 * @return void
	 */
	public void sortAscendName()
	{
		startMillisec = System.currentTimeMillis();
		ISort();
		endMillisec = System.currentTimeMillis();

		System.out.println("Fifty cities sorted by name");
	}

	/**
	 * sortDescendName() calls RSort to begin
	 * the sorting process using Merge Sort.
	 * It records the start and end times for the
	 * sort, and then prints out what will be displayed
	 * This method is in charge of the 4th option.
	 * It passes in the type into RSort so that
	 * it will know what to do later on.
	 * 
	 * @return void
	 */
	public void sortDescendName()
	{
		int type = 1;

		startMillisec = System.currentTimeMillis();
		RSort(type);
		endMillisec = System.currentTimeMillis();

		System.out.println("Fifty cities sorted by name descending");
	}

	/**
	 * sortPrintStateAscendPop() first prompts the user
	 * to enter a specific state name using a while loop
	 * to determine whether to keep asking (if invalid input)
	 * or to continue. It then calls RSort to sort the 
	 * citys' population in ascending order, and records
	 * the start and end time of the sort. It then only prints
	 * the most populous cities in the given state using printf.
	 * 
	 * @return void
	 */
	public void sortPrintStateAscendPop()
	{
		String stateInput = new String("");
		boolean isValid = false;

		while (!isValid)
		{
			stateInput = Prompt.getString("Enter state name (ie. Alabama)");

			int count = 0;
			while(count < cities.size() && 
					!cities.get(count).getState().equals(stateInput))
			{
				count++;
			}

			if (count < cities.size())
				isValid = true;
			else 
				isValid = false;

			if (!isValid)
				System.out.println("ERROR: " + stateInput + " is not valid.");
		}

		int type = 0;

		startMillisec = System.currentTimeMillis();
		RSort(type);
		endMillisec = System.currentTimeMillis();

		System.out.println("\nFifty most populous cities in " + stateInput);
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", "Type",
						 "Population");

		int index = cities.size()-1;
		int counter = 1;

		while (counter <= 50 && index >= 0)
		{
			if (cities.get(index).getState().equals(stateInput))
			{
				System.out.printf("%3d: %s\n", counter, cities.get(index).toString());
				counter++;
			}
			index--;
		}

		System.out.println();
	}

	/**
	 * sortPrintAllMatch() first prompts the user
	 * to enter a specific city name using a while loop
	 * to determine whether to keep asking (if invalid input)
	 * or to continue. It then calls RSort to sort the 
	 * citys' population in ascending order, and records
	 * the start and end time of the sort. It then only prints
	 * the most populous cities that match the city name
	 * provided by the user. 
	 * 
	 * @return void
	 */
	public void sortPrintAllMatch()
	{
		String cityName = new String("");
		boolean isValid = false;

		while (!isValid)
		{
			cityName = Prompt.getString("Enter city name");

			int count = 0;
			while(count < cities.size() && !cities.get(count).getName().equals(cityName))
			{
				count++;
			}

			if (count < cities.size())
				isValid = true;
			else 
				isValid = false;

			if (!isValid)
				System.out.println("ERROR: " + cityName + " is not valid.");
		}

		int counter = 1;
		int type = 0;

		startMillisec = System.currentTimeMillis();
		RSort(type);
		endMillisec = System.currentTimeMillis();

		int index = cities.size()-1;

		System.out.println("\nCity " + cityName + " by population");
		System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", "Type",
						 "Population");

		while (index >= 0)
		{
			if (cities.get(index).getName().equals(cityName))
			{
				System.out.printf("%3d: %s\n", counter, cities.get(index).toString());
				counter++;
			}
			index--;
		}

		System.out.println();
	}

	/**
	 * printMessage() prints the results for options 1, 2, 3,
	 * and 4. It uses if else statements to determine what to print,
	 * and whether to print the farewell message or not. 
	 * 
	 * @param  userInput	The option number that the user selects.
	 * @return void
	 */
	public void printMessage(String userInput)
	{
		if (!userInput.equals("9"))
		{
			System.out.printf("     %-22s %-22s %-12s %12s\n", "State", 
							 "City", "Type", "Population");

			if (userInput.equals("1") || userInput.equals("3"))
			{
				for (int a = 0; a < 50; a++)
				{
					System.out.printf("%3d: %s\n", a+1, cities.get(a).toString());
				}
			}
			else if (userInput.equals("2") || userInput.equals("4"))
			{
				int counter = 1;
				for (int a = cities.size()-1; a > cities.size()-51; a--)
				{
					System.out.printf("%3d: %s\n", counter, cities.get(a).toString());
					counter++;
				}
			}
		}
		else
		{
			System.out.println("Thanks for using Population! Hope you " +
									"learned something new!");
		}

		System.out.println();
	}

	/**
	 * printTime() prints the time it takes, in millseconds, to
	 * sort all cities accordingly.
	 *
	 * @return void
	 */
	public void printTime()
	{
		System.out.println("Elapsed Time: " + (endMillisec - startMillisec) 
								+ " milliseconds.\n");
	}

	/**
	 * SSort() sorts all the cities in ascending population order
	 * using Selection Sort
	 * 
	 * @return void
	 */
	public void SSort()
	{
		for (int n = cities.size(); n > 1; n--)
		{
			// Find the index iMax of the largest element
			// among a[0], ..., a[n-1]:
			int iMax = 0;
			for (int i = 1; i < n; i++)
			{
				if (cities.get(i).compareTo(cities.get(iMax)) > 0)
					iMax = i;
			}
			
			swap(iMax, n);
		}
	}
	
	/**
	 * ISort() sorts all the cities in alphabetical order
	 * using Insertion Sort
	 * 
	 * @return void
	 */
	public void ISort()
	{
		for (int n = 1; n < cities.size(); n++)
		{
			City aTemp = cities.get(n);
			int i = n;
			
			while (i > 0 && (checkName(aTemp, i-1)))
			{
				cities.set(i, cities.get(i-1));
				i--;
			}

			cities.set(i, aTemp);
		}
	}
	
	/**
	 * RSort() sorts all the cities alphabetically and in 
	 * ascending population order using Merge Sort. It first
	 * makes a new temporary arraylist of capacity 'n' before initializing
	 * it and calling recursiveSort().
	 * 
	 * @return void
	 */
	public void RSort(int type)
	{
		int n = cities.size();
		temp = new ArrayList<City>(n);
		
		for (int a = 0; a < n; a++)
		{
			temp.add(new City("", "", "", 0));
		}
		
		recursiveSort(0, n-1, type);
	}
	
	/**
	 * recursiveSort() calls itself until it reaches its base case
	 * (1 or 2 elements). At that point, it uses an if statement
	 * to determine whether to swap the two elements. Before this, 
	 * it also calls merge() to merge the two sections together.
	 * 
	 * @param	from  The starting point from which to sort
	 * @param	to    The point at which to end the sort.
	 * @param 	type  The indicated type of sort (alphabetically or
	 * 				  by population)
	 * @return  	  void
	 */
	public void recursiveSort(int from, int to, int type)
	{
		if (to - from < 2) 
		{
			if (to > from && (type == 0 && 
				cities.get(to).compareTo(cities.get(from)) < 0 || 
					type == 1 && checkName(cities.get(to), from)))
			{
				City aTemp = cities.get(to); 
				cities.set(to, cities.get(from));
				cities.set(from, aTemp);
			}
		}
		else 
		{
			int middle = (from + to) / 2;
			recursiveSort(from, middle, type);
			recursiveSort(middle + 1, to, type);
			merge(from, middle, to, type);
		}
	}
	
	/**
	 * merge() merges elements in the arraylist from 'from' 
	 * to 'middle'and from 'middle + 1' to 'to' into on 
	 * sorted arraylist from 'from' to 'to'. This essentially
	 * combines the different sections of the arraylist. 
	 * 
	 * @param	from   The starting point from which to sort
	 * @param 	middle The middle point of the arraylist (where 
	 * 				   the arraylist was split in the recursive 
	 * 				   method).
	 * @param	to     The point at which to end the sort.
	 * @param 	type   The indicated type of sort (alphabetically or
	 * 				   by population)
	 * @return 		   void
	 */
	public void merge(int from, int middle, int to, int type)
	{
		int i = from, j = middle + 1, k = from;
		
		// While both arrays have elements left unprocessed:
		while (i <= middle && j <= to)
		{
			if (type == 0 && cities.get(i).compareTo(cities.get(j)) < 0 || 
					type == 1 && checkName(cities.get(i), j))
			{
				temp.set(k, cities.get(i)); 
				i++;
			}
			else
			{	
				temp.set(k, cities.get(j));
				j++;
			}

			k++;
		}
		
		while (i <= middle)
		{
			temp.set(k, cities.get(i));
			i++;
			k++;
		}

		while (j <= to)
		{
			temp.set(k, cities.get(j));
			j++;
			k++;
		}

		for (k = from; k <= to; k++)
		{
			cities.set(k, temp.get(k));
		}
	}

	/**
	 * swap() merges swaps two elements in an arraylist.
	 * It swaps the elements in the 'first' and 'second' 
	 * locations. 
	 * 
	 * @param	first   An index of the arraylist to be swapped
	 * @param 	second  The second index of the arraylist
	 * 				    to be swapped.
	 * @return 		    void
	 */
	public void swap(int first, int second)
	{
		City aTemp = cities.get(first);
		cities.set(first, cities.get(second-1));
		cities.set(second-1, aTemp);
	}

	/**
	 * checkName() returns whether the state name of 
	 * a City object is less (lower down the alphabet)
	 * than the other City object. If both City objects
	 * have the same state name, it will check to see
	 * which one has a higher population.
	 * 
	 * @param	aTemp   City object to be compared
	 * @param 	second  Index of the cities arraylist
	 * 					to be compared with (essentially
	 * 					the "second City object")
	 * @return 			"True" if state name is lower down 
	 *   				the alphabet or if population
	 * 					is greater and "false" if neither
	 * 					of those are true. 
	 */
	public boolean checkName(City aTemp, int second)
	{
	  City bTemp = cities.get(second);

	  return (aTemp.getName().compareTo(bTemp.getName()) < 0 || 
	        (aTemp.getName().compareTo(bTemp.getName()) == 0 && 
	         aTemp.compareTo(bTemp) > 0));
	}
}

