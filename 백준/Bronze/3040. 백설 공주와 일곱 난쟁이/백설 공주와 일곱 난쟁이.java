import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int arr[] = new int[9];
		int sum = 0;
		for(int i = 0;i<9; i++) {
			arr[i] = (Integer.parseInt(br.readLine()));
			sum += arr[i];
		}
		sum -= 100;
		int ans1=0,ans2=0;
		for(int i = 0;i<8;i++) {
			for(int j = i+1; j<9; j++) {
				if(arr[i] + arr[j] == sum) {
					ans1 = i;
					ans2 = j;
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<9; i++) {
			if(i == ans1 || i == ans2)continue;
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb.toString());
		
	}

}