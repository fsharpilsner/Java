/*
 * Unique_Similar.java
 *
 * Created on den 21 juni 2007, 14:40
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

public class Unique_Similar
{
 
    public static HashSet<String> dbBuffers = new HashSet();
    public static HashSet<String> docBuffers = new HashSet();
    public static String line;
 
    /** Creates a new instance of Unique_Similar */
    public Unique_Similar ()
    {
     
     try
       { 
          //-------------------------------------------------------------------
            
            BufferedReader in_db_no_space = new BufferedReader(new FileReader("filtered_db3.txt"));
            BufferedReader in_doc = new BufferedReader(new FileReader("doc_special_chars_removed.txt"));
             
             
            while ( (line=in_db_no_space.readLine()) !=null )
            {
                 
                 StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) 
                    {
                     String s = st.nextToken();
                     dbBuffers.add(s);
                     //System.out.println(st.nextToken());
                    }
            }
             while ( (line=in_doc.readLine()) !=null )
            {
                 
                 StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) {
                     String s = st.nextToken();
                     docBuffers.add(s);
                     //System.out.println(st.nextToken());
                    }
            }
            //-------------------------------------------------------------------
       in_db_no_space.close();
       in_doc.close();
            
            
       /////////rearange Set to Array/////////////////////
       Iterator doci = docBuffers.iterator();
       String [] docStrings = new String[docBuffers.size()];
       for(int j=0;  j<docBuffers.size(); j++, doci.hasNext ())
       {
           docStrings[j]=doci.next().toString();
           
       }
       /////////rearange Set to Array/////////////////////
       Iterator dbi = dbBuffers.iterator();
       String [] dbStrings = new String[dbBuffers.size()];
       for(int j=0;  j<dbBuffers.size(); j++, dbi.hasNext ())
       {
           dbStrings[j]=dbi.next().toString();
           
       }
       System.out.println (dbBuffers.size());
       
       //////////////////////////////////////////////////
       //for each word in the doc, compare it to DB. remove those in common
       PrintWriter similar_file = new PrintWriter(new BufferedWriter(new FileWriter("similars.txt")));
       for(int z=0; z<docStrings.length; z++)
       {
           for(int j=0; j<dbStrings.length; j++)
           {
               if (docStrings[z].equals (" "))
                    docStrings[z]="";
               if (dbStrings[z].equals (" "))
                    dbStrings[z]="";
     
                    
               if (docStrings[z].equals (dbStrings[j]))
               {
                   docBuffers.remove (docStrings[z]); //change the objects
                   dbBuffers.remove (dbStrings[j]); //change the objects
                   //System.out.println(docStrings[i]);
                   docStrings[z]=""; //remove commons from doc.
                   dbStrings[j]="";//remove commons from db
               }
               if ( similar(docStrings[z], dbStrings[j]) && docStrings[z]!="" && dbStrings[z]!="" && docStrings[z]!=" " && dbStrings[z]!=" ")
                   //System.out.println(docStrings[z] + " was similar to " +  dbStrings[j]);
                   similar_file.print (docStrings[z] + " was similar to " +  dbStrings[j]);
           }
       }
       similar_file.close ();
      //////////////////////////////////// 
      PrintWriter db_diff = new PrintWriter(new BufferedWriter(new FileWriter("db_diff.txt")));
      PrintWriter doc_diff = new PrintWriter(new BufferedWriter(new FileWriter("doc_diff.txt")));
       
       for(int z=0; z<docStrings.length; z++)
           doc_diff.write (docStrings[z] + " ");
           //System.out.println(docStrings[z]); //The difference
        
       System.out.println("------------");
       
        for(int z=0; z<dbStrings.length; z++)
           db_diff.write (dbStrings[z] + " ");
      
         doc_diff.close ();
         db_diff.close();
      ////////////////////////////////////  
         
  
         
       }catch(IOException ioe){} 
  
}//Main()-Constructor   
    
////////////////////////////////////////////////////////////        
 //---------------------------------------------------------   
  public static boolean isJunk(int i )
 {
     if(i==10 || i==13 || i== 172 ||  i== 17 )
         return true ;
     if( i>=0 && i<=8 )
         return true ;
     if( i>=10 && i<=31 )
         return true ;
     if( i==94 || i==95 || i==96 )
         return true ;
     if(i>=126 && i<=255)
         return true ;

     return false;
 }
  
  //---------------------------------------------------------
  ////////////////////////////////////////////////////////////    
   public static boolean similar(String s1, String s2)
    {
        int chance=0;
        String choice, smaller;
        int j=0, i=0;
        int l1 = s1.length ();
        int l2 = s2.length ();
        
        if (l1>l2){
            choice = s1;
            smaller= s2;
        }
            
        else{
            choice = s2;
            smaller = s1;
        }
        try {
        for(i=0; i< choice.length (); i++)
            for(j=0; j< smaller.length (); j++)
                if (choice.charAt (i)==smaller.charAt (j))
                    chance++;
        }catch(StringIndexOutOfBoundsException siobe) {}
        
        //System.out.println ("chance="+chance + "j*i=" +i*j);
        if (chance>= i*j*0.20   &&(choice.length () - smaller.length () <=2) )
            return true;
        
        if (choice.length ()<=2 || smaller.length ()<=2)
        return false;
        
        return false;
    }
 //---------------------------------------------------------
  ////////////////////////////////////////////////////////////    
  
}
