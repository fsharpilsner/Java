/*
 *This program generate the context. 
 *one (for now) have to catch all text surrounded by <br><br>... <br><br>
 *and remove all <b></b> <...></..> tags in the remaining so to gather 
 *the questions. look at foo or foo.withoutText.
 *The answers are kind of free. They should be catched by a similarity function
 *
 *THIS IS FOR EXTRACTING USEFULL CONTENTS OUT OF XML FILE
 *
 *
 */
 
 /**
 *
 * @author Kaveh Piroozram
 */


//package javaapplication12;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;


public class TextExtractor implements ContentHandler {

  private PrintWriter out;
  public String localName, context;
  
//--------------------------------------------------  
  public TextExtractor(PrintWriter out) {
    this.out = out;   
  }
//--------------------------------------------------    
  public void characters(char[] text, int start, int length)
   throws SAXException {
     
    //try {
      if ("Text".equalsIgnoreCase(localName))
          
      {
            context=new String (text, start, length); //this is the content between tags
            out.write("" + context);
            //out.write("" + localName); //localName is null when Text is print out
      }
   /*   
    }
    catch (IOException e) {
      throw new SAXException(e);   
    }*/
    
  }
//--------------------------------------------------  
  
  public void startDocument ()
    {
	//System.out.println("Start document");
    }
//--------------------------------------------------
    public void endDocument ()
    {
	//System.out.println("End document");
        printContext();
    }
//--------------------------------------------------
  public void startElement(String namespaceURI, String localName, 
          String qualifiedName, Attributes atts) 
  { 
    //System.out.println("" + qualifiedName); //qualified name is the name of <Tags>
    if (localName.equals("Text"))  //local name of startElement
    {
      this.localName=  localName;
      //System.out.println(">>>>>>>>>> start Text");
    }
    
  } 
//--------------------------------------------------
  public void endElement(String namespaceURI, String localName,String qualifiedName) {
  //System.out.println("" + qualifiedName);
    if (qualifiedName.equals("Text"))  //qualified name of endElement
      System.out.println("");
    }
//--------------------------------------------------  
  public void printContext()
  {
      
     // System.out.println("was--->"+context);
  }
  
  
  //-------------------------------------------------------------------------
  // do-nothing methods
  public void setDocumentLocator(Locator locator) {}
  //public void startDocument() {}
  //public void endDocument() {}
  public void startPrefixMapping(String prefix, String uri) {}
  public void endPrefixMapping(String prefix) {}
  //public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes atts) {}
  //public void endElement(String namespaceURI, String localName,String qualifiedName) {}
  public void ignorableWhitespace(char[] text, int start, 
   int length) throws SAXException {}
  public void processingInstruction(String target, String data){}
  public void skippedEntity(String name) {}
//-------------------------------------------------------------------------
} // end TextExtractor


