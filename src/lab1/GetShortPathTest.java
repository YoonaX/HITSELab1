package lab1;

import java.util.Vector;
import org.junit.Test;
import org.junit.Before;

public class GetShortPathTest {
	Digraph G = new Digraph();
	String File = "C:/Users/weibowen/Desktop/Lab1.txt";

	@Before
	public void setup() throws Exception {
		G.ReadFileBuildDigraph(File);
	}

	@Test
	public void test1() {
        String Result = G.TwoPointsGetShortPath("the", "of");
        if (G.NodeShortPath.size() == 0)
        	System.out.println(Result);
        else {
	        for (int i = 0;i < G.NodeShortPath.size(); i++) {
		    	if (G.NodeShortPath.get(i).End.equals("of")) {
		    		if (G.NodeShortPath.get(i).Costs == 0) {
		    			System.out.println(Result);
		                break;
		            }
		    		System.out.println("\"the\" to \"of\" " + " 权值为：" + G.NodeShortPath.get(i).Costs + " 路径如下：");
		    		Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
	    		    break;
		    	}
		    }
        }
        System.out.print("\n");
	}

	@Test
	public void test2() {
        String Result = G.TwoPointsGetShortPath("i", "the");
        for (int i = 0;i < G.NodeShortPath.size(); i++) {
	    	if (G.NodeShortPath.get(i).End.equals("the")) {
	    		if (G.NodeShortPath.get(i).Costs == 0) {
	    			System.out.println(Result);
	                break;
	            }
	    		System.out.println("\"i\" to \"the\" " + " 权值为：" + G.NodeShortPath.get(i).Costs);
	    		Vector <PQueue> S = G.NodeShortPath;
    		    for (int k = 0; k < S.get(i).P.size(); k++)
    	            System.out.println(S.get(i).P.get(k));
	    		break;
	    	}
	    }
        System.out.print("\n");
	}

	@Test
	public void test3() {
        String Result = G.TwoPointsGetShortPath("abcd1", "abc");
        if (G.NodeShortPath.size() ==0)
        	System.out.println(Result);
        else {
	        for (int i = 0;i < G.NodeShortPath.size(); i++) {
		    	if (G.NodeShortPath.get(i).End.equals("abc")) {
		    		if (G.NodeShortPath.get(i).Costs == 0) {
		    			System.out.println(Result);
		                break;
		            }
		    		System.out.println("\"abcd1\" to \"abc\" " + " 权值为：" + G.NodeShortPath.get(i).Costs + " 路径如下：");
		    		Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
		    		break;
		    	}
		    }
        }
        System.out.print("\n");
   }

	@Test
	public void test4() {
        String Result = G.TwoPointsGetShortPath("i", "abc");

        if (G.NodeShortPath.size() ==0)
        	System.out.println(Result);
        else
        {
	        for (int i = 0;i < G.NodeShortPath.size(); i++)
		    {
		    	if (G.NodeShortPath.get(i).End.equals("abc"))
		    	{
		    		if (G.NodeShortPath.get(i).Costs == 0)
		            {
		    			System.out.println(Result);
		                break;
		            }
		    		System.out.println("\"i\" to \"abc\" " + " 权值为：" + G.NodeShortPath.get(i).Costs + " 路径如下：");
		    		Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
		    		break;
		    	}
		    }
        }
        System.out.print("\n");
    }

	@Test
	public void test5() {
        String Result = G.TwoPointsGetShortPath("abc", "i");

        if (G.NodeShortPath.size() ==0)
        	System.out.println(Result);
        else
        {
	        for (int i = 0;i < G.NodeShortPath.size(); i++)
		    {
		    	if (G.NodeShortPath.get(i).End.equals("i"))
		    	{
		    		if (G.NodeShortPath.get(i).Costs == 0)
		            {
		    			System.out.println(Result);
		                break;
		            }
		    		System.out.println("\"abc\" to \"i\" " + " 权值为：" + G.NodeShortPath.get(i).Costs + " 路径如下：");
		    		Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
		    		break;
		    	}
		    }
        }
        System.out.print("\n");
    }

	@Test
	public void test6() {
        String Result = G.TwoPointsGetShortPath("i", "lucky");
        if (G.NodeShortPath.size() ==0)
        	System.out.println(Result);
        else
        {
	        for (int i = 0;i < G.NodeShortPath.size(); i++) {
		    	if (G.NodeShortPath.get(i).End.equals("lucky")) {
		    		if (G.NodeShortPath.get(i).Costs == 0) {
		    			System.out.println(Result);
		                break;
		            }
		    		System.out.println("\"i\" to \"lucky\" " + " 权值为：" + G.NodeShortPath.get(i).Costs + " 路径如下：");
		    		Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
		    		break;
		    	}
		    }
	    }
        System.out.print("\n");
	}

	@Test
	public void test7() {
	    G.TwoPointsGetShortPath("in", "in");
	    if (G.NodeShortPath.size() > 0) {
	    	int Flag = 0;
		    for (int i = 0; i < G.NodeShortPath.size(); i++) {
		    	if (G.NodeShortPath.get(i).Costs > 0) {
		        	System.out.println("\"in\" to \"" + G.NodeShortPath.get(i).End + "\" 权值为：" +
		        			G.NodeShortPath.get(i).Costs + " 路径如下：");
		        	Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
	    		    System.out.print("\n");
	        	    Flag++;
	        	}
	        }
		    if (Flag == 0)
		    	System.out.println("\"the\" 和图中所有点都不可达\n");
        }
	    else
	    	System.out.println("\"the\" 不在图中\n");
	}

	@Test
	public void test8() {
	    G.TwoPointsGetShortPath("Study", "Study");
	    if (G.NodeShortPath.size() > 0) {
	    	int Flag = 0;
		    for (int i = 0; i < G.NodeShortPath.size(); i++) {
		    	if (G.NodeShortPath.get(i).Costs > 0)
	        	{
		        	System.out.println("Study1 to " + G.NodeShortPath.get(i).End + " 权值为：" + G.NodeShortPath.get(i).Costs);
	    		    for (int k = 0; k < G.NodeShortPath.get(i).P.size(); k++)
	    	            System.out.println(G.NodeShortPath.get(i).P.get(k));
		            System.out.print("\n");
		            Flag++;
	        	}
	        }
		    if (Flag == 0)
		    	System.out.println("\"Study\" 和图中所有点都不可达\n");
        }
	    else
	    	System.out.println("\"Study\" 不在图中\n");
     }

	@Test
	public void test9() {
	    G.TwoPointsGetShortPath("lucky", "lucky");

	    if (G.NodeShortPath.size() > 0)
        {
	    	int Flag = 0;
		    for (int i = 0; i < G.NodeShortPath.size(); i++)
	        {
		    	if (G.NodeShortPath.get(i).Costs > 0)
	        	{
		    		System.out.println("lucky to " + G.NodeShortPath.get(i).End + " 权值为：" + G.NodeShortPath.get(i).Costs);
		    		Vector <PQueue> S = G.NodeShortPath;
	    		    for (int k = 0; k < S.get(i).P.size(); k++)
	    	            System.out.println(S.get(i).P.get(k));
		            System.out.print("\n");
		            Flag++;
	        	}

	        }
		    if (Flag == 0)
		    	System.out.println("\"lucky\" 和图中所有点都不可达\n");
        }
	    else
	    	System.out.println("\"lucky\" 不在图中\n");
	}

}
