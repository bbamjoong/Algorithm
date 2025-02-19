import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        double A[] = new double[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++) {
            A[i] = Double.parseDouble(st.nextToken());
        }
        double result = Double.MAX_VALUE;
        while(K <= N) {
            for(int i = 0; i <= N-K; i++) {
                double m = getM(A, i, N, K);
                double b = getB(A, m, i, N, K);
                
                result = Math.min(result, b);
            }
            K += 1;
        }
 
        double newResult = Double.parseDouble(String.format("%.11f", result));
        System.out.println(newResult);
    }
    
    public static double getM (double[] A, int idx, int N, int K) {
        double sum = 0.0;
        for(int i = 0; i < K; i++) {
            sum += A[idx+i];
        }
        return sum / K;
    }
    
    public static double getB(double[] A, double m, int idx, int N, int K) {
        double sum = 0.0;
        for(int i=0; i < K; i++) {
            sum += (Math.pow(A[idx+i]-m, 2));
        }
        return Math.sqrt(sum/K);
    }
}
