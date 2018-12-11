
public class gammaDelta {
	static String head,tail,tail2,pad="";
	static String new_head="";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 25;
		String numToBinary = Integer.toBinaryString(num);
		System.out.println(numToBinary);
		convertToGamma(numToBinary,'g');		
		String gamma = pad+"0,"+tail;
		tail2=tail;
		System.out.println("gamma="+gamma);

		convertToGamma(pad,'d');
		String delta = new_head+"1,"+tail2;
		System.out.println("delta="+delta);
	}

	private static void convertToGamma(String numToBinary,char x) {
		if(x=='g') {
			head = numToBinary.substring(0,1);
			tail = numToBinary.substring(1);
			System.out.println(head+","+tail);
			for(int i=0;i<tail.length();i++) {
				pad+="1";
			}
		}
		else if(x=='d') {
			head = numToBinary.substring(1);
			//tail = numToBinary.substring(1);
			//System.out.println(head+","+tail);
			int d_size = head.length();
			new_head = Integer.toBinaryString(d_size);			
		}
	}

}
