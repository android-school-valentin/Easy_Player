package info.flexweather.easyplayer.dataloaders;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;

import info.flexweather.easyplayer.models.Song;

public class SongLoader {

    public static Song getSongById(Context context, long songId) {
        Song song = new Song();
        String selection = MediaStore.Audio.Media._ID + "=?";
        Cursor cursor = context.getContentResolver().query(Song.SONG_URI, Song.DEFAULT_PROJECTION, selection, new String[]{String.valueOf(songId)}, Song.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            song.populate(cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
        return song;
    }

    public static ArrayList<Song> getAllSongs(Context context) {
        ArrayList<Song> songList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Song.SONG_URI, Song.DEFAULT_PROJECTION, null, null, Song.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Song song = new Song();
                song.populate(cursor);
                songList.add(song);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return songList;
    }

}
