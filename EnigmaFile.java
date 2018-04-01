import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EnigmaFile {
	private FileReader input;
	private FileWriter output;
	private BufferedReader inputBuff;
	private BufferedWriter outputBuff;
	private String message;
	
	//creates the files and the buffers and gets the input message
	EnigmaFile () throws IOException
	{
		input = new FileReader ("input.txt"); 
		inputBuff = new BufferedReader (input);
	    output = new FileWriter ("output.txt");
		outputBuff = new BufferedWriter (output);
		message = inputBuff.readLine();
	}
	
	//decrypts a message
	public String decryptMessage(EnigmaMachine enigma) throws IOException
	{
		char[] encrypted = new char[message.length()];
		for (int i = 0; i < message.length(); i++)
		{
			encrypted[i] = enigma.encodeLetter(message.charAt(i));
		}
		return new String(encrypted);
	}
	
	//writes output to the output file
	public void writeOutput (String toWrite) throws IOException
	{
		outputBuff.write(toWrite);
		outputBuff.flush();
	}
}
