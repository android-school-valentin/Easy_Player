package info.flexweather.easyplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class Playlist {

    public long id;
    public String name;
    public int count;

    public static final String[] DEFAULT_PROJECTION = {
            MediaStore.Audio.Playlists._ID,
            MediaStore.Audio.Playlists.NAME,
    };



    public static final String DEFAULT_SORT_ORDER = MediaStore.Audio.Playlists.DEFAULT_SORT_ORDER;

    public static final Uri PLAYLIST_URI = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;

    public Playlist() {
        this.id = -1;
        this.name = "";
        this.count = -1;
    }

    public void populate(Cursor cursor) {
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
    }

}
