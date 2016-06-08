/*

Communication Interface for Virtual Classroom Components.
Complied in JDK 1.4.2
Usage:

Step1: Create your component which implemnts Interface 
ComponentBase.

Step2: Replace the ComponentMy with your class name in 
InterfaceServer::main().

Step3: start up Interface Server by
java InterfaceServer

Step4: Use Virtual remote which is also provided on the 
web to send message and get the feedback message.

*/ 


import java.io.*;
import java.net.*;
import java.util.*;

/* 
Class KeyValueList:
List of (Key, Value) pair--the basic format of message
Keys: MsgID and Description are required for any messages
MsgID 0-999 is reserved for system use.
You MsgID could start from 1000. 
*/

class KeyValueList{
private Vector Keys;
private Vector Values;

/* Constructor */
public KeyValueList(){
Keys=new Vector();
Values=new Vector();
}

/* Look up the value given key, used in getValue() */

private int lookupKey(String strKey){
for(int i=0;i<Keys.size();i++){
String k=(String) Keys.elementAt(i);
if (strKey.equals(k)) return i;
} 
return -1;
}

/* add new (key,value) pair to list */

public boolean addPair(String strKey,String strValue){
return (Keys.add(strKey)&& Values.add(strValue));
}

/* get the value given key */

public String getValue(String strKey){
int index=lookupKey(strKey);
if (index==-1) return null;
return (String) Values.elementAt(index);
} 

/* Show whole list */
public String toString(){
String result = new String();
for(int i=0;i<Keys.size();i++){
result+=(String) Keys.elementAt(i)+":"+(String) Values.elementAt(i)+"\n";
} 
return result;
}

public int size(){ return Keys.size(); }

/* get Key or Value by index */
public String keyAt(int index){ return (String) Keys.elementAt(index);}
public String valueAt(int index){ return (String) Values.elementAt(index);}
}
