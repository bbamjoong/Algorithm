import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();  // BufferedReader 닫기
        
        String[] arr = {"pi", "ka", "chu"};
        
        for (String s : arr) {
            str = str.replaceAll(s, "O");
        }
        
        str = str.replace("O", "");
        
        if (str.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
