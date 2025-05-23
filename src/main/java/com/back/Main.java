package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WiseSaying wiseSaying = null;
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

                wiseSaying = new WiseSaying();
                wiseSaying.id = id;
                wiseSaying.content = wiseSayingContent;
                wiseSaying.author = wiseSayingAuthor;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")){
                if(wiseSaying == null) continue; // 명언이 등록되지 않았을 때
                System.out.println("번호 / 작가 / 명언");
                System.out.println("==========================");
                System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.content,wiseSaying.author));
            }
        }
    }
}