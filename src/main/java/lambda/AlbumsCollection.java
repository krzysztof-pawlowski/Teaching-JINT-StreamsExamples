package lambda;

import entities.Album;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class AlbumsCollection {

    private static List<Album> albums;

    private static void initAlbumsList() {
        Album album1 = new Album("Celebration Day", "Led Zeppelin", Album.Genre.ROCK, 2013);
        Album album2 = new Album("A Night At The Opera", "Queen", Album.Genre.ROCK, 1975);
        Album album3 = new Album("A Day At The Races", "Queen", Album.Genre.ROCK, 1976);
        Album album4 = new Album("Piano", "Leszek Mozdzer", Album.Genre.JAZZ, 2004);
        Album album5 = new Album("Doo-bop", "Miles Davis", Album.Genre.JAZZ, 1992);

        albums = Arrays.asList(album1, album2, album3, album4, album5);
    }



    private static void printAlbumsInfosFuncInterface(List<Album> albumsList, CheckAlbum predicate) {
        for (Album album : albumsList) {
            if (predicate.test(album)) {
                album.printAlbumInfo();
            }
        }
    }

    private static void printAlbumsInfosPredicate(List<Album> albumsList, Predicate<Album> predicate) {
        for (Album album : albumsList) {
            if (predicate.test(album)) {
                album.printAlbumInfo();
            }
        }
    }

    private static void printAlbumsInfosConsumer(List<Album> albumsList, Predicate<Album> predicate,
        Consumer<Album> consumer) {
        for (Album album : albumsList) {
            if (predicate.test(album)) {
                consumer.accept(album);
            }
        }
    }

    private static void printAlbumsInfosFunction(List<Album> albumsList, Predicate<Album> predicate,
        Function<Album, String> mapper, Consumer<String> consumer) {
        for (Album album : albumsList) {
            if (predicate.test(album)) {
                String data = mapper.apply(album);
                consumer.accept(data);
            }
        }
    }

    public static void main(String[] args) {

        initAlbumsList();

        // Albums issued after 1975 - print info
        System.out.println("--- Example 1 ---");

        for (Album album : albums) {
            if (album.getYearOfIssue() > 1975) {
                album.printAlbumInfo();
            }
        }

        // Jazz albums issued after 1975 but before 2000 - print info with "if" stmt
        System.out.println("--- Example 2 ---");

        for (Album album : albums) {
            if (album.getYearOfIssue() > 1975 && album.getYearOfIssue() < 2000
                && album.getGenre() == Album.Genre.JAZZ) {
                album.printAlbumInfo();
            }
        }

        // Jazz albums issued after 1975 but before 2000 - print info with predicate
        System.out.println("--- Example 3 ---");

        printAlbumsInfosFuncInterface(albums, new JazzAlbumIssuedInYearsRangePredicate());

        // Jazz albums issued after 1975 but before 2000 - print info with anonymous class predicate
        System.out.println("--- Example 4 ---");

        printAlbumsInfosFuncInterface(albums, new CheckAlbum() {

            @Override
            public boolean test(Album album) {
                return album.getYearOfIssue() > 1975 && album.getYearOfIssue() < 2000
                    && album.getGenre() == Album.Genre.JAZZ;
            }
        });

        // Jazz albums issued after 1975 but before 2000 - print info with lambda expression
        System.out.println("--- Example 5 ---");

        printAlbumsInfosFuncInterface(albums, (Album album) -> album.getYearOfIssue() > 1975
            && album.getYearOfIssue() < 2000
            && album.getGenre() == Album.Genre.JAZZ);

        // Jazz albums issued after 1975 but before 2000 - print info with lambda expression in var
        System.out.println("--- Example 6 ---");

        CheckAlbum checkAlbum = (Album album) -> album.getYearOfIssue() > 1975
            && album.getYearOfIssue() < 2000
            && album.getGenre() == Album.Genre.JAZZ;
        printAlbumsInfosFuncInterface(albums, checkAlbum);

        // Jazz albums issued after 1975 but before 2000 - print info with lambda expression and
        // standard predicate
        System.out.println("--- Example 7 ---");

        printAlbumsInfosPredicate(albums, (Album album) -> album.getYearOfIssue() > 1975
            && album.getYearOfIssue() < 2000
            && album.getGenre() == Album.Genre.JAZZ);

        // Jazz albums issued after 1975 but before 2000 - print using toString() method
        // with lambda expression and standard consumer
        System.out.println("--- Example 8 ---");

        printAlbumsInfosConsumer(albums,
            (Album album) -> album.getYearOfIssue() > 1975
                && album.getYearOfIssue() < 2000
                && album.getGenre() == Album.Genre.JAZZ,
            (Album album) -> System.out.println(album));

        // Jazz albums issued after 1975 but before 2000 - print capitalized album name
        // with lambda expression and standard consumer and standard function
        System.out.println("--- Example 9 ---");

        printAlbumsInfosFunction(albums,
            (Album album) -> album.getYearOfIssue() > 1975
                && album.getYearOfIssue() < 2000
                && album.getGenre() == Album.Genre.JAZZ,
            (Album album) -> album.getAlbumName().toUpperCase(),
            System.out::println);

    }

}
