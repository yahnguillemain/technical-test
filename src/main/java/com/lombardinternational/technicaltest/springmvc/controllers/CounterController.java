package com.lombardinternational.technicaltest.springmvc.controllers;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/counter")
@RestController
@RequiredArgsConstructor
public class CounterController {

    private final CounterService service;

    @GetMapping
    public CountResult get() {
        return CountResult.builder().result(service.getValue()).build();
    }

    @PatchMapping
    public void incrementCounter() {
        service.increment();
    }

    @Data
    @Builder
    public static class CountResult {

        private int result;
    }

}
