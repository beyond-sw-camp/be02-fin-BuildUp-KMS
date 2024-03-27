package com.example.bootshelf.es.controller;

import com.example.bootshelf.es.model.EsBoard;
import com.example.bootshelf.es.service.EsBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/es/board")
public class EsBoardController {
    private final EsBoardService esBoardService;

    @GetMapping("/boards/search")
    public ResponseEntity<Page<EsBoard>> searchBoards(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<EsBoard> boards = esBoardService.searchByTitle(title, page, size);
        return ResponseEntity.ok(boards);
    }
}
