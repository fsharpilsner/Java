/*
 * NewClass.java
 *
 * Created on den 27 juni 2007, 10:42
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Kaveh Piroozram
 */
import java.io.*;
import java.util.*;

public class NewClass
{
    
    public static HashSet<String> diffBuffers = new HashSet();
    public static HashSet<String> wordBuffers = new HashSet();
    public static HashSet<String> results = new HashSet();
    
    /** Creates a new instance of NewClass */
    public NewClass ()
    {
        try 
        {
        BufferedReader word_file = new BufferedReader(new FileReader("filtered_doc2.txt"));
        BufferedReader diff_file = new BufferedReader(new FileReader("doc_diff.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        
        String line;
        //--------------------Read from file and store into Set-----
        while ((line=word_file.readLine ()) != null)
        {
          StringTokenizer st = new StringTokenizer(line, ".", true);
             while (st.hasMoreTokens()) 
             {  
                    String s= st.nextToken() + ".";
                    wordBuffers.add (s);
                    //System.out.println("-------> " + );
              }
         }
        
        word_file.close ();
        //--------------------Read from file and store into Set-----
        while ((line=diff_file.readLine ()) != null)
        {
          if (line.length ()>=2)
          {
              diffBuffers.add (line);
              
          }
        }
        diff_file.close ();
        //--------------------------------------------------------
        
        //--------------------------------------------------------
        /////////rearange Set to Array///////////////////// 
       Iterator diffi = diffBuffers.iterator();
       String [] diffStrings = new String[diffBuffers.size()];
       for(int j=0;  j<diffBuffers.size(); j++, diffi.hasNext ())
       {
           diffStrings[j]=diffi.next().toString();
           
       }
       /////////rearange Set to Array/////////////////////
       Iterator wordi = wordBuffers.iterator();
       String [] wordStrings = new String[wordBuffers.size()];
       for(int j=0;  j<wordBuffers.size(); j++, wordi.hasNext ())
       {
           wordStrings[j]=wordi.next().toString();
           
       }
       //-----Check to see which buffers from word contains diffs------------
       for(int z=0; z<wordStrings.length; z++)
       {
           for(int j=0; j<diffStrings.length; j++)
           {
               
               if (wordStrings[z].contains (diffStrings[j]) 
               && diffStrings[j]!="" && diffStrings[j]!=" " && diffStrings[j]!="  "
                       && diffStrings[j]!="    " && diffStrings[j]!="     ")
               {
                   results.add (wordStrings[z]);
                   //System.out.println(wordStrings[z] + "<<CONTAINED>>" +  diffStrings[j] +"\n---");
                   
               }
           }
       }   
       //--------------------------------------------------------
       
       
       Iterator resulti = results.iterator();
       while(resulti.hasNext ())
           out.println (resulti.next ());
       
           out.close ();
        }catch(IOException ioe) {}
        
       
       
    }
    
    public static void main(String args[])
    {
        NewClass nc = new NewClass();
        
    }
    
}
