package com.example.music.service;

import com.example.music.WebDriverUtil;
import com.example.music.entity.Album;
import com.example.music.entity.Artist;
import com.example.music.entity.Song;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ResourceLoader resourceLoader;
    public void AtristSearch(String artist) throws IOException {
        String url = "https://www.genie.co.kr/search/searchArtist?query=" + artist + "&Coll=";

        WebDriver driver = WebDriverUtil.getChromeDriver(resourceLoader);
        driver.get(url);
        List<Artist> artists = new ArrayList<>();

        List<WebElement> elements = driver.findElements(By.className("artist-item"));
        String totalArtist = driver.findElement(By.xpath("//*[@id=\"body-content\"]/div[4]/div[1]/span/strong")).getText();
        System.out.println("totalArtist = " + totalArtist);


        for (WebElement web : elements) {
            Artist artist1 = new Artist();

            WebElement imageElement = web.findElement(By.cssSelector("div.dumy > a > img"));
            artist1.setImage(imageElement.getAttribute("src"));

            String artistId = web.findElement(By.cssSelector("div.dumy > a")).getAttribute("onclick");
            //slicing -> "fnViewAlbumLayer('80523187');return false;" -> 80523187
            int startIndex = artistId.indexOf("'") + 1; // 시작 따옴표 이후 인덱스
            int endIndex = artistId.lastIndexOf("'"); // 마지막 따옴표 인덱스
            String result = artistId.substring(startIndex, endIndex);
            artist1.setArtistId(result);

            WebElement infoElement = web.findElement(By.cssSelector("div.dumy > p.cont"));
            String findArtist = infoElement.findElement(By.className("artist")).getText();
            artist1.setArtist(findArtist);

            String country = infoElement.findElement(By.className("nationality")).getText();
            artist1.setCountry(country);

            String gender = infoElement.findElement(By.className("type")).getText();
            artist1.setGender(gender);

            artists.add(artist1);

            System.out.println("album = " + artist1);
        }
        System.out.println("artists.size() = " + artists.size());

        driver.quit();
    }

    public void ArtistDetail(String ArtistId) throws IOException {

        String url = "https://www.genie.co.kr/detail/artistSong?xxnm=" + ArtistId;

        WebDriver driver = WebDriverUtil.getChromeDriver(resourceLoader);
        driver.get(url);
        List<Song> songs = new ArrayList<>();

        //page number
        WebElement element1 = driver.findElement(By.className("page-nav"));
        List<WebElement> pageElement = element1.findElements(By.cssSelector("div.page-nav > a"));
        pageElement.remove(pageElement.size() - 1);
        pageElement.remove(0);

        pageElement.get(2).click();
        //
        String totalSongCount = driver.findElement(By.cssSelector("#songlist-box > div.tit-box > span > strong")).getText();
        System.out.println("totalSongCount = " + totalSongCount);

        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("list-wrap")));


        List<WebElement> elements = element.findElements(By.cssSelector("tr.list"));//tr태그의 list클래스

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
