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
            } else if (cmd.startsWith("수정")) {
                actionModify(cmd);
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
            System.out.printf("%d / %s / %s", wiseSaying.id, wiseSaying.content,wiseSaying.author);
        }

    }
    void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wisesaying = write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.", wisesaying.id);
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

        if (deleteIndex == -1){ // 존재 하지 않는 명언에 대한 예외처리, 한번 삭제된 번호는 재사용 x
            System.out.printf("%d번 명언은 존재하지 않습니다.", id);
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.", id);
    }

    int delete(int id){
        int deleteIndex = findIndexById(id);

        if (deleteIndex == -1) return deleteIndex;

        for (int i = deleteIndex +1; i <= wiseSayingsLastIndex; i++){
            wiseSayings[i-1] = wiseSayings[i];
        }

        wiseSayings[wiseSayingsLastIndex] = null;
        wiseSayingsLastIndex--;

        return deleteIndex;
    }

    void actionModify(String cmd){
        String[] cmdBits = cmd.split("=", 2);

        if(cmdBits.length <2 || cmdBits[1].isEmpty()){
            System.out.println("id를 입력해주세요.");
            return;
        }
        int id = Integer.parseInt(cmdBits[1]);

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.", id);
            return;
        }

        System.out.printf("명언(기존) : %s", wiseSaying.content);
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s", wiseSaying.author);
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);
    }

    int findIndexById(int id){
        for (int i=0; i <= wiseSayingsLastIndex; i++){
            if (wiseSayings[i].id == id){
                return i;
            }
        }
        return -1;
    }

    WiseSaying findById(int id){
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayings[index];
    }

    void modify(WiseSaying wiseSaying, String content, String author){
        wiseSaying.content = content;
        wiseSaying.author = author;
    }

}