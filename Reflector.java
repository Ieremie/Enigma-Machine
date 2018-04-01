
public class Reflector extends Rotor {
	
	//sets the reflector's name and its mapping
	Reflector (String name)
	{
		super(name);
		initialise();
	}

	//sets the mapping of the reflector accordingly to its type
	@Override
	protected void initialise()
	{
		if (name.equals("ReflectorI"))
			mapping = new int[] { 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 
				13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
		else
			mapping = new int[]	{ 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17,
				25,	23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };
	}

	//returns the corresponding value of the received parameter
	@Override
	protected int substitute(int x)
	{
		
		return mapping[x];
	}
}
