import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int a = Integer.parseInt(br.readLine());

        int s = 0;

        StringTokenizer st;
        for (int i = 0; i < a; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            int num = 0;
            while (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            switch (str) {
                case ("add"):
                    s |= (1 << num);
                    break;
                case ("remove"):
                    s &= ~(1 << num);
                    break;
                case ("check"):
                    if ((s & (1 << num)) > 0) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case ("toggle"):
                    s ^= (1 << num);
                    break;
                case ("all"):
                    s = (1 << 21) - 1;
                    break;
                case ("empty"):
                    s = 0;
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}