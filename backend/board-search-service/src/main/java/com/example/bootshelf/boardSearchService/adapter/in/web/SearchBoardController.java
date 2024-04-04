package com.example.bootshelf.boardSearchService.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class SearchBoardController {


    @RequestMapping(method = RequestMethod.POST, value = "/board-search-service/login")
    public ResponseEntity<String> login(@RequestBody String name) {

        String nickName = "지흔";
        return ResponseEntity.ok().body(nickName);
    }
}