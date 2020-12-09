package info.flexweather.easyplayer.dataloaders;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

import info.flexweather.easyplayer.models.Playlist;

public class PlaylistLoader {

    public static ArrayList<Playlist> getAllPlaylists(Context context) {
        ArrayList<Playlist> playlists = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Playlist.PLAYLIST_URI, Playlist.DEFAULT_PROJECTION,
                null, null, Playlist.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Playlist playlist = new Playlist();
                playlist.populate(cursor);
                playlists.add(playlist);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return playlists;
    }

    public static Playlist getPlaylistByName(Context context, String name) {
        Playlist playlist = new Playlist();
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
                Playlist.DEFAULT_PROJECTION,
                MediaStore.Audio.Playlists.NAME + "=?",
                new String[] { name }, null);
        if (cursor != null && cursor.moveToFirst()) {
            playlist.populate(cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
        return playlist;
    }

    public static void createPlaylist(Context context, String name) {
        ContentValues values = new ContentValues(1);
        values.put(MediaStore.Audio.Playlists.NAME, name);
        context.getContentResolver().insert(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, values);
    }

    public static void deletePlaylist(Context context, long id) {
        Uri uri = ContentUris.withAppendedId(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, id);
        context.getContentResolver().delete(uri, null, null);
    }

}
