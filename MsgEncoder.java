
/*
Class MsgEncoder:
Serialize the KeyValue List and Send it out to a Stream.
*/
class MsgEncoder{
private PrintStream printOut;
/* Default of delimiter in system is $$$ */
private final String delimiter="$$$";

public MsgEncoder(){
}

/* Encode the Key Value List into a string and Send it out */

public void sendMsg(KeyValueList kvList, OutputStream out) throws IOException{
PrintStream printOut= new PrintStream(out);
if (kvList==null) return;
String outMsg= new String();
for(int i=0; i<kvList.size();i++){
if (outMsg.equals(""))
outMsg=kvList.keyAt(i)+delimiter + kvList.valueAt(i);
else
outMsg+=delimiter+kvList.keyAt(i)+delimiter + kvList.valueAt(i);
}
//System.out.println(outMsg);
printOut.println(outMsg);
}
}
