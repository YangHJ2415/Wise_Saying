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
                System.out.println("프로그램이 종료되었습니다.");
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String wiseSayingContent = scanner.nextLine().trim();
                System.out.print("작가 : ");
                String wiseSayingAuthor = scanner.nextLine().trim();

                int id = ++lastId;

                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = id;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuthor;

                System.out.println("==========================");
                System.out.println("id : %d".formatted(wiseSaying.id));
                System.out.println("명언 : %s".formatted(wiseSaying.content));
                System.out.println("작가 : %s".formatted(wiseSaying.author));

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            }
        }
    }
}