import java.io.IOException;
import java.util.Scanner;

public class Bombe {
	private EnigmaMachine enigma;
	private EnigmaFile file;
	
	//creates an enigma machine, an input and output and checks if a challenge is required to be ran
	public Bombe () throws IOException
	{
		System.out.println("Do you want to test the Bombe or the Enigma Machine? The format for input is: 'bombe' or 'enigma'");
		Scanner scanner = new Scanner (System.in);
		String line;
		line = scanner.nextLine();
		enigma = new EnigmaMachine ();
		file = new EnigmaFile();
		if (line.equals("enigma"))
		{
			enigma.start();
			file.writeOutput(file.decryptMessage(enigma));
			
		}
		//it runs the challenge chose by the user
		else
		{
			System.out.println("Which challenge? 1, 2 or 3?");
			line = scanner.nextLine();
			if (line.charAt(0) == '1')
				challenge1();
			if (line.charAt(0) == '2')
				challenge2();
			if (line.charAt(0) == '3')
				challenge3();
	
		}
	}
	private String message;
	//solves the first challenge
	private void challenge1 () throws IOException
	{
		//sets up the known parts of the enigma machine
		String keyword = "ANSWER";
		enigma.addRotor("IV", 0);
		enigma.setPosition(0, 8);
		
		enigma.addRotor("III", 1);
		enigma.setPosition(1, 4);
		
		enigma.addRotor("II", 2);
		enigma.setPosition(2, 21);
		
		enigma.addReflector("ReflectorI");
		
		//tries all the possible ways of connecting the two half given plugs
		for (char i = 'A'; i <= 'Z'; i++)
		{
			for (char j = 'A'; j <= 'Z'; j++)
			{
				enigma.clearPlugboard();
				enigma.addReflector("ReflectorI");
				enigma.addPlug('D', i);
				enigma.addPlug('S', j);
				message = file.decryptMessage(enigma);
				if (message.contains(keyword))
				{
					file.writeOutput((message + " plug1: D " + i + " plug2: S " + j));
					file.writeOutput("\n");
				}
			}
		}
	}
	
	//solves the second challenge
	private void challenge2 () throws IOException
	{
		String keyword = "ELECTRIC";
		
		//sets up the enigma machine with the known information
		enigma.addPlug('H', 'L');
		enigma.addPlug('G', 'P');
		
		enigma.addRotor("V", 0);
		enigma.addRotor("III", 1);
		enigma.addRotor("II", 2);
		
		enigma.addReflector("ReflectorI");
		
		//tries all the possible positions of the rotors
		for (int i = 0; i <= 25; i++)
			for (int j = 0; j <= 25; j++)
				for (int c = 0; c <= 25; c++)
				{
					enigma.getRotor(0).setPosition(i);
					enigma.getRotor(1).setPosition(j);
					enigma.getRotor(2).setPosition(c);
					message = file.decryptMessage(enigma);
					message.contains(keyword);
					if (message.contains(keyword))
					{
						file.writeOutput(message + " slot 0 pos: " + i + " slot 1 pos: " + j + " slot 2 pos:" + c);
					}
				}
	}
	
	//solves the third challenge
	private void challenge3 () throws IOException
	{
		String keyword = "JAVA";
		
		//sets up the enigma machine with the known information
		String name = "", temp;
		String types[] = new String[5];
		types[0] = "I"; types[1] = "II"; types[2] = "III"; types[3] = "IV"; types[4] = "V"; 
		enigma.addPlug('M', 'F');
		enigma.addPlug('O', 'I');
		enigma.addReflector("ReflectorI");
		
		//tries all the possible types of the rotors
		for (int i = 0; i <= 4; i++)
			for (int j = 0; j <= 4; j++)
				for (int c = 0; c <= 4; c++)
				{
					temp = name.concat(types[c]);
					enigma.addRotor(temp, 0);
					enigma.getRotor(0).setPosition(22);
					
					temp = name.concat(types[j]);
					enigma.addRotor(temp, 1);
					enigma.getRotor(1).setPosition(24);
					
					temp = name.concat(types[i]);
					enigma.addRotor(temp, 2);
					enigma.getRotor(2).setPosition(23);
					
					message = file.decryptMessage(enigma);
					message.contains(keyword);
					//System.out.println(message);
					if (message.contains(keyword))
					{
						temp = name.concat(types[c]);
						file.writeOutput((message + " slot 0 type: " + temp));
						temp = name.concat(types[j]);
						file.writeOutput((" slot 1 type: " + temp));
						temp = name.concat(types[i]);
						file.writeOutput((" slot 2 type: " + temp));
						
					}
				}
		
	}
}
