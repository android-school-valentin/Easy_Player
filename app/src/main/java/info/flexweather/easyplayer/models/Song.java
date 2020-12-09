package info.flexweather.easyplayer.models;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

public class Song implements Parcelable {

    public long id;
    public String title;
    public long albumId;
    public String albumName;
    public long artistId;
    public String artistName;
    public long duration;
    public int trackNumber;
    public String path;

    public static final String[] DEFAULT_PROJECTION = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.DATA
    };

    public static final String DEFAULT_SORT_ORDER = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;

    public static final Uri SONG_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel parcel) {
            long id = parcel.readLong();
            String title = parcel.readString();
            long albumId = parcel.readLong();
            String albumName = parcel.readString();
            long artistId = parcel.readLong();
            String artistName = parcel.readString();
            long duration = parcel.readLong();
            int trackNumber = parcel.readInt();
            String path = parcel.readString();
            return new Song(id, title, albumId, albumName, artistId, artistName, duration, trackNumber, path);
        }

        @Override
        public Song[] newArray(int i) {
            return new Song[i];
        }
    };

    public Song() {
        this.id = -1;
        this.title = "";
        this.albumId = -1;
        this.albumName = "";
        this.artistId = -1;
        this.artistName = "";
        this.duration = -1;
        this.trackNumber = -1;
        this.path = "";
    }

    private Song(long id, String title, long albumId, String albumName, long artistId, String artistName, long duration, int trackNumber, String path) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistName = artistName;
        this.duration = duration;
        this.trackNumber = trackNumber;
        this.path = path;
    }

    public void populate(Cursor cursor) {
        this.id = cursor.getLong(0);
        this.title = cursor.getString(1);
        this.albumId = cursor.getLong(2);
        this.albumName = cursor.getString(3);
        this.artistId = cursor.getLong(4);
        this.artistName = cursor.getString(5);
        this.duration = cursor.getLong(6);
        this.trackNumber = cursor.getInt(7);
        this.path = cursor.getString(8);
    }

    public Uri getUri() {
        return Uri.parse(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeLong(albumId);
        parcel.writeString(albumName);
        parcel.writeLong(artistId);
        parcel.writeString(artistName);
        parcel.writeLong(duration);
        parcel.writeInt(trackNumber);
        parcel.writeString(path);
    }
}
