
public class Plug {
	private char socketEnd1, socketEnd2;
	
	//sets the ends of the plug to the received chars
	public Plug (char socketEnd1, char socketEnd2)
	{
		setEnd1(socketEnd1);
		setEnd2(socketEnd2);
	}
	
	//returns the char corresponding to the first end
	public char getEnd1 ()
	{
		return socketEnd1;
	}
	
	//returns the char corresponding to the second end
	public char getEnd2 ()
	{
		return socketEnd2;
	}
	
	//sets the character of the first end
	public void setEnd1 (char socketEnd1)
	{
		this.socketEnd1 = socketEnd1;
	}
	
	//sets the character of the second end
	public void setEnd2 (char socketEnd2)
	{
		this.socketEnd2 = socketEnd2;
	}
	
	//returns the letter on the other side of the plug, if there is a plug
	public char encode (char letterIn)
	{
		if (socketEnd1 != letterIn && socketEnd2 != letterIn)
		{
			return letterIn;
		}
		if (socketEnd1 == letterIn)
		{
			return socketEnd2;
		}
		return socketEnd1;
	}
	
	//checks if this plugin clashes with another one received as parameter
	public boolean clashesWith (Plug plugin)
	{
		if (plugin.socketEnd1 == socketEnd1 || plugin.socketEnd1 == socketEnd2 ||
			plugin.socketEnd2 == socketEnd1 || plugin.socketEnd2 == socketEnd2)
			return true;
		return false;
	}
}
