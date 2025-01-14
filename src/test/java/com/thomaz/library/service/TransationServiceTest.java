package com.thomaz.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransationServiceTest {

    @Autowired
    private TransationService transationService;

    @Test
    public void test() {
        transationService.execute();
    }

    @Test
    public void managed() {
        transationService.update();
    }
}