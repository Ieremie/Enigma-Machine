public abstract class Rotor {
	protected String name;
	protected int position;
	protected int[] mapping;
	protected static final int ROTORSIZE = 26;
	
	//sets the rotor's name
	public Rotor (String name)
	{
		this.name = name;
	}
	
	//sets the rotor's position
	public void setPosition (int pos)
	{
		position = pos;
	}
	
	//returns rotor's position
	public int getPosition ()
	{
		return position;
	}
	
	protected abstract void initialise();
	protected abstract int substitute (int x);
}
