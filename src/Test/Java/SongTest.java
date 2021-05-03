import server.Model.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Emanuel Dabadie
 */
public class SongTest {

    Song aSong = new Song("titreduson", "artisteduson", 20,"content.wav", "jazz");

    @Test
    void SongTitleTest() {
        Assertions.assertEquals("titreduson", aSong.getTitle());
    }

    @Test
    void SongArtistTest() {
        Assertions.assertEquals("artisteduson", aSong.getArtist());
    }

    @Test
    void SongGenreTest() {
        Assertions.assertEquals("jazz", aSong.getGenre());
    }

}
