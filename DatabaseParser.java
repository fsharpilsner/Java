/*
 * DatabaseParser.java
 *
 * Created on den 21 juni 2007, 11:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Kaveh Piroozram
 */

import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import java.util.*;


public class DatabaseParser
{
    public static HashSet<String> Buffers = new HashSet();
    
    /** Creates a new instance of DatabaseParser */
    public DatabaseParser ()
    {
      ///////////////////////// Parse XML and filter first level ////////////////////////////      
    try {
      XMLReader parser = XMLReaderFactory.createXMLReader();
      PrintWriter raw_out = new PrintWriter(new BufferedWriter(new FileWriter("raw_db.txt")));
      ContentHandler handler = new TextExtractor(raw_out);
      parser.setContentHandler(handler);
     //new TextExtractor(out).printContext();
      parser.parse("4.xml");
      raw_out.close();
      
      BufferedReader raw_in = new BufferedReader(new FileReader("raw_db.txt"));
      PrintWriter filtered_out = new PrintWriter(new BufferedWriter(new FileWriter("filtered_db.txt")));
            
            String line;
            
            while ((line=raw_in.readLine())!=null ){
                
                lineCleaner(line);
                //System.out.println(line);
                //out.write(line);
            }
            Iterator hsi= Buffers.iterator();
            while (hsi.hasNext())
            {
                //Object O = hsi.next();
                filtered_out.write(" ");
                filtered_out.write(hsi.next().toString());
            }     
     
            raw_in.close ();
            filtered_out.close ();
    }
    catch (Exception e) {
      System.err.println(e); 
    }
    /////////////////////////////////////////////////////////////////////////////////
    
    
    ////////////////////////// filter second level //////////////////////////////
     try
       { 
            BufferedReader filtered_in = new BufferedReader(new FileReader("filtered_db.txt"));
            PrintWriter filtered_out2 = new PrintWriter(new BufferedWriter(new FileWriter("filtered_db2.txt")));
            int r;
            //The purpose here is to remove all remaining <u> </u> <b> </b> tags
            while ( (r = filtered_in.read()) != -1 )
            {
                if ((char)r=='/' || (char)r=='<' || (char)r=='>' 
                         || (char)r==':'  || (char)r=='…'
                        || (char)r==';' || (char)r=='!'
                        || (char)r=='\'' || (char)r=='"' 
                        || (char)r=='*' || (char)r=='/' || r==7 || 
                        r==8|| r==9 || r==10 || r==13 || r==32 || (r>=128&&r<=159) || r==255)
                    r=' ';
                
                filtered_out2.write(r);
             }
       filtered_in.close();
       filtered_out2.close();
      
     }catch(IOException ioe) {} 
    /////////////////////////////////////////////////////////////////////////////////
  
    ///////////////////////FILTER THIRD LEVEL//////////////////////
    
    try {
      
       BufferedReader filter2_in = new BufferedReader(new FileReader("filtered_db2.txt"));
       PrintWriter filter3_out = new PrintWriter(new BufferedWriter(new FileWriter("filtered_db3.txt")));

            String line;
            
            while ( (line=filter2_in.readLine()) !=null )
            {
                 line= line.replaceAll("\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line= line.replaceAll("\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s\\s", " ");
                 line=line.trim ();
                 StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) {
                        String s= st.nextToken();
                        s=s.replaceAll(">", " ");
                        s=s.replaceAll("\\s", "");
                        
                        if (s.equals ("b"))
                            s="";
                        if (s.equals ("br"))
                            s="";
                        if (s.equals ("u"))
                            s="";
                        if (s.equals ("i"))
                            s="";
                        if (s.equals ("  "))
                            s=" ";
                        if (s.equals ("   "))
                            s=" ";
                        if (s.equals ("    "))
                            s=" ";
                        if (s.equals ("     "))
                            s=" ";
                        if (s.equals ("      "))
                            s=" ";
                        if (s.equals ("       "))
                            s=" ";
                        if (s.equals ("        "))
                            s=" ";
                        if (s.equals ("         "))
                            s=" ";
                        if (s.equals ("          "))
                            s=" ";
                        if (s.equals ("           "))
                            s=" ";
                        if (s.equals ("            "))
                            s=" ";
                        if (s.contains ("="))
                            s="";
                        if (s.contains ("#"))
                            s="";
                        if (s.contains ("f("))
                            s="";
                        if (s.contains ("+"))
                            s="";
                        if (s.contains ("()"))
                            s="";
                        if (s.contains ("||"))
                            s="";
                        if (s.contains ("&nbsp"))
                            s="";
                        /*
                         *this will cover certain rare sentences which are not covered.
                        if (s.contains ("&lt;/b&gt;"))
                            s="";
                         */                       
                        filter3_out.write(s + " ");
                        
                    }
            }
       
       filter2_in.close();
       filter3_out.close();
  
  }catch(IOException ioe)  {}
    
   //////////////////////////////////////////////////////////////////////////
    
  }//Main()-Constructor
  
  
  
  ////////////////////////////////////////////////////////////
      public static void lineCleaner(String s)
    {
        
                
        char [] seq = new char[s.length()];
        boolean special_line=false;
        
        /*
        if( s.contains("^f(") ) {
              s="";
              special_line=true;
        }
        */
        
        //copy String to Array
        for (int i=0; i<s.length(); i++) 
        {
            seq[i]=s.charAt(i);
            
        }
        
        //now look for tags
        for(int i=0; i<seq.length; i++)
        {
            //<b>
            if ( seq[i]=='<' && seq[i+1]=='b' && seq[i+1]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    special_line=true;
            }
            //<u>
            if ( seq[i]=='<' && seq[i+1]=='u' && seq[i+1]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    special_line=true;
            }
            //<i>
            if ( seq[i]=='<' && seq[i+1]=='i' && seq[i+1]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    special_line=true;
            }
            //<B>
            if ( seq[i]=='<' && seq[i+1]=='B' && seq[i+1]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    special_line=true;
            }
            //<br>
            if ( seq[i]=='<' && seq[i+1]=='b' && seq[i+2]=='r' && seq[i+3]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    seq[i+3]=(char)' ';
                    special_line=true;
            }
            //<bR>
            if ( seq[i]=='<' && seq[i+1]=='b' && seq[i+2]=='R' && seq[i+3]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    seq[i+3]=(char)' ';
                    special_line=true;
            }
            //<Br>
             if ( seq[i]=='<' && seq[i+1]=='B' && seq[i+2]=='r' && seq[i+3]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    seq[i+3]=(char)' ';
                    special_line=true;
            }
            //<BR>
            if ( seq[i]=='<' && seq[i+1]=='B' && seq[i+2]=='R' && seq[i+3]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    seq[i+3]=(char)' ';
                    special_line=true;
            }
           //</b>
            if ( seq[i]=='<' && seq[i+1]=='/' && seq[i+2]=='b' && seq[i+3]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    seq[i+3]=(char)' ';
                    special_line=true;
            }
           //</B>
            if ( seq[i]=='<' && seq[i+1]=='/' && seq[i+2]=='B' && seq[i+3]=='>')
            {
                    seq[i]=(char)' ';
                    seq[i+1]=(char)' ';
                    seq[i+2]=(char)' ';
                    seq[i+3]=(char)' ';
                    special_line=true;
            }
            
        }
        
    
        s= new String(seq); //copy the finished seq back to string.
        //if(special_line)
            Buffers.add(s);  //not just lines surrounded by <br> to cover everything. Inclusive answers too.
         
    }
  ////////////////////////////////////////////////////////////

}


    
    

