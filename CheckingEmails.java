import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.io.*;
import java.util.*;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CheckingEmails {

   public static ArrayList<String> fetch() {
	   String pop3host = "pop.gmail.com";// change accordingly
	      String storeType = "pop3";
	      String username = 
	         "group7vote@gmail.com";// change accordingly
	      String password = "DomAndBrandon";// change accordingly
	   ArrayList<String> x = new ArrayList<String>();
      try {
         // create properties field
         Properties properties = new Properties();
         properties.put("mail.store.protocol", "pop3");
         properties.put("mail.pop3.host", pop3host);
         properties.put("mail.pop3.port", "995");
         properties.put("mail.pop3.starttls.enable", "true");
         Session emailSession = Session.getDefaultInstance(properties);
         // emailSession.setDebug(true);

         // create the POP3 store object and connect with the pop server
         Store store = emailSession.getStore("pop3s");

         store.connect(pop3host, username, password);

         // create the folder object and open it
         Folder emailFolder = store.getFolder("INBOX");
         emailFolder.open(Folder.READ_ONLY);

         BufferedReader reader = new BufferedReader(new InputStreamReader(
	      System.in));
         BufferedReader reader2 = new BufferedReader(new InputStreamReader(
       	      System.in));

         // retrieve the messages from the folder in an array and print it
         Message[] messages = emailFolder.getMessages();
         //System.out.println("messages.length---" + messages.length);
         String subject = "";
         String sender = "";
         
         for (int i = messages.length-1; i >= 0; i--) {
        	 //System.out.println("begining");
        	 Message message = messages[i];
        	 //writeEnvelope(message);
        	 subject = getSubject(message);
        	 sender = getSender(message);
        	 if(!subject.equals("") && !sender.equals("") && !x.contains(sender)){
        		 x.add(subject);
        		 x.add(sender);
        		 }
        	//System.out.println("end");
            //System.out.println(i);
         }

         // close the store and folder objects
         emailFolder.close(false);
         store.close();
         //System.out.println(x);
         return x;

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } //catch (IOException e) {
        // e.printStackTrace();      }
      catch (Exception e) {
         e.printStackTrace();
      }
      return x;
   }
   
      
   public static void main(String[] args) {

      String host = "pop.gmail.com";// change accordingly
      String mailStoreType = "pop3";
      String username = 
         "group7vote@gmail.com";// change accordingly
      String password = "DomAndBrandon";// change accordingly
      
      //Call method fetch
      ArrayList<String> x = fetch();
      
      //System.out.println(x);

   }

     
   /*
   * This method would print FROM,TO and SUBJECT of the message
   */
   public static void writeEnvelope(Message m) throws Exception {
      System.out.println("This is the message envelope");
      System.out.println("---------------------------");
      Address[] a;

      // FROM
      if ((a = m.getFrom()) != null) {
         for (int j = 0; j < a.length; j++)
         System.out.println("FROM: " + a[j].toString());
      }

      // TO
      if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
         for (int j = 0; j < a.length; j++)
         System.out.println("TO: " + a[j].toString());
      }

      // SUBJECT
      if (m.getSubject() != null)
         System.out.println("SUBJECT: " + m.getSubject());

   }
   
   public static String getSender(Message m) throws Exception {
	   Address[] a;
	   
	   if ((a = m.getFrom()) != null) {
	         return a[0].toString();
	   }
	   else{return "";}
   
   }
   
   public static String getSubject(Message m) throws Exception {
	   Address[] a;
	   if (m.getSubject() != null){
		   return m.getSubject();
	   }
	   else{return "";}
   
   }

}