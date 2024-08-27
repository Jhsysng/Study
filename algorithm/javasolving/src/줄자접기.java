import java.util.Scanner;

public class 줄자접기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 줄자의 길이
        double rulerLength = scanner.nextInt();

        // 빨간 점의 위치
        int red1 = scanner.nextInt();
        int red2 = scanner.nextInt();

        // 파란 점의 위치
        int blue1 = scanner.nextInt();
        int blue2 = scanner.nextInt();

        // 노란 점의 위치
        int yellow1 = scanner.nextInt();
        int yellow2 = scanner.nextInt();

        // 빨간 점부터 접기
        rulerLength = foldRuler(rulerLength, red1, red2);

        // 파란 점 접기
        rulerLength = foldRuler(rulerLength, blue1, blue2);

        // 노란 점 접기
        rulerLength = foldRuler(rulerLength, yellow1, yellow2);

        // 결과 출력 (소수점 이하 1자리까지 출력)
        System.out.printf("%.1f\n", rulerLength);

        scanner.close();
    }

    // 두 점을 기준으로 줄자를 접는 함수
    private static double foldRuler(double length, int p1, int p2) {
        // 두 점을 작은 순서로 정렬
        int left = Math.min(p1, p2);
        int right = Math.max(p1, p2);

        // 접을 위치는 두 점의 중간
        double foldPoint = (left + right) / 2.0;

        // 접힌 후 줄자의 새로운 길이는 줄자에서 더 큰 쪽의 길이
        double newLength = Math.min(foldPoint, length - foldPoint);

        return newLength;
    }
}