/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Ryan Hau
 *	@since	January 10th, 2022
 */
public class City implements Comparable<City> 
{
	
	// fields
	private String state; // Holds state name
	private String name; // Holds city name
	private String designation; // Holds city type
	private int population; // Holds city population
	
	/*Constructor() initializes the above field varables by setting
	  them equal to parameters*/
	public City (String state, String name, String designation, 
						int population)
	{
		this.state = state;
		this.name = name;
		this.designation = designation;
		this.population = population;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	 public int compareTo(City other)
	 {
		if (other.population != population)
			return this.population - other.population;
		else if (!other.state.equals(state))
			return this.state.compareTo(other.state);
		else
			return this.name.compareTo(other.name);
	 }
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 public boolean equals(City other)
	 {
		if (state.equals(other.name))
			return true;
		else
			return false;
	 }

	/**	Accessor methods */

	// Gets the state name
	public String getState()
	{
		return state;
	}

	// Gets the city name
	public String getName()
	{
		return name;
	}

	// Gets the city type
	public String getDesig()
	{
		return designation;
	}

	// Gets the city population
	public int getPop()
	{
		return population;
	}
	
	/**	Formats a string of the above city information using String.format
	 * 
	 *	@return		City information and details
	 */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
