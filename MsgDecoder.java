/*
Class MsgDecoder:
Get String from input Stream and reconstruct it to 
a Key Value List.
*/

class MsgDecoder {

private BufferedReader bufferIn;
private final String delimiter="$$$";

public MsgDecoder(InputStream in){
bufferIn = new BufferedReader(new InputStreamReader(in)); 
}

/*
get String and output KeyValueList
*/

public KeyValueList getMsg() throws IOException{
String strMsg= bufferIn.readLine();

if (strMsg==null) return null;

KeyValueList kvList = new KeyValueList(); 
StringTokenizer st = new StringTokenizer(strMsg,delimiter);
while (st.hasMoreTokens()) {
kvList.addPair(st.nextToken(),st.nextToken());
}
return kvList;
}

}