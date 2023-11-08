package com.example.music.entity;

public class Album {
    String album;

    String artist;
    String since;

    String image;

    String album_id;

    String download_Link;


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

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getDownload_Link() {
        return download_Link;
    }

    public void setDownload_Link(String download_Link) {
        this.download_Link = download_Link;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", since='" + since + '\'' +
                ", image='" + image + '\'' +
                ", album_id='" + album_id + '\'' +
                ", download_Link='" + download_Link + '\'' +
                '}';
    }
}
