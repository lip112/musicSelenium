package com.example.music;


import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Component
public class WebDriverUtil {

    public static WebDriver getChromeDriver(ResourceLoader resourceLoader) throws IOException {
        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            // WebDriver 경로

            Resource resource = resourceLoader.getResource("classpath:chromedriver");
            File file = resource.getFile();
            String absolutePath = file.getAbsolutePath();
            System.setProperty("webdriver.chrome.driver", absolutePath);
        }

        // webDriver 옵션 설정
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu"); //gpu를사용하지 않음 리눅스로 헤드리스를 사용할때 필요
        chromeOptions.addArguments("--disable-popup-blocking");       //팝업안띄움
        chromeOptions.addArguments("headless");                       //브라우저 안띄움
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음


        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // 접속시 최대 30초기다림

        return driver;
    }

    public static void quit(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.quit();
        }
    }

    public static void close(WebDriver driver) {
        if (!ObjectUtils.isEmpty(driver)) {
            driver.close();
        }
    }

}
