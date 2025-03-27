import java.io.*;
import java.util.*;
 
public class Main {
	
	public static int N, M;
	public static int[][] arr;
	public static String S, T;
	public static int result = -1;
    
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	arr = new int[N][M];
    	for(int i=0;i<N;i++) {
    		String s = br.readLine();
    		for(int j=0;j<M;j++) {
    			arr[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));	
    		}
    	}
    	
    	for(int i=0; i < N; i++) {
    		for(int j=0; j < M; j++) {
    			for(int di = -N; di < N; di++){ // 행 등차값
    				for(int dj = -M; dj < M; dj++) { //열 등차 값
    					
    					if(di == 0 && dj == 0) { //등차 값 0이면 진행x
    						continue;
    					}
    					
    					int nI = i;
    					int nJ = j;
    					int now = 0;
                        
    					while( nI >= 0 && nI < N && nJ >= 0 && nJ < M) {
    						now *= 10;
    						now += arr[nI][nJ];

    						int sqrt_check = (int) Math.sqrt( (double) now);
    						if( sqrt_check * sqrt_check == now) result = Math.max(result, now);
    						
    						nI += di;
    						nJ += dj;
    					}
    				}
    			}
    		}
    	}
    	System.out.println(result);	
    }
}