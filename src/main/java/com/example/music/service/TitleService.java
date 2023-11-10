package com.example.music.service;

import com.example.music.WebDriverUtil;
import com.example.music.entity.Song;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final ResourceLoader resourceLoader;
    public void TitleSearch() throws IOException {
        int page = 2;
        String songTitle = "강남";
        String url = "https://www.genie.co.kr/search/searchSong?Coll=sAll&query=" + songTitle + "&page=" + page +"&pagesize=20&of=SCORE&fscount=&Genre=&Country=&reQuery=&researchyn=N";

        WebDriver driver = WebDriverUtil.getChromeDriver(resourceLoader);
        driver.get(url);
        List<Song> songs = new ArrayList<>();


        WebElement element = driver.findElement(By.className("list-wrap"));
        List<WebElement> elements = element.findElements(By.cssSelector("tr.list"));//tr태그의 list클래스
        String totalSongCount = driver.findElement(By.xpath("//*[@id=\"ALL\"]/a/strong/strong")).getText();
        for (WebElement web : elements) {
            Song song = new Song();

            WebElement imageElement = web.findElement(By.cssSelector("a.cover > img"));
            song.setImage(imageElement.getAttribute("src"));

            WebElement infoElement = web.findElement(By.className("info"));
            String title = infoElement.findElement(By.cssSelector("a.title.ellipsis")).getAttribute("title");
            song.setTitle(title);

            String artist = infoElement.findElement(By.cssSelector("a.artist.ellipsis")).getText();
            song.setArtist(artist);

            String album = infoElement.findElement(By.cssSelector("a.albumtitle.ellipsis")).getText();
            song.setAlbum(album);

            songs.add(song);

            System.out.println("song = " + song);
        }
        System.out.println("songs.size() = " + songs.size());

        driver.quit();
    }


}


//
//// 이미지 다운로드
//            try (BufferedInputStream in = new BufferedInputStream(new URL(song.getImage()).openStream());
//                    FileOutputStream fileOutputStream = new FileOutputStream("path/to/save/image.jpg" + "")) {
//                    byte dataBuffer[] = new byte[1024];
//                    int bytesRead;
//                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//                    fileOutputStream.write(dataBuffer, 0, bytesRead);
//                    }
//                    } catch (IOException e) {
//                    // 에러 처리
//                    e.printStackTrace();
//                    }