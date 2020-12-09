package info.flexweather.easyplayer.dataloaders;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import info.flexweather.easyplayer.models.Album;
import info.flexweather.easyplayer.models.Song;

public class AlbumLoader {

    public List<Album> getAllAlbums(Context context) {
        List<Album> albumList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Album.ALBUM_URI, Album.DEFAULT_PROJECTION,
                null, null, Album.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                albumList.add(new Album(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getLong(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                ));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return albumList;
    }

    public static Album getAlbumById(Context context, long id) {
        Cursor cursor = context.getContentResolver().query(Album.ALBUM_URI, Album.DEFAULT_PROJECTION,
                "_id=?", new String[]{String.valueOf(id)}, null);
        Album album = null;
        if (cursor != null && cursor.moveToFirst()) {
            album = new Album(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getLong(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5));
        }

        if (cursor != null) {
            cursor.close();
        }

        return album;
    }

    public static List<Song> getSongsFromAlbum(Context context, long id) {
        List<Song> songList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Song.SONG_URI, Song.DEFAULT_PROJECTION,
                MediaStore.Audio.Media.ALBUM_ID + "=?", new String[]{String.valueOf(id)}, null);
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
