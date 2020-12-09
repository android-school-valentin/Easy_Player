package info.flexweather.easyplayer.models;

import android.net.Uri;
import android.provider.MediaStore;

public class Album {

    public final long id;
    public final String albumName;
    public final long artistId;
    public final String artistName;
    public final int songNumber;
    public final int year;

    public static final String[] DEFAULT_PROJECTION = {
            MediaStore.Audio.AlbumColumns.ALBUM_ID,
            MediaStore.Audio.AlbumColumns.ALBUM,
            MediaStore.Audio.AlbumColumns.ARTIST_ID,
            MediaStore.Audio.AlbumColumns.ARTIST,
            MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS,
            MediaStore.Audio.AlbumColumns.LAST_YEAR,
    };

    public static final String DEFAULT_SORT_ORDER = MediaStore.Audio.Albums.DEFAULT_SORT_ORDER;

    public static final Uri ALBUM_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

    public Album() {
        this.id = -1;
        this.albumName = "";
        this.artistId = -1;
        this.artistName = "";
        this.songNumber = 0;
        this.year = 0;
    }

    public Album(long id, String albumName, long artistId, String artistName, int songNumber, int year) {
        this.id = id;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.songNumber = songNumber;
        this.year = year;
    }
}
