import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

    static class Exp {
        String A, B, C;
        boolean isAdd;

        Exp(String expression) {
            StringTokenizer st = new StringTokenizer(expression);
            A = st.nextToken();
            isAdd = st.nextToken().equals("+");
            B = st.nextToken();
            st.nextToken(); // "="
            C = st.nextToken();
        }

        int compute(int radix) {
            int aa = Integer.parseInt(A, radix);
            int bb = Integer.parseInt(B, radix);
            if (isAdd) {
                return aa + bb;
            }
            return aa + -bb;
        }

        boolean isValid(int radix) {
            int cc = Integer.parseInt(C, radix);
            return compute(radix) == cc;
        }

        String formatResult(int radix) {
            return Integer.toString(compute(radix), radix);
        }
    }

    static List<Exp> expList;
    static int mxRadix = 2;
    static boolean[] failed = new boolean[10];

    public String[] solution(String[] expressions) {
        int n = expressions.length;
        expList = new ArrayList<>(n);

        for (String s : expressions) {
            expList.add(new Exp(s));
        }

        mxRadix = 2;
        for (String s : expressions) { // 최소 진법(mxRadix) 계산 (문자 중 가장 큰 숫자 +1)
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    mxRadix = Math.max(mxRadix, c - '0' + 1);
                }
            }
        }

        for (Exp e : expList) {
            if ("X".equals(e.C)) {
                continue;
            }
            for (int r = mxRadix; r <= 9; r++) {
                if (failed[r]) {
                    continue;
                }
                if (!e.isValid(r)) {
                    failed[r] = true;
                }
            }
        }

        List<String> ansList = new ArrayList<>();
        for (Exp e : expList) {
            if (!"X".equals(e.C)) {
                continue;    // 이미 완성된 식은 건너뛰기
            }

            StringBuilder sb = new StringBuilder();
            sb.append(e.A)
                    .append(e.isAdd ? " + " : " - ")
                    .append(e.B)
                    .append(" = ");

            String candidate = null;
            boolean ambiguous = false;

            for (int r = mxRadix; r <= 9; r++) {
                if (failed[r]) {
                    continue;
                }
                String res = e.formatResult(r);

                if (candidate == null) { // 첫번째 진법 경우의 수.
                    candidate = res;

                } else if (!candidate.equals(res)) { // x번째 진법 경우의 수. 이전 진법과 결과가 다르면 안됨.
                    ambiguous = true;
                    break;
                }
            }

            if (candidate == null || ambiguous) { // 가능한 진법이 없거나, 진법이 여러개이지만 결과가 다르다면 "?"
                sb.append("?");
            } else {
                sb.append(candidate);
            }
            ansList.add(sb.toString());
        }

        String[] ans = new String[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }

        return ans;
    }
}
