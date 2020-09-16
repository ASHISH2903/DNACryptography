import java.io.File;

import java.io.FileNotFoundException;

import java.io.PrintWriter;

import java.util.Scanner;

public class DNA {

	public static void main(String[] args) {
		String DNA = "";
		String Primer1 = " ";
		String Primer2 = " ";
		String strTask = "";
		try {
			// get data from file.
			File file = new File("DNA_DATA.txt");
			Scanner sc = new Scanner(file);
			String strinput = sc.nextLine();
			String[] myString = strinput.split(" ");
			DNA = myString[1];
			System.out.println("first element :" + DNA.charAt(0));
		//	Primer1 = myString[2];
		//	Primer2 = myString[3];
			strTask = myString[2];
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("exception in getting data");
			System.exit(0);
		}

		// pcrAmplifier object used to perform PCR amplification
		Amplifier pcrAmplifier = new Amplifier(Primer1, Primer2);

		// dConverter object is used to convert data into different format.
		DataConverter dConverter = new DataConverter();

		// For Encoding
		if (strTask.equals("E")) {
			System.out.println("Unicode :" + DNA);
			// convert string into ascii
			String strAscii = dConverter.StringToASCII(DNA);
			System.out.println("Ascii :" + strAscii);

			// convert ascii into Hexadecimal value
			String Hex = dConverter.asciiToHex(strAscii);
			System.out.println("Hexadecimal value :" + Hex);
			// convert hexadecimal value to binary value
			String Binary = dConverter.HexToBinary(Hex);
			System.out.println("Binary Value :" + Binary);
			// convert binary value to DNADigitalcode
			String DNACode = dConverter.BinaryToDNADigitalCode(Binary);
			System.out.println("DNA Digital coding: \n" + DNACode);
			//System.out.println("Primer1 :" + Primer1);
			//System.out.println("Primer2 :" + Primer2);

			// use pcr amplification method to encode data.
			String strEncoded = pcrAmplifier.encode(DNACode);

			System.out.println("Amplified Message:\n" + strEncoded);

			// print output into output.txt file
			File output = new File("outputEncoded.txt");
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(output);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("file not found exception");
				System.exit(0);
			}
			pw.write(strEncoded);
			pw.close();

			System.out.println("Now message is ready to send.");
		} // for Decoding
		else if (strTask.equals("D")) {
			System.out.println("DATA :" + DNA);
			//System.out.println("Primer1 :" + Primer1);
			//System.out.println("Primer2 :" + Primer2);
			// use pcr amplification method to decode data into DNA Digital code
			String DNACode = pcrAmplifier.decode(DNA.trim());

			System.out.println("DNA Digital coding: \n" + DNACode);
			// convert DNA Digital code to binary value
			String Binary = dConverter.DNADigitalCodeTOBinary(DNACode);

			System.out.println("Binary Value :" + Binary);

			// convert binary value to hexadecimal value
			String Hex = dConverter.BinaryToHex(Binary);

			System.out.println("Hexadecimal value :" + Hex);
			// convert hexadecimal value to ascii
			String strAscii = dConverter.HexToAscii(Hex);
			System.out.println("Ascii :" + strAscii);
			// convert ascii to string
			DNA = dConverter.AsciiToString(strAscii);
			System.out.println("Unicode :" + DNA);

			// print output into output.txt file
			File output = new File("outputDecoded.txt");
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(output);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
				System.out.println("file not found exception");
				System.exit(0);
			}
			pw.write(DNA);
			pw.close();

		}
	}

}
