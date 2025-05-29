package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wisesaying.controller.WisesayingController;
import com.back.domain.wisesaying.repoitory.WiseSayingRepository;
import com.back.domain.wisesaying.service.WisesayingService;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppContext {
    public static final Scanner scanner = new Scanner(System.in);
    public static final DateTimeFormatter forPrintDateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");

    public static final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
    public static final WisesayingService wiseSayingService = new WisesayingService();
    public static final WisesayingController wiseSayingController = new WisesayingController();

    public static final SystemController systemController = new SystemController();
}
