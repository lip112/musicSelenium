package com.example.music.entity;

public class Song {
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
                ", album='" + album + '\'' +
                ", Song_id='" + Song_id + '\'' +
                ", download_Link='" + download_Link + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
