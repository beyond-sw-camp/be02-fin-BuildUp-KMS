//package com.example.bootshelf.es.service;
//
//import com.example.bootshelf.es.model.EsBoard;
//import com.example.bootshelf.es.repository.EsBoardRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class EsBoardService {
//    private final EsBoardRepository esBoardRepository;
//
//    public Page<EsBoard> searchByTitle(String title, int page, int size) {
//        return esBoardRepository.findByBoardTitleContaining(title, PageRequest.of(page, size));
//    }
//}
