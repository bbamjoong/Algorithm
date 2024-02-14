import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Egg> eggs = new ArrayList<>();
    static int ans = Integer.MIN_VALUE;

    static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static void eggAttack(int idx, int cnt) {
        if (idx == n) {
            ans = Math.max(ans, cnt);
            return;
        }
        // 손으로 든 계란이 이미 깨졌거나 모든 계란이 이미 다 깨져 있다면
        if (eggs.get(idx).durability <= 0 || cnt == n - 1) {
            // 다음 계란을 들어 봄
            eggAttack(idx + 1, cnt);
            return;
        }

        int beforeRecoveryCnt = cnt; // 회복 전 cnt
        for (int i = 0; i < n; i++) {

            if (i == idx) { // 끝에 도달하면 continue
                continue;
            }

            if (eggs.get(i).durability <= 0) { // 내가 잡을 계란이 죽어있으면 continue
                continue;
            }

            eggFight(eggs.get(idx), eggs.get(i)); // 계란 싸움

            if (eggs.get(idx).durability <= 0) { // 싸움 이후 손에 든 계란이 죽어있으면 cnt++
                cnt++;
            }

            if (eggs.get(i).durability <= 0) { // 싸움 이후 선택한 계란이 죽어있으면 cnt++
                cnt++;
            }

            eggAttack(idx + 1, cnt);
            eggRecover(eggs.get(idx), eggs.get(i));
            cnt = beforeRecoveryCnt;
        }
    }
    
    static void eggFight(Egg handEgg, Egg targetEgg) {
        targetEgg.durability -= handEgg.weight;
        handEgg.durability -= targetEgg.weight;
    }

    static void eggRecover(Egg handEgg, Egg targetEgg) {
        targetEgg.durability += handEgg.weight;
        handEgg.durability += targetEgg.weight;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken()); // 계란의 내구도
            int weight = Integer.parseInt(st.nextToken()); // 계란의 무게
            eggs.add(new Egg(durability, weight));
        }

        eggAttack(0, 0); // 0번째 계란부터 시작 , 이 땐 깨진 계란 0개

        System.out.println(ans);
    }
}