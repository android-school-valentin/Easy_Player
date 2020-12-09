package info.flexweather.easyplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Artist {

    public String id;
    public String artistName;
    public int albumCount;
    public int songCount;

    public static final String[] DEFAULT_PROJECTION = {
            MediaStore.Audio.ArtistColumns.ARTIST_KEY,
            MediaStore.Audio.ArtistColumns.ARTIST,
            MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS,
            MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS
    };

    public static final String DEFAULT_SORT_ORDER = MediaStore.Audio.Artists.DEFAULT_SORT_ORDER;

    public static final Uri ARTIST_URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    public Artist() {
        this.id = "";
        this.artistName = "";
        this.albumCount = 0;
        this.songCount = 0;
    }

    public void populate(Cursor cursor) {
        this.id = cursor.getString(0);
        this.artistName = cursor.getString(1);
        this.albumCount = cursor.getInt(2);
        this.songCount = cursor.getInt(3);
    }
}
