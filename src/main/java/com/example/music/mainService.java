package com.example.music;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class mainService {

    public HashMap<String, String> main() throws IOException {
        //song까지 하고 song객체 만들었다. 각자 foreach문으로 한개씩 가져오기
        List<Song> songs = new ArrayList<>();

        String url = "https://www.genie.co.kr/search/searchSong?query=ETA&Coll=";
        HashMap<String, String> detailAlbumInfo = new HashMap<>();

        Document doc = Jsoup.connect(url).get();
        Element infozone = doc.selectFirst("div .music-list-wrap .music-list-wrap tbody");

        Elements list = infozone.select("tr");
        for (Element element : list) {
            Song song = new Song();
            System.out.println("element = " + element);
            song.setSong_id(element.attr("songid")); //song id

            Elements select = element.select("a.cover > img");
            song.setImage(select.attr("src"));

            //info
            Elements songInfo = element.select("td.info > a");
            song.setTitle(songInfo.attr("title"));

            Element select_artist = element.selectFirst("td.info > a.artist");
            song.setArtist(select_artist.toString());

            Element select_album = element.selectFirst("td.info > a.albumtitle");
            song.setAlbum(select_album.toString());

//            System.out.println("songInfo = " + songInfo.attr("title"));

            songs.add(song);
        }
        System.out.println("songs.size() = " + songs.size());

        return detailAlbumInfo;
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
    }
}
