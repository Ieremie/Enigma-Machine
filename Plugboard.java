import java.util.ArrayList;

public class Plugboard {
	private ArrayList<Plug> plugs = new ArrayList<Plug>();
	
	//adds a plug to the plugboard, setting it's ends to the characters received as parameters
	public boolean addPlug (char socketEnd1, char socketEnd2)
	{
		Plug plug = new Plug (socketEnd1, socketEnd2);
		//checks if there is already a plug connected to the received chars
		for (int i = 0; i < plugs.size(); i++) 
		{
			if (plug.clashesWith(plugs.get(i)))
				return false;
		}
		plugs.add(plug);
		return true;
	}
	
	//returns the number of plugs
	public int getNumPlugs ()
	{
		return plugs.size();
	}
	
	//removes all the plugs from the plugboard
	public void clear ()
	{
		plugs.clear();
	}
	
	// returns the corresponding character for another character
	public char substitute (char toSubstitute)
	{
		char substituted = toSubstitute;
		//looks through the plugs list to see if there is any corresponding character
		for (int i = 0; i < plugs.size(); i++)
		{
			substituted = plugs.get(i).encode(toSubstitute);
			if (substituted != toSubstitute)
				break;
		}
		return substituted;
	}
}
