
public class BasicRotor extends Rotor{
	private int[] inverseMapping;
	
	//sets the Basic Rotor's name and mapping
	BasicRotor (String name)
	{
		super(name);
		initialise();
	}

	//sets the mapping and inverse mapping accordingly to the type of the rotor
	@Override
	protected void initialise()
	{
		String name = this.name;
		
		//cuts the unnecessary parts of the rotor's name
		if (name.length() > 4 && name.substring(0, 4).equals("turn"))
			name = this.name.substring(4, name.length());
		
		//sets the mapping
		switch (name)
		{
			case "I": mapping = new int[] {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14,
											22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
					break;
			
			case "II": mapping = new int[] {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22,
											19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4};
					break;
					
			case "III": mapping = new int[] {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 
												24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};
					break;
			
			case "IV": mapping = new int[] {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7,
												23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
					break;
			
			case "V": mapping = new int[] {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7,
												11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};
					break;
			
		}
		
		//sets the inverse mapping
		inverseMapping = new int[ROTORSIZE];
		for (int i = 0; i < ROTORSIZE; i++)
			inverseMapping[mapping[i]] = i;
	}

	//returns an int corresponding to a character to the required int
	@Override
	protected int substitute(int x)
	{
		int temp = (x - position);
		//if temp is negative, it will decrease from the maximum value of rotor's size
		if (temp < 0)
			temp = ROTORSIZE + temp;
		return (mapping[temp] + position) % ROTORSIZE;
	}
	
	//returns an int corresponding to a character, but using the inverse mapping
	protected int substituteBack (int x)
	{
		int temp = (x - position);
		if (temp < 0)
			temp = ROTORSIZE + temp;
		return (inverseMapping[temp] + position) % ROTORSIZE;
	}
	
	//increases the position of the rotor by 1, resetting to 0 if it goes over the size
	protected void rotate ()
	{
		position = (position + 1) % ROTORSIZE;
	}
}
