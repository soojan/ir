import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.BitSet;

public class vbd_1 {
	static int lastSetBit = 0;
	static int dataSize = 24;
	
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
			decodeToByte(b);
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
		}
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
		}		
	}

	private static int getIndex24(int i) {
		int x = (int) Math.pow(2, (23 - i));
		return x;		
	}	
}
