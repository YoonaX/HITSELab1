package lab1;

import java.util.Vector;


import org.junit.Before;
import org.junit.Test;

public class GetBridgeWordsTest {
	Digraph G = new Digraph();
	String File = "C:/Users/weibowen/Desktop/Lab.txt";
	String Str = "";
	String S = "";

	@Before
	public void setup() throws Exception {
		G.ReadFileBuildDigraph(File);
	}

	@Test
	public void test1()
	{
        Str = G.GetBridgeWords("the", "of");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1) {
            	S +=  "the bridge words from \"the\" to \"of\" is :";
            }
            else {
            	S += "the bridge words from \"the\" to \"of\" are :";
            }
        	for (int I = 0; I < BridgeWords.size(); I++) {
        		S += (BridgeWords.get(I) + "  ");
        	}
        	System.out.println(S);
		}
        else {
        	System.out.println(Str);
        }
        System.out.print("\n");
	}

	@Test
	public void test2() {
		Str = G.GetBridgeWords("big", "time");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1)
            	S +=  "the bridge words from \"big\" to \"time\" is :";
            else
            	S += "the bridge words from \"big\" to \"time\" are :";
        	for (int I = 0; I < BridgeWords.size(); I++)
        		S += (BridgeWords.get(I) + "  ");
        	System.out.println(S);
		}
        else
        	System.out.println(Str);
        System.out.print("\n");
	}

	@Test
	public void test3() {
		Str = G.GetBridgeWords("time", "big");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1)
            	S +=  "the bridge words from \"time\" to \"big\" is :";
            else
            	S += "the bridge words from \"time\" to \"big\" are :";
        	for (int I = 0; I < BridgeWords.size(); I++)
        		S += (BridgeWords.get(I) + "  ");
        	System.out.println(S);
		}
        else
        	System.out.println(Str);
        System.out.print("\n");
	}

	@Test
	public void test4() {
		Str = G.GetBridgeWords("time", "time");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1)
            	S +=  "the bridge words from \"time\" to \"time\" is :";
            else
            	S += "the bridge words from \"time\" to \"time\" are :";
        	for (int I = 0; I < BridgeWords.size(); I++)
        		S += (BridgeWords.get(I) + "  ");
        	System.out.println(S);
		}
        else
        	System.out.println(Str);
        System.out.print("\n");
	}

	@Test
	public void test5() {
		Str = G.GetBridgeWords("in", "time1");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1)
            	S +=  "the bridge words from \"in\" to \"time1\" is :";
            else
            	S += "the bridge words from \"in\" to \"time1\" are :";
        	for (int I = 0; I < BridgeWords.size(); I++)
        		S += (BridgeWords.get(I) + "  ");
        	System.out.println(S);
		}
        else
        	System.out.println(Str);
        System.out.print("\n");
	}

	@Test
	public void test6() {
		Str = G.GetBridgeWords("in1", "time");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1)
            	S +=  "the bridge words from \"in1\" to \"time\" is :";
            else
            	S += "the bridge words from \"in1\" to \"time\" are :";
        	for (int I = 0; I < BridgeWords.size(); I++)
        		S += (BridgeWords.get(I) + "  ");
        	System.out.println(S);
		}
        else
        	System.out.println(Str);
        System.out.print("\n");
	}

	@Test
	public void test7() {
		Str = G.GetBridgeWords("abc", "time1");
        Vector<String> BridgeWords = G.BridgeWords;
        if (BridgeWords.size() >= 1)
		{
            if (BridgeWords.size() == 1)
            	S +=  "the bridge words from \"abc\" to \"time1\" is :";
            else
            	S += "the bridge words from \"abc\" to \"time\" are :";
        	for (int I = 0; I < BridgeWords.size(); I++)
        		S += (BridgeWords.get(I) + "  ");
        	System.out.println(S);
		}
        else
        	System.out.println(Str);
        System.out.print("\n");
	}
}
