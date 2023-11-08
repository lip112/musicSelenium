package com.example.music;

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
public class AlbumService {
    private final ResourceLoader resourceLoader;

    public void albumSearch() throws IOException {
        int page = 2;
        String albumTitle = "강남";
        String url = "https://www.genie.co.kr/search/searchAlbum?query=" + albumTitle + "&page=" + page +"&pagesize=20&of=SCORE&fscount=&Genre=&Country=&reQuery=&researchyn=N";

        WebDriver driver = WebDriverUtil.getChromeDriver(resourceLoader);
        driver.get(url);
        List<Album> albums = new ArrayList<>();


        List<WebElement> elements = driver.findElements(By.className("list-album"));
        String totalSongCount = driver.findElement(By.xpath("//*[@id=\"body-content\"]/div[4]/div[1]/span/strong")).getText();
        for (WebElement web : elements) {
            Album song = new Album();

            WebElement imageElement = web.findElement(By.cssSelector("a.album-cover > img"));
            song.setImage(imageElement.getAttribute("src"));

            WebElement infoElement = web.findElement(By.className("album-info"));
            String album = infoElement.findElement(By.cssSelector("dt.ellipsis > a")).getText();
            song.setAlbum(album);

            String artist = infoElement.findElement(By.className("ellipsis")).getText();
            song.setArtist(artist);

            String since = infoElement.findElement(By.cssSelector("dd.desc")).getText();
            song.setSince(since);

            albums.add(song);

            System.out.println("album = " + song);
        }
        System.out.println("albums.size() = " + albums.size());

        driver.quit();
    }


    class Album {
        String album;

        String artist;
        String since;

        String image;

        String Song_id;

        String download_Link;


        @Override
        public String toString() {
            return "Song{" +
                    ", album='" + album + '\'' +
                    ", artist='" + artist + '\'' +
                    ", since='" + since + '\'' +
                    ", image='" + image + '\'' +
                    ", Song_id='" + Song_id + '\'' +
                    ", download_Link='" + download_Link + '\'' +
                    '}';
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getSince() {
            return since;
        }

        public void setSince(String since) {
            this.since = since;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

    }
}
