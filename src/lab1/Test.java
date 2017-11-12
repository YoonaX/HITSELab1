package lab1;

import java.util.PriorityQueue;
import java.util.Vector;
import java.util.Comparator;

public class Test
{
	public static void main(String[] args)
	{

		Comparator<PQueue> Order = new Comparator<PQueue>()
		{
			public int compare(PQueue S1, PQueue S2)
			{
				return (S1.Costs - S2.Costs);
			}
		};
        System.out.println("�ڽӵ㣺");
		Digraph D = new Digraph();
		String File = "C:/Users/Ponyztx/Desktop/Lab1.txt";
		D.ReadFileBuildDigraph(File);

        String Str = D.GetBridgeWords("the", "data");
        if (D.BridgeWords.size() == 0)
        	System.out.println(Str);
        else
        {
        	System.out.print("and �� are  has  Bridges:\n");
        	for (int i = 0; i < D.BridgeWords.size(); i++)
        		System.out.print(D.BridgeWords.get(i) + " -> ");
        }
        System.out.print("\n\n");

        System.out.println("һ���㣺");
        Vector<PQueue> NodeShortPath = new  Vector<PQueue>();
        PriorityQueue<PQueue> PQ = new  PriorityQueue<PQueue>(Order);
        D.OnePointGetShortPath(PQ, NodeShortPath, "in");
        for (int i = 0; i < NodeShortPath.size(); i++)
        {
        	System.out.println("in to " + NodeShortPath.get(i).End + " ȨֵΪ��" + NodeShortPath.get(i).Costs);
        	for (int j = 0; j < NodeShortPath.get(i).P.size(); j++)
        	System.out.println(NodeShortPath.get(i).P.get(j));
            System.out.print("\n\n");
        }


        System.out.println("�����㣺");
        D.TwoPointsGetShortPath("the", "of");
        for (int i = 0;i < D.NodeShortPath.size(); i++)
	    {
	    	if (D.NodeShortPath.get(i).End.equals("time"))
	    	{
	    		System.out.println("in to of " + " ȨֵΪ��" + D.NodeShortPath.get(i).Costs);
	    		break;
	    	}
	    }
        Vector<String> ShortPath = D.ShortPath;
        for (int i = 0; i < ShortPath.size(); i++)
        	System.out.println(ShortPath.get(i));

        System.out.println("\n\nstart randomwalking:");
		String word = "";
		String preword ="";
		boolean flag = true;

		do {
			preword = D.RandomWalk(word);
			if (!preword.equals(word) || !preword.equals("-end-"))
			{
				if (preword.length()>=5 && preword.subSequence(0, 5).equals("-end-"))
				{
					String s = preword.substring(5);
					System.out.print(s + " ");
				}
				else
				{
					System.out.print(preword + " ");
				}
			}
			else
			{
				flag = false;
				//System.out.println("End");
			}
			word = preword;
		} while (flag);
	}
}
