package lambda;

import entities.Album;

public class JazzAlbumIssuedInYearsRangePredicate implements CheckAlbum {

    @Override
    public boolean test(Album album) {
        return album.getYearOfIssue() > 1975 && album.getYearOfIssue() < 2000
            && album.getGenre() == Album.Genre.JAZZ;
    }

}
