package info.flexweather.easyplayer.util;

import android.content.ContentUris;
import android.net.Uri;

public class Utils {

    public static Uri getAlbumImageUri(long id) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), id);
    }
}
