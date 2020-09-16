package src.DNACryptography;
import java.math.BigInteger;


public class DataConverter {
	
	
	String StringToASCII(String strString){		
        StringBuilder ascii = new StringBuilder();
        
        for (int i=0; i < strString.length(); i++) {
        	// for unicode append for each character
        	ascii.append("\\u0");
        	ascii.append(Integer.toHexString(strString.charAt(i)));            
        }       
        return ascii.toString();	
	}
	
	 
	 String asciiToHex(String ascii){
        StringBuilder hex = new StringBuilder();
        
        for (int i=0; i < ascii.length(); i++) {
            hex.append(Integer.toHexString(ascii.charAt(i)));
            hex.append("");
        }       
        return hex.toString();
    } 
	
	
	String HexToBinary(String Hex) {
		String Binary="";
		for(int j=0;j<Hex.length();j++){
	    int i = Integer.parseInt(Hex.charAt(j)+"", 16);
	    String Bin = Integer.toBinaryString(i);
		//set binary into 4 digit
	   for(int k=Bin.length();k<4;k++){
		   
		   Bin ="0"+Bin;		   
	   }
		
		Binary +=Bin;
		}
	    return Binary;
	}
	
	String BinaryToDNADigitalCode(String Binary){
		String DNADigitalCode="";
		for(int i=0;i<Binary.length();i=i+2){
			String strDNA=Binary.substring(i,i+2);
			if(strDNA.equals("00")){
				DNADigitalCode +="A";
			}
			else if(strDNA.equals("01")){
				DNADigitalCode +="T";
			}
			else if(strDNA.equals("10")){
				DNADigitalCode +="G";
			}
			else{
				DNADigitalCode +="C";
			}
		}
		
		return DNADigitalCode;
	}

	
	String DNADigitalCodeTOBinary(String DNADigitalCode){
		String Binary="";
		for(int i=0;i<DNADigitalCode.length();i++){
		
			if(DNADigitalCode.charAt(i)=='A'){
				Binary +="00";
			}
			else if(DNADigitalCode.charAt(i)=='T'){
				Binary +="01";
			}
			else if(DNADigitalCode.charAt(i)=='G'){
				Binary +="10";
			}
			else{
				Binary +="11";
			}
		}
		
		return Binary;
	}

	
	String BinaryToHex(String Binary){
		String Hex="";
		
		Hex=new BigInteger(Binary, 2).toString(16).toUpperCase();
			  
		return Hex;
	}
	
	
	public String HexToAscii(String hex){
		
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	 
		  //49204c6f7665204a617661 split into two characters 49, 20, 4c...
		  for( int i=0; i<hex.length()-1; i+=2 ){
	 
		      //grab the hex in pairs
		      String output = hex.substring(i, (i + 2));
		      //convert hex to decimal
		      int decimal = Integer.parseInt(output, 16);
		      //convert the decimal to character
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }
		
	 
		  return sb.toString();
	  }
	

	public String AsciiToString(String hex){
		
		  StringBuilder sb = new StringBuilder();
		  StringBuilder temp = new StringBuilder();
	    
		  hex=convertAscii(hex);
		  
		  String[] strhex=hex.split(" ");
	
		  for( int i=0; i<strhex.length; i++ ){
	       
		     
		      String output = strhex[i];		      
		      if(output.equals(""))continue;
		      int decimal = Integer.parseInt(output, 16);
		   
		      sb.append((char)decimal);
	 
		      temp.append(decimal);
		  }
	
	 
		  return sb.toString();
	  }
	
	String convertAscii(String Ascii){
		String strConverted="";
		//used to remove appended '\\u0' for each character
		  for(int i=0;i<Ascii.length()-2;i++){
			  if(Ascii.charAt(i)=='\\' && Ascii.charAt(i+1)=='u' && Ascii.charAt(i+2)=='0'){
				 i=i+2;
				 if(i!=0){
			    strConverted +=" ";}
			  }
			  else{
				  strConverted +=Ascii.charAt(i);
			  }
		  }
		  strConverted +=Ascii.substring(Ascii.length()-2);
		//  System.out.println(strConverted);
		
		return strConverted;
	}
	
}
