import java.io.IOException;
import java.util.Scanner;

public class EnigmaMachine {
	static private Plugboard plugboard;
	static private BasicRotor[] rotors = new BasicRotor[3];
	static private Reflector reflector;

	//creates a plugboard
	EnigmaMachine ()
	{
		plugboard = new Plugboard();
	}
	
	//adds a plug to the plugboard connecting the received characters
	public static void addPlug(char socketEnd1, char socketEnd2)
	{
		plugboard.addPlug(socketEnd1, socketEnd2);
	}

	//clears the plugboard
	public void clearPlugboard()
	{
		plugboard.clear();
	}

	//adds a rotor with the specified name in the specified slot
	public static void addRotor(String name, int slot)
	{
		//checks if it is a turnover rotor
		if (name.length() >= 4 && name.substring(0, 4).equals("turn"))
		{
			if (slot != 2)
				rotors[slot] = new TurnoverRotor (name, rotors[slot + 1]);
			else
				rotors[slot] = new TurnoverRotor (name, null);
		}	
		else
			rotors[slot] = new BasicRotor (name);
	}
	//returns the rotor in the specified slot
	public static BasicRotor getRotor(int slot)
	{
		return rotors[slot];
	}

	//adds a reflector with the specified name
	public static void addReflector(String name)
	{
		reflector = new Reflector(name);
	}

	//return the reflector
	public static Reflector getReflector()
	{
		return reflector;
	}

	//sets the position of a rotor from a specified slot to a specified position
	public static void setPosition(int slot, int position)
	{
		rotors[slot].setPosition(position);
	}

	//main function, creates a bombe
	public static void main (String[] args) throws IOException
	{
		Bombe bombe = new Bombe();
	}
	
	//encodes a letter using the plugboard, 3 rotors, reflector, 3 rotors and the plugboard again
	public static char encodeLetter(char c)
	{
		c = plugboard.substitute(c);
		int cInt;
		
		cInt = rotors[0].substitute(c - 'A');
		cInt = rotors[1].substitute(cInt);
		cInt = rotors[2].substitute(cInt);
		
		cInt = reflector.substitute(cInt);
		
		cInt = rotors[2].substituteBack(cInt);
		cInt = rotors[1].substituteBack(cInt);
		cInt = rotors[0].substituteBack(cInt);
		
		rotors[0].rotate();
		
		c = (char)(cInt + 'A');
		c = plugboard.substitute(c);
		return c;
	}
	private String message;
	
	//sets up the enigma machine with the user's preferred rotors and plugs
	public static void start ()
	{
		Scanner scanner = new Scanner (System.in);
		String line;
		System.out.println("Connect the plugs. Format is : 'A B' or 'finish' ");
		
		//connects plugs
		while ((line = scanner.nextLine()).equals("finish") == false)
		{
			addPlug(line.charAt(0), line.charAt(2));
		}
		
		System.out.println("Add a rotor in slot 2 and its position. Careful, this is the LAST SLOT, not the first! Format is 'I 20', for turnover it is 'turnI 20'");	
		line = scanner.nextLine();
		addRotor (line.split(" ")[0], 2);
		setPosition (2, Integer.parseInt(line.split(" ")[1]));
		
		System.out.println ("Now add a rotor in slot 1");
		line = scanner.nextLine();
		addRotor (line.split(" ")[0], 1);
		setPosition (1, Integer.parseInt(line.split(" ")[1]));
		
		System.out.println ("Now add a rotor in slot 0");
		line = scanner.nextLine();
		addRotor (line.split(" ")[0], 0);
		setPosition (0, Integer.parseInt(line.split(" ")[1]));
		
		System.out.println("Last step. Which Reflector do you want? ReflectorI or ReflectorII");
		line = scanner.nextLine();
		addReflector(line);
		
	}
}
