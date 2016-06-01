package streams;

import entities.Album;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionsOperations {

    private static List<Album> albums;

    private static void initAlbumsList() {
        Album album1 = new Album("Celebration Day", "Led Zeppelin", Album.Genre.ROCK, 2013);
        Album album2 = new Album("A Night At The Opera", "Queen", Album.Genre.ROCK, 1975);
        Album album3 = new Album("A Day At The Races", "Queen", Album.Genre.ROCK, 1976);
        Album album4 = new Album("Piano", "Leszek Mozdzer", Album.Genre.JAZZ, 2004);
        Album album5 = new Album("Doo-bop", "Miles Davis", Album.Genre.JAZZ, 1992);

        albums = Arrays.asList(album1, album2, album3, album4, album5);
    }

    private static void printCapitalizedAlbumName(List<Album> albums, Predicate<Album> predicate) {
        for (Album album : albums) {
            if (predicate.test(album)) {
                System.out.println(album.getAlbumName().toUpperCase());
            }
        }
    }

    public static void main(String[] args) {

        initAlbumsList();

        // get capitalized names of albums by Queen after 1975

        // approach with loop
        System.out.println("--- Example 1 ---");
        for (Album album : albums) {
            if (album.getArtist().equals("Queen")) {
                if (album.getYearOfIssue() > 1975) {
                    System.out.println(album.getAlbumName().toUpperCase());
                }
            }
        }

        // approach with lambda expression
        System.out.println("--- Example 2 ---");
        printCapitalizedAlbumName(albums,
            album -> (album.getArtist().equals("Queen") && (album.getYearOfIssue() > 1975))
        );

        // approach with streams
        System.out.println("--- Example 3 ---");
        albums.stream()
            .filter(album -> album.getArtist().equals("Queen"))
            .filter(album -> album.getYearOfIssue() > 1975)
            .map(album -> album.getAlbumName().toUpperCase())
            .forEach(System.out::println);

        // get albums by Queen

        // approach with loop
        System.out.println("--- Example 4 ---");
        List<Album> queenAlbumsLoop = new LinkedList<Album>();
        for (Album album : albums) {
            if (album.getArtist().equals("Queen")) {
                queenAlbumsLoop.add(album);
            }
        }

        // approach with streams
        System.out.println("--- Example 5 ---");
        albums.stream().filter(album -> album.getArtist().equals("Queen")).collect(Collectors.toList());

        // get all the albums names sorted by year of issue

        // approach with list sorting
        System.out.println("--- Example 6 ---");
        List<Album> albumsCopy = new LinkedList<Album>(albums);
        albumsCopy.sort(new Comparator<Album>() {

            @Override
            public int compare(Album a1, Album a2) {
                return a1.getYearOfIssue() - a2.getYearOfIssue();
            }
        });
        for (Album album : albumsCopy) {
            System.out.println(album.getAlbumName());
        }

        // approach with streams
        System.out.println("--- Example 7 ---");
        albums.stream()
            .sorted((album1, album2) -> album1.getYearOfIssue() - album2.getYearOfIssue())
            .map(album -> album.getAlbumName()).forEach(System.out::println);

    }

}
