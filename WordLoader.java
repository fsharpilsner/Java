/*
 * WordLoader.java
 *
 * Created on den 20 juni 2007, 13:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Kaveh Piroozram
 */

import java.io.*;
import java.util.regex.*;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.HWPFDocument;

public class WordLoader
{
    /** Creates a new instance of WordLoader */
    public WordLoader ()
    {
            
        try 
        {
         PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("raw.txt")));
         FileInputStream f_is= new FileInputStream("1.doc");
         HWPFDocument hw= new HWPFDocument(f_is);  
         WordExtractor we = new WordExtractor(hw);
         String doc_s=we.getText();
         out.print (doc_s);
         //System.out.println(doc_s);
         out.close (); //done extracting raw text
         f_is.close ();
         
         //from here on the formating
         String line;
         boolean reached_screened=false;
         BufferedReader raw_in = new BufferedReader(new FileReader("raw.txt"));
         PrintWriter filtered_out = new PrintWriter(new BufferedWriter(new FileWriter("filtered_doc.txt")));
         String screener = "SCREENER";
         String END="\n:";
         String buff2;
         
         /************** STAGE 1 ****************
         * to clear out the first page and  
         * question options
         */
             
         while ( (line = raw_in.readLine()) !=null)
         {
           //------------------------------------
           //check to reach the screener
           if (line.contains(screener))
            reached_screened=true;
            //------------------------------------ 
               //passed. begin writing
                 if(reached_screened)
                 {
                     buff2=line;
                     buff2= checkForString(buff2);
                     
                     filtered_out.write(buff2);
                     filtered_out.write ('\n');
                 }
              }
             raw_in.close();
             filtered_out.close();
             /*
              * doc_special_chars_removed.txt is for checking for missed/unique strings
              * filtered_doc.txt is for extracting unique sentences
              *
              */
             
            BufferedReader Docin = new BufferedReader(new FileReader("filtered_doc.txt"));
            PrintWriter Docout = new PrintWriter(new BufferedWriter(new FileWriter("doc_special_chars_removed.txt")));
            int r;
            while ( (r = Docin.read()) != -1 )
            {
                if ((char)r=='/' || (char)r=='<' || (char)r=='>' 
                        || (char)r==':'  || (char)r=='…'
                        || (char)r==';' || (char)r=='!'
                        || (char)r=='\'' || (char)r=='"' 
                        || (char)r=='*' || (char)r=='/' || r==7 || (char)r=='=' ||
                        r==8|| r==9 || r==10 || r==13 || r==32 || (r>=128&&r<=159) || r==255)
                    r=(char)' ';
                
                Docout.write(r);
                //System.out.println (r);
             }
            Docin.close();
            Docout.close();
      }catch(IOException ioe){}
        
    }//Main()-Constructor
  
    //////////////////////////////////////////////////
    public static String checkForString(String buff2)
    {
                        ///////////////
                     if(buff2.contains("PN")) //nr1
                         buff2="";
                     if (buff2.contains("COUNTRY")) //nr2
                        buff2="";
                     if (buff2.contains("SINGLE CODE")) //nr2
                        buff2="";
                     if (buff2.contains("CHECK QUOTA")) //nr2
                        buff2="";
                     if (buff2.contains("SINGLE RESPONSE")) //nr2
                        buff2="";
                     if (buff2.contains("TERMINATE")) //nr2
                        buff2="";
                     if (buff2.contains("CODE")) //nr2
                        buff2="";
                     if (buff2.contains("INSERT NUMBER")) //nr2
                        buff2="";
                     if (buff2.contains("RESPONSE")) //nr2
                        buff2="";
                     if (buff2.contains("ANSWER")) //nr2
                        buff2="";
                     if (buff2.contains("MULTIPLE")) //nr2
                        buff2="";
                     if (buff2.contains("RANDOMISE")) //nr2
                        buff2="";
                     if (buff2.contains("GRID")) //nr2
                        buff2="";
                     if (buff2.contains("DECLARACIONES")) //nr2
                        buff2="";
                     if (buff2.contains("ALLOW")) //nr2
                        buff2="";
                     if (buff2.contains("BRAND")) //nr2
                        buff2="";
                     if (buff2.contains("EQUAL")) //nr2
                        buff2="";
                     if (buff2.contains("SCREEN")) //nr2
                        buff2="";
                     if (buff2.contains("PATIENT")) //nr2
                        buff2="";
                     if (buff2.contains("PRODUCTOS")) //nr2
                        buff2="";
                     if (buff2.contains("SHOW")) //nr2
                        buff2="";
                     if (buff2.contains("AWARE")) //nr2
                        buff2="";
                     if (buff2.contains("ASK")) //nr2
                        buff2="";
                     if (buff2.contains("ESCALA")) //nr2
                        buff2="";
                     if (buff2.contains("STATEMENTS")) //nr2
                        buff2="";
                     if (buff2.contains("ATRIBUTOS")) //nr2
                        buff2="";
                     if (buff2.contains("COMMUNICATION")) //nr2
                        buff2="";
                     if (buff2.contains("SECTION")) //nr2
                        buff2="";
                     if (buff2.contains("QUOTA")) //nr2
                        buff2="";
                     if (buff2.contains("TOTAL")) //nr2
                        buff2="";
                     if (buff2.contains("ADD")) //nr2
                        buff2="";
                     if (buff2.equals("Prompted")) //nr2
                        buff2 += " " ;
                     /*
                     if (buff2.contains("……")) //nr2
                        buff2="";
                      */
                     if (buff2.contains("_")) //nr2
                        buff2="";
                     if (buff2.contains("DEMOGRAPHICS")) //nr2
                        buff2="";
                     if (buff2.contains("SEE")) //nr2
                        buff2="";
                     if (buff2.contains("PACIENTE")) //nr2
                        buff2="";
                     /* either we should remove the parenthesis or compare string with
                      *string since they have parenthesis in the database as well 
                     if (buff2.contains("(")){
                         ;
                     }
                     */
                     
          return buff2;
    }
    //////////////////////////////////////////////////
    
}
