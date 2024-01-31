import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		int N = nextInt();
		int M = nextInt();
		
		int[] nums = new int[N];
		nums[0] = nextInt();
		for(int i=1; i<N; i++) {
			nums[i] = nextInt() + nums[i-1];
		}
		
		
		for(int i=0; i<M; i++) {
			int fromIdx = nextInt() - 1;
			int toIdx = nextInt() - 1;
			int sum = nums[toIdx];
			if(fromIdx > 0) {
				sum -= nums[fromIdx-1];
			}
			
			sb.append(sum).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static final int BUFFER_SIZE = 1 << 13;
	static byte[] buffer = new byte[BUFFER_SIZE];
	static int bufferLen, bufferIdx;
	
	static int nextInt() throws IOException {
		byte b;
		int n = 0;
		while((b=read()) <= 32);
		do {
			n = (n<<3) + (n<<1) + (b&15);
		}while(isNumber(b=read()));
		
		return n;
	}
	
	static boolean isNumber(byte b) {
		return 47 < b && b < 58;
	}
	
	static byte read() throws IOException {
		if(bufferIdx == bufferLen) {
			bufferLen = System.in.read(buffer, bufferIdx=0, BUFFER_SIZE);
			if(bufferLen == -1) {
				buffer[0] = -1;
			}
		}
		return buffer[bufferIdx++];
	}
}