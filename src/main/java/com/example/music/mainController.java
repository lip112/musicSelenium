package com.example.music;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class mainController {
    private final mainService MainService;
    private final Selenium selenium;

    @GetMapping("/")
    public void main() throws IOException {
        selenium.open();

//        HashMap<String, String> main = MainService.main();
    }
}
