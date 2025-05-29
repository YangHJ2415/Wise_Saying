package com.back.domain.wisesaying.controller;

import com.back.Rq;
import com.back.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WisesayingController {
    private final Scanner scanner;
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public WisesayingController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("==========================");

        List<WiseSaying> forListWiseSayings = findForList();
        for (WiseSaying wiseSaying : forListWiseSayings){
            System.out.printf("%d / %s / %s %n", wiseSaying.getId(), wiseSaying.getContent(),wiseSaying.getAuthor());
        }
    }
    private List<WiseSaying> findForList() {
        return wiseSayings.reversed();
    }

    public void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wisesaying = write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wisesaying.getId());
    }
    private WiseSaying write(String content, String author){
        WiseSaying wiseSaying = new WiseSaying(++lastId, content, author);
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public void actionDelete(Rq rq){
        int id = rq.getParamAsInt("id", -1);

        if(id == -1){
            System.out.println("id를 입력해주세요.");
            return;
        }

        boolean deleted = delete(id);

        if (!deleted){ // 존재 하지 않는 명언에 대한 예외처리, 한번 삭제된 번호는 재사용 x
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
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

    public void actionModify(Rq rq){
        int id = rq.getParamAsInt("id", -1);

        if(id == -1){
            System.out.println("id를 입력해주세요.");
            return;
        }

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
    private WiseSaying findById(int id){
        return wiseSayings
                .stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }
    private void modify(WiseSaying wiseSaying, String content, String author){
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}


