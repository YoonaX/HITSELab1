package lab1;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Digraph
{
	private Vector<DigraphNode> HeadNodeList;
	public Vector<String> NodeList;
	public Vector<String> FileText;
	private Map<String, Boolean> Visit;
    private String FileLastString;
    public Vector<String>BridgeWords;
    public Vector<PQueue> NodeShortPath = new Vector<PQueue>();
    public Vector<String> KeyNode = new Vector<String>();
    public Vector<String> ShortPath = new Vector<String>();


	public Digraph()
	{
		HeadNodeList = new Vector<DigraphNode >();
		NodeList = new Vector<String>();
		FileText = new Vector<String>();
		BridgeWords = new Vector<String>();
		Visit = new HashMap<String, Boolean>();

	}

	public void Insert(String CurNodeKey, String PosNodeKey)
	{
		DigraphNode NewNode = new DigraphNode();
		NewNode.Words = PosNodeKey;
		int i = 0;
		int Size = HeadNodeList.size();
		for (; i < Size; i++)
		{
			if (CurNodeKey.equals (HeadNodeList.get(i).Words))//遍历表头节点,单词连续重复的情况
			{
				DigraphNode Node = HeadNodeList.get(i);

				while (Node != null)
				{
					if (Node.Words.equals(PosNodeKey))
					{
						Node.Weight++;
					    break;
					}
					Node = Node.Next;
				}
				if (Node == null)
				{
					NewNode.Next = (HeadNodeList.get(i)).Next;
					NewNode.Weight = 1;
					(HeadNodeList.get(i)).Next = NewNode;
					(HeadNodeList.get(i)).AdjPointNumber++;
				}
				break;
			}
		}
		if (i == Size)//出现新的表头节点
		{
			DigraphNode HeadNode = new DigraphNode();
			HeadNode.Next = HeadNode.Next = NewNode;//改
			HeadNode.Words = CurNodeKey;
			HeadNode.AdjPointNumber ++;
			NewNode.Next = null;
			NewNode.Weight = 1;
		    HeadNodeList.addElement(HeadNode);
		}
	}

	public void ReadFileBuildDigraph(String FileName)
	{
		File file = new File(FileName);
		try(
		Reader reader = new InputStreamReader(new FileInputStream(file)))
		{
			int tempchar;
			int CurFlag = 1;
			StringBuffer CurNodeKey = new StringBuffer("");
			StringBuffer PosNodeKey = new StringBuffer("");
			while((tempchar = reader.read()) != -1)
			{
				if (tempchar != '\r')
				{
					int x = ChangeWords(CurFlag, CurNodeKey, PosNodeKey, tempchar);

					CurFlag = x;
				}
		    }


			if (!PosNodeKey.toString().equals(""))
			{
				Insert(CurNodeKey.toString(), PosNodeKey.toString());
				FileText.addElement(CurNodeKey.toString());//
				FileText.addElement(PosNodeKey.toString());//
				if (!NodeList.contains(CurNodeKey.toString()))
			    {
					NodeList.addElement(CurNodeKey.toString());//
			    }
				if (!NodeList.contains(PosNodeKey.toString()))
			    {
					NodeList.addElement(PosNodeKey.toString());//
			    }
			}
			else
			{
				FileText.addElement(FileLastString);//
				if (!NodeList.contains(FileLastString))
			    {
			    	Visit.put(FileLastString, false);
			        NodeList.addElement(FileLastString);
			    }//
			}
	}
	catch(IOException e){
	    e.printStackTrace();}

}

	public int ChangeWords(int CurFlag, StringBuffer CurNodeKey, StringBuffer PosNodeKey, int tempchar)
	{
		if ( ((char)tempchar >= 'a' && (char)tempchar <= 'z')
	     ||  ((char)tempchar >= 'A' && (char)tempchar <= 'Z'))
		{
			if ((char)tempchar >= 'A' && (char)tempchar <= 'Z')
                tempchar += 32;//大小写转换
			if (CurFlag == 1)
				CurNodeKey.append((char)tempchar);
			else
				PosNodeKey.append((char)tempchar);
		}
		else
		{
			if (CurFlag == 0 && !(PosNodeKey.toString()).equals(""))
			{
				if(!( CurNodeKey.toString()).equals(""))
				{Insert(CurNodeKey.toString(), PosNodeKey.toString());
				FileText.addElement(CurNodeKey.toString());//

				if (!NodeList.contains(CurNodeKey.toString()))
			    {
			    	Visit.put(CurNodeKey.toString(), false);
			        NodeList.addElement(CurNodeKey.toString());
			    }//
				}
				CurNodeKey.replace(0, CurNodeKey.length(), PosNodeKey.toString());
			    CurNodeKey = new StringBuffer(PosNodeKey.toString());
			    FileLastString = PosNodeKey.toString();
			    PosNodeKey.delete(0, PosNodeKey.length());
			    PosNodeKey = new StringBuffer("");
			}
			else
				CurFlag = 0;
		}
		return CurFlag;
	}


	public String GetBridgeWords(String StartString, String EndString)
	{
		String Str;
		BridgeWords.clear();
		KeyNode.clear();
		KeyNode.add(StartString);
		KeyNode.add(EndString);
		Vector<String> BridgeWords = new Vector<String>();
		for (int i = 0; i < HeadNodeList.size(); i++)
		{
			if (StartString.equals(HeadNodeList.get(i).Words))
			{
				if (NodeList.contains(EndString))
				{
					GBridgeWords(StartString, EndString, i);
					if (BridgeWords.size() >= 1)
						return BridgeWords.get(0);
					else
					{
						Str = "No bridge words from " + "\"" + StartString + "\"" +" to \"" + EndString + "\" !";
					    return Str;
					}

				}
				Str =  "No "  + "\"" + EndString + "\" in the graph!";
				return Str;
			}
		}
		if (NodeList.contains(EndString))
			Str = "No "  + "\"" + StartString + "\" in the graph!";
		else
			Str = "No "  + "\"" + StartString + "\"  and \"" + EndString + "\" in the graph!";
		return Str;
	}

	public void GBridgeWords(String StartString, String EndString, int i)
	{
		for (int j = 0; j < NodeList.size(); j++)
			Visit.put(NodeList.get(j), false);
		int Distance = 1;
		DFS(HeadNodeList.get(i), Distance, EndString);
	}

	public void DFS(DigraphNode Node, int Distance, String EndString)
	{
		Visit.put(Node.Words, true);
    	DigraphNode NextNode = Node.Next;
        while (NextNode != null)
        {
        	if (!Visit.get(NextNode.Words))
        	{
        		if (Distance <= 2)
        		{
	        		if (Distance == 2)
	        		{
	        			if (EndString.equals(NextNode.Words) )
	        			{
	        		        BridgeWords.addElement(Node.Words);
	        		        KeyNode.addElement(Node.Words);
	        			}
	        		}
	        		else
	        		{
		        		for (int i = 0; i < HeadNodeList.size(); i++)
		        			if ((NextNode.Words).equals(HeadNodeList.get(i).Words))
		        			{
		        				DFS(HeadNodeList.get(i), Distance + 1, EndString);
		        			    break;
		        			}
	        		}
        		}
        	}
        	NextNode = NextNode.Next;
        }
	}

	public String GetNewFiles(String Text)
	{
		String Result = "";
		String[] Words = Text.split(" ");
		Result += Words[0];
		for (int i = 0; i < Words.length - 1; i++)
		{
			BridgeWords.clear();
			GetBridgeWords(Words[i], Words[i + 1]);
			if (BridgeWords.size() != 0)
			{
			    Result += (" " + BridgeWords.get((int)(Math.random() * BridgeWords.size())));
			}
			Result += (" " + Words[i + 1]);
		}
		return Result;
	}

	public String TwoPointsGetShortPath(String Start, String End)
	{
		KeyNode.clear();
		Comparator<PQueue> Order = new Comparator<PQueue>()
		{
			public int compare(PQueue S1, PQueue S2)
			{
				return (S1.Costs - S2.Costs);
			}
		};

		PriorityQueue<PQueue> D = new  PriorityQueue<PQueue>(Order);

		NodeShortPath.clear();

		OnePointGetShortPath(D, NodeShortPath, Start);


		for (int i = 0;i < NodeShortPath.size(); i++)
	    {
	    	if (NodeShortPath.get(i).End.equals(End))
	    	{
	    		ShortPath = NodeShortPath.get(i).P;
	    		break;
	    	}
	    }

	    String S = "\"" + Start + "\" 与 " + "\"" + End + "\" 不可达";
	    return S;
}

	public void DInit(PriorityQueue<PQueue> D, Vector<PQueue> NodeShortPath, String Start)
	{
		for (Iterator<String> it = NodeList.iterator(); it.hasNext();)//遍历点集合
	    {
			String Str = it.next();
			PQueue Node = new PQueue();
			PQueue S = new PQueue();//记录最短路径
	        S.Path = new Vector<String>();
			if (!Str.equals(Start))//如果不是起始点
			{
				Node.Costs = 1000000;//初始D数组
				Node.End = Str;

		        D.add(Node);

		        S.Path.addElement(Start);
		        S.End = Str;
		        NodeShortPath.addElement(S);//出发点到所有点的最短路径
			}
	    }
	}

	public void UpdataStart(PriorityQueue<PQueue> D, Vector<PQueue> NodeShortPath, String Start)
	{
		for (int i = 0; i < HeadNodeList.size(); i++)
		{
			if (HeadNodeList.get(i).Words.equals(Start))
			{
				DigraphNode Node  = HeadNodeList.get(i).Next;
				while (Node != null)
				{
					PQueue SNode = new PQueue();
					for (PQueue SP: D)
				        if (SP.End.equals(Node.Words))//找到图的邻接点在D中对应的对象
	    			    {
		    				SNode = SP;
		    				SNode.Costs = Node.Weight;
		    				D.remove(SP);
		    				D.add(SNode);

		    				for (int k = 0; k < NodeShortPath.size(); k++)//更新路径
		                    {
		                    	if (NodeShortPath.get(k).End.equals(SNode.End))
		                        {
		                    		NodeShortPath.get(k).Costs = SNode.Costs;
		                    		NodeShortPath.get(k).Path.addElement(SNode.End);
		                    		NodeShortPath.get(k).P.addElement((Start + " " + SNode.End));//
		                    		break;
		                        }
		                    }

		    				break;
	    			    }
					Node = Node.Next;
				}
				break;
			}		}

	}

	public void OnePointGetShortPath(PriorityQueue<PQueue> D, Vector<PQueue> NodeShortPath, String Start)
	{
		DInit(D, NodeShortPath, Start);

        UpdataStart(D, NodeShortPath, Start);
	    while(D.peek() != null)//出队列
	    {
	    	PQueue Node = D.poll();//优先队列第一个点
	    	String TempNodeString = Node.End;
	    	int Costs = Node.Costs;
	        int i = 0;
	    	for (; i < HeadNodeList.size(); i++)//找到顶点图中对应顶点
	        	if (HeadNodeList.get(i).Words.equals(TempNodeString))
	        		break;
	        if (i != HeadNodeList.size())
	        {
	    	    DigraphNode node = HeadNodeList.get(i).Next;//领接点
	    	    while (node != null)
		    	{
		    		 for (PQueue SP: D)
    	                 if (SP.End.equals(node.Words))//找到图的邻接点在D中对应的对象
    		             {
                            if (Costs +  node.Weight <= SP.Costs)//用当前中间点来其他距离
                            {
                            	int A = 0;
			    				for (; A < NodeShortPath.size(); A++)
	                        	{
	                        	    if (NodeShortPath.get(A).End.equals(Node.End))
	                        	    	break;
	                        	}

		                        for (int B = 0; B < NodeShortPath.size(); B++)
	                        	{
		                        	if (NodeShortPath.get(B).End.equals(node.Words))
	                        	    {
			                        	if (Costs +  node.Weight == SP.Costs)
	                        	    	{
	                        	    		String S = "";
	                        	    		int I = 0;
	                        	    		for (; I < NodeShortPath.get(A).P.size(); I++)
		                        	    	{
	                        	    			S = "";
	                        	    			S = (NodeShortPath.get(A).P.get(I) + " ");
	                        	    			S += node.Words;
	                        	    			NodeShortPath.get(B).P.add(S);
		                        	    	}

	                        	    	}
	                        	    	else
	                        	    	{
	                        	    		String S = "";
		                        	    	for (int I = 0; I < NodeShortPath.get(A).P.size(); I++)
		                        	    	{
		                        	    		S =  NodeShortPath.get(A).P.get(I);
		                        	    		S += (" " + node.Words);
		                        	    		NodeShortPath.get(B).P.addElement(S);
		                        	    		S = "";
		                        	    	}
	                        	    	}
	                        	    }

	                        	    if (NodeShortPath.get(B).End.equals(node.Words))
	                        	    {
	                        	    	if (Costs +  node.Weight < SP.Costs)
	                        	    	{
	                        	    	    NodeShortPath.get(B).Costs = Costs + node.Weight;//更新D;
	                        	    	    PQueue SPNode = SP;
	    			            	        SPNode.Costs = Costs + node.Weight;//更新D;
	    			            	        D.remove(SP);
	    				    				D.add(SPNode);
	                        	    	    NodeShortPath.get(B).Path.clear();

	                        	    	}

	                        	    	for (int I = 0; I < NodeShortPath.get(A).Path.size(); I++)
	                        	    		NodeShortPath.get(B).Path.addElement(NodeShortPath.get(A).Path.get(I));
	                        	    	NodeShortPath.get(B).Path.addElement(node.Words);

	                        	    	break;
	                        	    }
	                        	}
                            }
                            break;
    		            }
    		        node = node.Next;
		    	  }
	        }
	    }
	}

	public void Traverse()
	{
		for (int i = 0; i < NodeList.size(); i++)
			Visit.put(NodeList.get(i), false);
		for (int i = 0; i < NodeList.size(); i++)
			if (!Visit.get(NodeList.get(i)))
			{
				for (int j = 0; j < HeadNodeList.size(); j++)
				    if (HeadNodeList.get(j).Words.equals(NodeList.get(i)))
				    {
				    	DFSTraverse(HeadNodeList.get(j));
				        break;
				    }
			}
	}

	public void DFSTraverse(DigraphNode Node)
	{
		Visit.put(Node.Words, true);

		DigraphNode NextNode = Node.Next;

		while (NextNode != null)
		{
			if (!Visit.get(NextNode.Words))
			{
				int i = 0;
				for (; i < HeadNodeList.size(); i++)
				    if (HeadNodeList.get(i).Words.equals(NextNode.Words))
				        break;

				if (i != HeadNodeList.size())
		            DFSTraverse(HeadNodeList.get(i));
			}
			NextNode = NextNode.Next;
		}
	}

	public void picture()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		for(DigraphNode node : HeadNodeList)
		{
			while(node != null )
			{
				System.out.print(node.Words+" ");
				node=node.Next;
			}
			System.out.println();
		}

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null )
			{
				gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());

		String type = "png";
	//	String type = "dot";
	//	String type = "fig";    // open with xfig
	//	String type = "pdf";
	//	String type = "ps";
	//	String type = "svg";    // open with inkscape
	//	String type = "png";
	//	String type = "plain";
		//File out = new File("/out." + type);   // Linux
		File out = new File("D:\\out." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public void picture_qjc()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null && KeyNode.size()>=1)
			{
				if(head.Words.equals(KeyNode.get(0)) && !node.Words.equals(KeyNode.get(1)) && KeyNode.contains(node.Words))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=red];"+ node.Words+"[color=blue];");
				}
				else if(KeyNode.contains(head.Words) && node.Words.equals(KeyNode.get(1)))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=blue];"+ node.Words+"[color=red];");
				}
				else
				{
					gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				}
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());
		/*for(String a : KeyNode)
		{
			System.out.print(a+" ");
		}*/

		String type = "png";
		File out = new File("D:\\qjcout." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public boolean In(String word1, String word2)
	{
		for(int i=0; i<ShortPath.size(); i++)
		{
			if(KeyNode.contains(word1) && KeyNode.contains(word2) && KeyNode.indexOf(word1, i*KeyNode.size()/ShortPath.size())+1==KeyNode.indexOf(word2, i*KeyNode.size()/ShortPath.size()))
			{
				return true;
			}
		}
		return false;
	}

	public void picture_zdlj()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		KeyNode.clear();

		for(int i=0; i<ShortPath.size(); i++)
		{
			String[] tmp = ShortPath.get(i).split(" ");
			for(int j=0; j<tmp.length; j++)
				KeyNode.add(tmp[j]);
		}

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null && KeyNode.size()>=1)
			{
				if(In(head.Words, node.Words))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=red];"+ node.Words+"[color=red];");
				}

				else
				{
					gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				}
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());
		/*for(String a : KeyNode)
		{
			System.out.print(a+" ");
		}*/

		String type = "png";
		File out = new File("D:\\zdljout." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public boolean In_sjyz(String word1, String word2)
	{
		for(int i=0; i<KeyNode.size()-1; i++)
		{
			if(KeyNode.get(i).equals(word1) && KeyNode.get(i+1).equals(word2))
				return true;
		}
		return false;
	}
	public void picture_sjyz()
	{
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		for(DigraphNode node : HeadNodeList)
		{
			DigraphNode head = node;
			node=node.Next;
			while(node != null && KeyNode.size()>=1)
			{
				if(KeyNode.contains(head.Words))
				{
					gv.addln(head.Words+"[color=red]");
				}
				if(KeyNode.contains(node.Words))
				{
					gv.addln(node.Words+"[color=red]");
				}
				if(KeyNode.contains(head.Words) && KeyNode.contains(node.Words) && In_sjyz(head.Words, node.Words))
				{
					gv.addln(head.Words + " -> " + node.Words + "[color=red, label="+ node.Weight + "];" + head.Words+"[color=red];"+ node.Words+"[color=red];");
				}
				else
				{
					gv.addln(head.Words + " -> " + node.Words + "[label="+ node.Weight + "];");
				}
				node=node.Next;
			}
		}

		gv.addln(gv.end_graph());
		//System.out.println(gv.getDotSource());
		/**for(String a : KeyNode)
		{
			System.out.print(a+" ");
		}*/

		String type = "png";
		File out = new File("D:\\sjyzout." + type);    // Windows
		gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}

	public String RandomWalk(String pre)
	{
		if(pre=="")//初始化
		{
			KeyNode.clear();
			for(DigraphNode n : HeadNodeList)
			{
				while(n!=null)
				{
					n.flag=false;
					n=n.Next;
				}
			}
			String word = NodeList.get( (int)(NodeList.size() * Math.random() ));
			KeyNode.add(word);
			return word;
		}

		int i;
		for(i=0; i<HeadNodeList.size(); i++)
		{
			if(HeadNodeList.get(i).Words.equals(pre))
				break;
		}
		if(i==HeadNodeList.size())
			return "-end-";
		else
		{
			DigraphNode node = HeadNodeList.get(i);
			int child=(int)(Math.random() * node.AdjPointNumber);
			for(i=0; i<=child ; i++)
			{
				node = node.Next;
			}
			if(node.flag==true)
			{
				KeyNode.add(node.Words);
				return "-end-"+node.Words;
			}
			else
			{
				node.flag=true;
				KeyNode.add(node.Words);
				return node.Words;
			}
		}
	}
}

