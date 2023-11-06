package com.example.music;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class Selenium {
    private WebDriver webDriver;
    
    private String URL = "https://www.naver.com/";

    public void open() {
        WebDriver driver = WebDriverUtil.getChromeDriver();
        
        driver.get(URL);

    }
}
