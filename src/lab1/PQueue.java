package lab1;

import java.util.Vector;

public class PQueue
{
	int Costs = 0;
	String End;
	Vector<String> Path;
	Vector<String> P;
	String PP[][];
	int Conut = 0;

	public PQueue()
	{
		PP = new String[100][100];
		P = new Vector<String>();
	}
}
