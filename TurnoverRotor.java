
public class TurnoverRotor extends BasicRotor{
	private BasicRotor nextRotor = null;
	private int turnoverPosition;
	
	//sets the turnover rotor's name, turnover positions and next rotor
	public TurnoverRotor(String name, BasicRotor nextRotor) {
		super(name);
		setTurnoverPositions();
		setNextRotor(nextRotor);
	}

	//sets the turnover positions accordingly with the type of the rotors
	private void setTurnoverPositions ()
	{
		if (name.equals("turnI"))
			turnoverPosition = 24;
		
		if (name.equals("turnII"))
			turnoverPosition = 12;
		
		if (name.equals("turnIII"))
			turnoverPosition = 3;
		
		if (name.equals("turnIV"))
			turnoverPosition = 17;
		
		if (name.equals("turnV"))
			turnoverPosition = 7;
	}
	
	//sets the next rotor
	private void setNextRotor (BasicRotor nextRotor)
	{
		this.nextRotor = nextRotor;
	}

	//increases its position with 1 and if it is a turnover position it also calls rotate method on the next rotor
	@Override 
	public void rotate()
	{
		position++;
		//resets to 0 if it reaches the limit
		position %= ROTORSIZE;
		if (position == turnoverPosition && nextRotor != null)
			nextRotor.rotate();
	}
	
}
