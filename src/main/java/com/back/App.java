package com.back;


import com.back.domain.system.controller.SystemController;
import com.back.domain.wisesaying.controller.WisesayingController;

import java.util.Scanner;

public class App {

    public void run() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("== 명언 앱 ==");

        SystemController systemController = AppContext.systemController;
        WisesayingController wisesayingController = AppContext.wiseSayingController;

        while (true) {
            System.out.println("명령)");
            String cmd = scanner.nextLine().trim();

            Rq rq = new Rq(cmd);
            switch (rq.getActionName()) {
                case "종료" -> {
                    systemController.actionExit();
                    return;
                }
                case "목록" -> wisesayingController.actionList();
                case "등록" -> wisesayingController.actionWrite();
                case "삭제" -> wisesayingController.actionDelete(rq);
                case "수정" -> wisesayingController.actionModify(rq);
            }
        }
    }
}