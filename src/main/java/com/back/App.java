package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private Scanner scanner = new Scanner(System.in);
    private int lastId = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public void run(){
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

    private void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("==========================");

        List<WiseSaying> forListWiseSayings = findForList();
        for (WiseSaying wiseSaying : forListWiseSayings){
            System.out.printf("%d / %s / %s %n", wiseSaying.getId(), wiseSaying.getContent(),wiseSaying.getAuthor());
        }

    }
    private void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wisesaying = write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wisesaying.getId());
    }

    private void actionDelete(String cmd){
        String[] cmdBits = cmd.split("=", 2); // 기호를 기준으로 2조각으로 나눈기, cmdBits[1] = "id"

        if(cmdBits.length < 2 || cmdBits[1].isEmpty()){
            System.out.println("id를 입력해주세요.");
            return;
        }

        int id = Integer.parseInt(cmdBits[1]);

        boolean deleted = delete(id);

        if (!deleted){ // 존재 하지 않는 명언에 대한 예외처리, 한번 삭제된 번호는 재사용 x
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
    }

    private void actionModify(String cmd){
        String[] cmdBits = cmd.split("=", 2);

        if(cmdBits.length <2 || cmdBits[1].isEmpty()){
            System.out.println("id를 입력해주세요.");
            return;
        }
        int id = Integer.parseInt(cmdBits[1]);

        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
            return;
        }

        System.out.printf("명언(기존) : %s", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();

        System.out.printf("작가(기존) : %s", wiseSaying.getAuthor());
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        modify(wiseSaying, content, author);
    }

    private List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }

    private WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    private boolean delete(int id){
//        int deleteIndex = findIndexById(id);
//
//        if (deleteIndex == -1) return deleteIndex;
//
//        wiseSayings.remove(deleteIndex);
//
//        return deleteIndex;
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    private void modify(WiseSaying wiseSaying, String content, String author){
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    private int findIndexById(int id){
//        for (int i=0; i <= wiseSayings.size(); i++){
//            if (wiseSayings.get(i).getId() == id){
//                return i;
//            }
//        }
//        return -1;
        return wiseSayings.stream()
                .map(WiseSaying::getId)
                .toList()
                .indexOf(id);
    }

    private WiseSaying findById(int id){
        int index = findIndexById(id);

        if (index == -1) return null;

        return wiseSayings.get(index);
    }
}