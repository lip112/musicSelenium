package com.example.music;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Selenium {
    private final ResourceLoader resourceLoader;
    public void open() throws IOException {
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
            String title = infoElement.findElement(By.cssSelector("a.title.ellipsis")).getText();
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

    class Song {
        String artist;
        String image;
        String album;

        String Song_id;

        String download_Link;

        String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getSong_id() {
            return Song_id;
        }

        public void setSong_id(String song_id) {
            Song_id = song_id;
        }

        public String getDownload_Link() {
            return download_Link;
        }

        public void setDownload_Link(String download_Link) {
            this.download_Link = download_Link;
        }


        @Override
        public String toString() {
            return "Song{" +
                    "artist='" + artist + '\'' +
                    ", image='" + image + '\'' +
                    ", album='" + album + '\'' +
                    ", Song_id='" + Song_id + '\'' +
                    ", download_Link='" + download_Link + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
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