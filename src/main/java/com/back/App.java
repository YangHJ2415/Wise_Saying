package com.back;

import java.util.Scanner;

public class App {
    WiseSaying[] wiseSayings = new WiseSaying[100];
    int wiseSayingsLastIndex = -1;
    int lastId = 0;
    Scanner scanner = new Scanner(System.in);

    void run(){
        System.out.println("== 명언 앱 ==");
        while (true){
            System.out.println("명령)");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")){
                System.out.println("프로그램이 종료되었습니다.");
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")){
                actionList();
            }
        }
        scanner.close();
    }
    void actionList(){
        for(int i=wiseSayingsLastIndex; i>= 0; i--){
            WiseSaying wiseSaying = wiseSayings[i];

            System.out.println("번호 / 작가 / 명언");
            System.out.println("==========================");
            System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.content,wiseSaying.author);
        }
    }
    void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wisesaying = write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wisesaying.id);
    }

    WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;
        wiseSayings[++wiseSayingsLastIndex] = wiseSaying;
        return wiseSaying;
    }
}