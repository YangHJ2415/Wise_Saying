package com.back.domain.wisesaying.controller;

import com.back.Rq;
import com.back.WiseSaying;
import com.back.domain.wisesaying.service.WisesayingService;
import java.util.List;
import java.util.Scanner;

public class WisesayingController {
    private final Scanner scanner;
    WisesayingService wisesayingService;

    public WisesayingController(Scanner scanner) {
        this.scanner = scanner;
        this.wisesayingService = new WisesayingService();
    }

    public void actionList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("==========================");

        List<WiseSaying> forListWiseSayings = wisesayingService.findForList();
        for (WiseSaying wiseSaying : forListWiseSayings){
            System.out.printf("%d / %s / %s %n", wiseSaying.getId(), wiseSaying.getContent(),wiseSaying.getAuthor());
        }
    }

    public void actionWrite(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();

        WiseSaying wisesaying = wisesayingService.write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.%n", wisesaying.getId());
    }

    public void actionDelete(Rq rq){
        int id = rq.getParamAsInt("id", -1);

        if(id == -1){
            System.out.println("id를 입력해주세요.");
            return;
        }

        boolean deleted = wisesayingService.delete(id);

        if (!deleted){ // 존재 하지 않는 명언에 대한 예외처리, 한번 삭제된 번호는 재사용 x
            System.out.printf("%d번 명언은 존재하지 않습니다.%n", id);
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.%n", id);
    }

    public void actionModify(Rq rq){
        int id = rq.getParamAsInt("id", -1);

        if(id == -1){
            System.out.println("id를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wisesayingService.findById(id);

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

        wisesayingService.modify(wiseSaying, content, author);
    }
}