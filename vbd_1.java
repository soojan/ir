import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.BitSet;

public class vbd_1 {
	static int lastSetBit = 0;
	static int dataSize = 32;  //8 16 24 32 
	
	public static void main(String[] args) {
		int original=0;
		byte[] data ;
		
		BitSet b = new BitSet(dataSize);
		//FILE READ
		try {
			Path file = Paths.get("d:/input_encoded.txt");
			data = Files.readAllBytes(file);
			System.out.println(new String(data, StandardCharsets.UTF_8));
			BitSet bs = BitSet.valueOf(data);
			for(int i=0;i<dataSize;i++) {
				if(BitSet.valueOf(data).get(i) == true)
					{
						b.set(i);
						lastSetBit=i;
					}
			}
			if (dataSize == 24)
				decodeToByte(b);
			else if (dataSize == 16)
				decodeToByte2(b);
			else if (dataSize == 32)
				decodeToByte3(b);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//FILE READ END
	}

	private static void decodeToByte(BitSet b) {
		// TODO Auto-generated method stub
		String bytedata="";
		for(int i=0;i<b.length();i++) {
			if(b.get(i)==true)
				bytedata+="1";
			else
				bytedata+="0";
		}
		for(int j=lastSetBit+1;j<dataSize;j++) {
			bytedata+="0";
		}
		System.out.println(bytedata);		
		byteToDecimal(bytedata);
	}
	
	private static void decodeToByte2(BitSet b) {
		// TODO Auto-generated method stub
		String bytedata="";
		for(int i=0;i<b.length();i++) {
			if(b.get(i)==true)
				bytedata+="1";
			else
				bytedata+="0";
		}
		//
		String padding="";
		for(int k=bytedata.length();k<16;k++) {
			padding+="0";
		}
		bytedata = padding + bytedata;

		//
		System.out.println(bytedata);		
		byteToDecimal(bytedata);
	}

	private static void decodeToByte3(BitSet b) {
		// TODO Auto-generated method stub
		String bytedata="";
		for(int i=0;i<b.length();i++) {
			if(b.get(i)==true)
				bytedata+="1";
			else
				bytedata+="0";
		}
		//
		String padding="";
		for(int k=bytedata.length();k<32;k++) {
			padding+="0";
		}
		bytedata =   bytedata + padding;

		//
		System.out.println(bytedata);		
		byteToDecimal(bytedata);
	}
	
	private static void byteToDecimal(String bytedata) {
		// TODO Auto-generated method stub
		int s_len = bytedata.length();
		int[] byte_holder; 
		String a,b,c,d;
		if(s_len == 8)
		{
			byte_holder = new int[8];
		}
		else if(s_len == 16)
		{
			byte_holder = new int[16];
			a=bytedata.substring(0, 8);
			b=bytedata.substring(8, 16);
			System.out.println(a+" "+b);
			//a = a.substring(1);
			b = b.substring(1);
			String concatenated = "0"+a + b;		

			BitSet bs = new BitSet(s_len);
			for(int i=0;i<s_len;i++) {
				if(i<8)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				if(i>=8 && i<16)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}				
			}
			int res=0;
			for(int i=0;i<bs.length();i++) {				
				if(bs.get(i)) {
					res += getIndex16(i);
				}
			}
	        System.out.println(res);
		}		
			///
		
		else if(s_len == 24 )
		{
			byte_holder = new int[24];			
			a=bytedata.substring(0, 8);
			b=bytedata.substring(8, 16);
			c=bytedata.substring(16, 24);
			System.out.println(a+" "+b+" "+c);
			b = b.substring(1);
			c = c.substring(1);
			String concatenated = "00"+a + b + c;		

			BitSet bs = new BitSet(s_len);
			for(int i=0;i<s_len;i++) {
				if(i<8)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				if(i>=8 && i<16)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				if(i>=16 && i<24)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				
			}
			int res=0;
			for(int i=0;i<bs.length();i++) {				
				if(bs.get(i)) {
					res += getIndex24(i);
				}
			}
	        System.out.println(res);
		}
		else
		{
			byte_holder = new int[32];
			a=bytedata.substring(0, 8);
			b=bytedata.substring(8, 16);
			c=bytedata.substring(16, 24);
			d=bytedata.substring(24, 32);
			System.out.println("1>>>"+a+""+b+""+c+ ""+d);
			b = b.substring(1);
			c = c.substring(1);
			d = d.substring(1);
			String concatenated = a + b + c + d ;		
			System.out.println("2>>>"+a+""+b+""+c+ ""+d+","+concatenated.length());
			String padding="";
			for(int k=concatenated.length();k<32;k++) {
				padding+="0";
			}
			System.out.println(padding);
			concatenated = padding+concatenated;
			System.out.println("3>>>"+concatenated);
			BitSet bs = new BitSet(s_len);
			for(int i=0;i<s_len;i++) {
				if(i<8)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				if(i>=8 && i<16)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				if(i>=16 && i<24)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				if(i>=24 && i<32)
				{	
					char ch = concatenated.charAt(i);
					if(ch == '1')
					bs.set(i, true);
				}
				
			}
			int res=0;
			for(int i=0;i<bs.length();i++) {				
				if(bs.get(i)) {
					res += getIndex32(i);
				}
			}
	        System.out.println(res);
		}		
	}

	private static int getIndex32(int i) {
		int x = (int) Math.pow(2, (31 - i));
		return x;		
	}	
	
	private static int getIndex24(int i) {
		int x = (int) Math.pow(2, (23 - i));
		return x;		
	}	
	
	private static int getIndex16(int i) {
		int x = (int) Math.pow(2, (15 - i));
		return x;		
	}		
	
}
