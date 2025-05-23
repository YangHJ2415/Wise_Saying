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
            } else if (cmd.startsWith("삭제")){
                actionDelete(cmd);
            } else {
                System.out.println("잘못된 명령어입니다.");
            }
        }
        scanner.close();
    }

    void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("==========================");

        WiseSaying[] forListWiseSayings = findForList();
        for (WiseSaying wiseSaying : forListWiseSayings){
            System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.content,wiseSaying.author);
        }

//        for(int i=wiseSayingsLastIndex; i>= 0; i--){
//            WiseSaying wiseSaying = wiseSayings[i];
//            System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.content,wiseSaying.author);
//        }
    }
    void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wisesaying = write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wisesaying.id);
    }

    int getSize() {
        return wiseSayingsLastIndex + 1;
    }

    WiseSaying[] findForList() {
        WiseSaying[] forListWiseSayings = new WiseSaying[getSize()];

        int forListWiseSayingsIndex = -1;

        for (int i = wiseSayingsLastIndex; i>=0; i--){
            forListWiseSayings[++forListWiseSayingsIndex] = wiseSayings[i];
        }
        return forListWiseSayings;
    }

    WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying();
        wiseSaying.id = ++lastId;
        wiseSaying.content = content;
        wiseSaying.author = author;
        wiseSayings[++wiseSayingsLastIndex] = wiseSaying;
        return wiseSaying;
    }

    void actionDelete(String cmd){
        String[] cmdBits = cmd.split("=", 2); // 기호를 기준으로 2조각으로 나눈기, cmdBits[1] = "id"

        if(cmdBits.length < 2 || cmdBits[1].isEmpty()){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        int deleteIndex = delete(id);

        if (deleteIndex == -1){
            System.out.printf("%d번 명언은 존재하지 않습니다.", id);
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
    }

    int delete(int id){
        int deleteIndex = -1;

        for (int i=0; i <= wiseSayingsLastIndex; i++){
            if (wiseSayings[i].id == id){
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == -1) return deleteIndex;

        for (int i = deleteIndex +1; i <= wiseSayingsLastIndex; i++){
            wiseSayings[i-1] = wiseSayings[i];
        }

        wiseSayings[wiseSayingsLastIndex] = null;
        wiseSayingsLastIndex--;

        return deleteIndex;
    }
}