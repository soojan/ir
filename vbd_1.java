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
		System.out.println(b.length());
		//FILE READ
		try {
			Path file = Paths.get("d:/input_encoded.txt");
			data = Files.readAllBytes(file);
			System.out.println(new String(data, StandardCharsets.UTF_8));
			BitSet bs = BitSet.valueOf(data);
			//System.out.println(BitSet.valueOf(data));
			//System.out.println(bs);
			for(int i=0;i<dataSize;i++) {
				if(BitSet.valueOf(data).get(i) == true)
					{
						b.set(i);
						lastSetBit=i;
						System.out.println(i+","+b.get(i));
					}
					}
			System.out.println(b.length()+"BBB");
			decodeToByte(b);
			//System.out.println(b.length());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//FILE READ END
	}

	private static void decodeToByte(BitSet b) {
		// TODO Auto-generated method stub
		String bytedata="";
		//System.out.println(b.length()+"DDD");
		for(int i=0;i<b.length();i++) {
			if(b.get(i)==true)
				bytedata+="1";
			else
				bytedata+="0";
		}
		System.out.println(bytedata);
		for(int j=lastSetBit+1;j<dataSize;j++) {
			bytedata+="0";
		}
		System.out.println(bytedata);

	}
}
