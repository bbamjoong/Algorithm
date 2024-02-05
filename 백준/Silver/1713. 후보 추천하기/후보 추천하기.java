import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n; // 액자
        int k; // 입력 횟수
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        Student students[] = new Student[101];
        List<Student> studentList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            int number = Integer.parseInt(st.nextToken());

            if (students[number] == null) { // 처음 들어옴
                students[number] = new Student(number, 0, 0, false);
            }

            if (students[number].isPosted) { // 이미 게시됨
                students[number].recommendation++;
            } else { // 게시된 적이 없었음
                if (studentList.size() == n) { // 이미 게시판이 꽉찼다면
                    // 추천순 정렬 -> 나이순 정렬
                    studentList.sort((o1, o2) -> {
                        if (o1.recommendation == o2.recommendation) {
                            return o1.time - o2.time;
                        }
                        return o1.recommendation - o2.recommendation;
                    });
                    studentList.get(0).isPosted = false;
                    studentList.remove(0);
                }
                students[number].recommendation = 1;
                students[number].time = i;
                students[number].isPosted = true;
                studentList.add(students[number]);
            }
        }
        
        studentList.sort((o1, o2) -> o1.idx - o2.idx);
        for (Student i : studentList) {
            sb.append(i.idx).append(" ");
        }
        System.out.println(sb);
    }

    static class Student {
        int idx;
        int recommendation;
        int time; // 언제 입력받았나
        boolean isPosted;

        public Student(int idx, int recommendation, int time, boolean isPosted) {
            this.idx = idx;
            this.recommendation = recommendation;
            this.time = time;
            this.isPosted = isPosted;
        }
    }// class Student

}