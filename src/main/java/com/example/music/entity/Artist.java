package com.example.music.entity;

public class Artist {
    private String artist;
    private String gender;
    private String country;

    private String artistId;
    private String image;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artist='" + artist + '\'' +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", artistId='" + artistId + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
