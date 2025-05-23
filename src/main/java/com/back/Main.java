package com.back;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WiseSaying[] wiseSayings = new WiseSaying[100];
        int wiseSayingsLastIndex = -1;

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
                wiseSayings[++wiseSayingsLastIndex] = wiseSaying;

                System.out.printf("%d번 명언이 등록되었습니다.%n", id);
            } else if (cmd.equals("목록")){

                for(int i=wiseSayingsLastIndex; i>= 0; i--){
                    WiseSaying wiseSaying = wiseSayings[i];

                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("==========================");
                    System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.content,wiseSaying.author);
                }
            }
        }
    }
}