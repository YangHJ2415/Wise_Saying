package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int lastId = 0;

        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("명령)");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")){
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();
                int no = ++lastId;
                System.out.println("%d번 명언이 등록되었습니다.".formatted(no));
            }
        }
    }
}