import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.BitSet;

public class vbe_1 {

	public static void main(String[] args) {
		//int original = 31251336;
		int original=0;
		//FILE READ
		try {
		File file = new File("d:/input_original.txt");
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line = br.readLine()) != null){
			original = Integer.parseInt(line);
			System.out.println("Original file input="+original);
		}
		br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//FILE READ END
		
		
		int flag_orig_byte=0;
		BitSet bs = new BitSet();
		BitSet originalInBitSet = new BitSet();
		bs = BitSet.valueOf(new long[]{original});		
		String computedBinary = "";
		if(bs.length()<8)
		{
			flag_orig_byte=1;
			System.out.println("---1 byte---");
			//bs.set(7);
			computedBinary = computeBinary(bs);
			computedBinary = String.format("%0"+ 8 +"d", Integer.parseInt(computedBinary));
		}
		else if(bs.length()<15)
		{
			flag_orig_byte=2;
			System.out.println("---2 bytes---");			
			computedBinary = computeBinary(bs);
			//bs.set(7);
			computedBinary = String.format("%0"+ 16 +"d", Integer.parseInt(computedBinary));
		}
		else if(bs.length()<23)
		{
			flag_orig_byte=3;
			System.out.println("---3 bytes---");		
			computedBinary = computeBinary(bs);
			StringBuilder sb = new StringBuilder();
			while (sb.length() + computedBinary.length() < 24) {
		         sb.append('0');
		      }
		    sb.append(computedBinary);
		    String paddedString = sb.toString();
		    computedBinary = paddedString;  
		}
		else if(bs.length()<31)
		{
			flag_orig_byte=4;
			System.out.println("---4 bytes---");		
			computedBinary = computeBinary(bs);
			StringBuilder sb = new StringBuilder();
			while (sb.length() + computedBinary.length() < 32) {
		         sb.append('0');
		      }
		    sb.append(computedBinary);
		    String paddedString = sb.toString();
		    computedBinary = paddedString;  
		}
		System.out.println(">>>>"+computedBinary);
		if(flag_orig_byte==1) {
			System.out.println(computedBinary);	
		}
		else if(flag_orig_byte==2) {
			computedBinary = computedBinary.substring(0, 9) + "1" + computedBinary.substring(9, computedBinary.length());
			System.out.println(computedBinary.substring(1));	
		}
		else if(flag_orig_byte==3) {
			computedBinary = computedBinary.substring(0, 10) + "0" + computedBinary.substring(10, computedBinary.length());
			computedBinary = computedBinary.substring(0, 18) + "1" + computedBinary.substring(18, computedBinary.length());
			System.out.println(computedBinary.substring(2));	
			originalInBitSet = fromString(computedBinary.substring(2));
		}
		else if(flag_orig_byte==4) {
			computedBinary = computedBinary.substring(0, 10) + "0" + computedBinary.substring(10, computedBinary.length());
			computedBinary = computedBinary.substring(0, 19) + "0" + computedBinary.substring(19, computedBinary.length());
			computedBinary = computedBinary.substring(0, 24) + "1" + computedBinary.substring(24, computedBinary.length());
			System.out.println(computedBinary.substring(3));		
			originalInBitSet = fromString(computedBinary.substring(3));
		}
		
		System.out.println(originalInBitSet);
		writeInFile(originalInBitSet);
	}

	public static String computeBinary(BitSet bs) {
		//System.out.println(">>>"+bs);
		String val="";
		for(int i=bs.length()-1;i>=0;i--) {
			if(bs.get(i))
				val+="1";
			else
				val+="0";
		}
		return val;
	}
	
	private static BitSet fromString(String binary) {
	    BitSet bitset = new BitSet(binary.length());
	    for (int i = 0; i < binary.length(); i++) {
	        if (binary.charAt(i) == '1') {
	            bitset.set(i);
	        }
	    }
	    return bitset;
	}

	private static void writeInFile(BitSet bs) {
		File file = new File("data.txt");
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File("d:/input_encoded.txt"));
		    os.write(bs.toByteArray());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
