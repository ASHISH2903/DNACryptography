import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

// PCRAmplifier class used to encode and decode data.
public class Amplifier {

	
	String strPrimerPair="";
	Map<String,String> map = new HashMap<String, String>();
	Amplifier()  {
		map.put("AA","0001");
		map.put("AT","0010");
		map.put("AG","0011");
		map.put("AC","0010");
		
		map.put("TA","0101");
		map.put("TT","0110");
		map.put("TG","0111");
		map.put("TC","1000");
		
		map.put("GA","1001");
		map.put("GT","1010");
		map.put("GG","1011");
		map.put("GC","1100");
		
		map.put("CA","1101");
		map.put("CT","1110");
		map.put("CG","0000");
		map.put("CC","1111");
		
	}
	Amplifier(String Primer1,String Primer2){
		// make primer pair from primer1 and primer2
		this();
		strPrimerPair=Primer1+Primer2+Primer1;
	}
	

	String encode(String DNACode){
		System.out.println("A:00 "+"" + "T:01 "+ "" + "G:10 "+ "" + "C=11");
		System.out.println("AA:0001 BINARY:01 "+"" + "AT:0010 BINARY:02 "+ "" + "AG:0011 BINARY:03"+ "" + "AC=0010 BINARY:04 " + "TA:0101 BINARY:05 "+"" + "TT:0110 BINARY:06 "+ "" + "TG:0111 BINARY:07 "+ "" + "TC=1000 BINARY:08" + "GA:1001 BINARY:09 "+"" + "GT:1010 BINARY:10 "+ "" + "GG:1011 BINARY=11 "+ "" + "GC=1100 BINARY:12 " + "CA:1101 BINARY:13 "+"" + "CT:1110 BINARY:13 "+ "" + "CG:0000 BINARY=00 "+ "" + "CC=1111 BINARY:15");
		String strEncoded="";
		if(DNACode.length() %2 != 0)  {
			DNACode = DNACode + "c";
		}
	     for(int i=0;i<DNACode.length();){
	    	
	    	 String tmp = ""+DNACode.charAt(i++) +  DNACode.charAt(i++);
	    	 System.out.print("The key value is="+tmp+ "  ");
	    	 strEncoded += map.get(tmp);
	    	 if(i%5==0)
	    		 System.out.print("\n");
	}
		return strEncoded;
}
	
	String decode(String Encoded){
		System.out.println("A:00 "+"" + "T:01 "+ "" + "G:10 "+ "" + "C=11");
		System.out.println("AA:0001 BINARY:01 "+"" + "AT:0010 BINARY:02 "+ "" + "AG:0011 BINARY:03 "+ "" + "AC=0010 BINARY:04 " + "TA:0101 BINARY:05 "+"" + "TT:0110 BINARY:06 "+ "" + "TG:0111 BINARY:07 "+ "" + "TC=1000 BINARY:08 " + "GA:1001 BINARY:09 "+"" + "GT:1010 BINARY:10 "+ "" + "GG:1011 BINARY=11 "+ "" + "GC=1100 BINARY:12 " + "CA:1101 BINARY:13 "+"" + "CT:1110 BINARY:13 "+ "" + "CG:0000 BINARY=00 "+ "" + "CC=1111 BINARY:15 ");
		Encoded=Encoded.trim();
		String strDecoded="";
	     for(int i=0;i<Encoded.length();i=i+4){
	    	String strTemp=Encoded.substring(i,i+4);
	    	String key = getKeysByValue(map,strTemp);
	    	strDecoded += key;
	    	System.out.print("The key value is=" +key + "   " );
	    	if(i%5==0)
	    		 System.out.print("\n");
	    	//check for primer pair , if not found correct primer pair for each character then terminate program.
	    	
	    	
	    	/*if(strTemp.substring(1).equals(strPrimerPair)){
	    	    strDecoded += strTemp.charAt(0);
	    	}
	    	else{
	    		System.out.println("Primer Pair is incorrect");
	    		System.exit(0);
	    	}*/
	    }
		return strDecoded;
	}
	public static String getKeysByValue(Map<String, String> map, String value) {
	    Set<String> keys = new HashSet<String>();
	    for (Entry<String, String> entry : map.entrySet()) {
	        if (value.equals(entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    return (String)keys.toArray()[0];
	}
	
}
