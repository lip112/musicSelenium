package com.example.music.service;

import com.example.music.WebDriverUtil;
import com.example.music.entity.Album;
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
            String album_id = infoElement.findElement(By.cssSelector("dt.ellipsis > a")).getAttribute("onclick");
            //slicing -> "fnViewAlbumLayer('80523187');return false;" -> 80523187
            int startIndex = album_id.indexOf("'") + 1; // 시작 따옴표 이후 인덱스
            int endIndex = album_id.lastIndexOf("'"); // 마지막 따옴표 인덱스
            String result = album_id.substring(startIndex, endIndex);
            song.setAlbum_id(result);

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

    public void DetailAlbum(String albumId) throws IOException {
        String url = "https://www.genie.co.kr/detail/albumInfo?axnm=" + albumId;

        WebDriver driver = WebDriverUtil.getChromeDriver(resourceLoader);
        driver.get(url);
        List<Song> songs = new ArrayList<>();

        Album album = new Album();

        String album_image = driver.findElement(By.cssSelector("#body-content > div.album-detail-infos > div.photo-zone > a > span.cover > img")).getAttribute("src");
        album.setImage(album_image);

        String artist = driver.findElement(By.cssSelector("#body-content > div.album-detail-infos > div.info-zone > ul > li:nth-child(1) > span.value > a")).getText();
        album.setArtist(artist);

        String since = driver.findElement(By.cssSelector("#body-content > div.album-detail-infos > div.info-zone > ul > li:nth-child(5) > span.value")).getText();
        album.setSince(since);

        WebElement element = driver.findElement(By.className("list-wrap"));
        List<WebElement> elements = element.findElements(By.cssSelector("tr.list"));//tr태그의 list클래스

        for (WebElement web : elements) {

            Song song = new Song();

            WebElement infoElement = web.findElement(By.className("info"));

            String title = infoElement.findElement(By.cssSelector("a.title.ellipsis")).getAttribute("title");
            song.setTitle(title);

            String song_artist = infoElement.findElement(By.cssSelector("a.artist.ellipsis")).getText();
            song.setArtist(song_artist);

            songs.add(song);

            System.out.println("song = " + song);
        }
        System.out.println("albums.size() = " + songs.size());

        driver.quit();
    }
}
