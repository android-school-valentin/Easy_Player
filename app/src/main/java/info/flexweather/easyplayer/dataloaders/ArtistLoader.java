package info.flexweather.easyplayer.dataloaders;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import info.flexweather.easyplayer.models.Artist;
import info.flexweather.easyplayer.models.Song;

public class ArtistLoader {

    public static ArrayList<Artist> getAllArtists(Context context) {
        ArrayList<Artist> artistList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Artist.ARTIST_URI, Artist.DEFAULT_PROJECTION,
                null, null, Artist.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Artist artist = new Artist();
                artist.populate(cursor);
                artistList.add(artist);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return artistList;
    }

    public static Artist getArtistByKey(Context context, String key) {
        Artist artist = new Artist();
        String selection = MediaStore.Audio.Media.ARTIST_KEY + "=?";
        Cursor cursor = context.getContentResolver().query(Artist.ARTIST_URI, Artist.DEFAULT_PROJECTION, selection, new String[]{key}, Artist.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.moveToFirst()) {
            artist.populate(cursor);
        }
        if (cursor != null) {
            cursor.close();
        }
        return artist;
    }

    public static List<Song> getAllArtistSongs(Context context, String key) {
        List<Song> songList = new ArrayList<>();
        String selection = MediaStore.Audio.Media.ARTIST_KEY + "=?";
        Cursor cursor = context.getContentResolver().query(Song.SONG_URI, Song.DEFAULT_PROJECTION, selection, new String[]{key}, Song.DEFAULT_SORT_ORDER);
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
