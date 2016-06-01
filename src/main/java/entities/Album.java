package entities;

public class Album {
    public enum Genre { ROCK, JAZZ, POP }

    private String albumName;
    private String artist;
    private Genre genre;
    private int yearOfIssue;

    public Album(String albumName, String artist, Genre genre, int yearOfIssue) {
        this.albumName = albumName;
        this.artist = artist;
        this.genre = genre;
        this.yearOfIssue = yearOfIssue;
    }

    public String getAlbumName() {
        return albumName;
    }
    public String getArtist() {
        return artist;
    }
    public Genre getGenre() {
        return genre;
    }
    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void printAlbumInfo() {
        System.out.println(albumName + " by " + artist + " issued in " + yearOfIssue);
    }

    public String toString() {
        return artist + " - " + albumName;

    }
}