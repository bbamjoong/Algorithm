//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> stack = new ArrayList<Integer>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] array = br.readLine().split(" ");
            if (array.length == 2) {
                stack.add(0, Integer.parseInt(array[1]));
            } else {
                switch (array[0]) {
                    case "top":
                        if (stack.size() == 0) {
                            System.out.println("-1");
                        } else {
                            System.out.println(stack.get(0));
                        }
                        break;
                    case "size":
                        System.out.println(stack.size());
                        break;
                    case "pop":
                        if (stack.size() == 0) {
                            System.out.println("-1");
                        } else {
                            System.out.println(stack.get(0));
                            stack.remove(0);
                        }
                        break;
                    case "empty":
                        if (stack.size() == 0) {
                            System.out.println("1");
                        } else {
                            System.out.println("0");
                        }
                        break;
                }
            }
        }
    }
}