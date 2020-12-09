package info.flexweather.easyplayer.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.models.Song;
import info.flexweather.easyplayer.util.Utils;


public class SongAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Song> songList;
    private long[] idList;

    public static final int VIEW_HOLDER_SONG = 1;
    public static final int VIEW_HOLDER_ALBUM_DETAILS_SONG = 2;
    public static final int VIEW_HOLDER_ARTIST_DETAILS_SONG = 3;
    private int itemType;

    private long[] getIdList() {
        long[] idList = new long[getItemCount()];
        for (int i = 0; i < idList.length; i++) {
            idList[i] = songList.get(i).id;
        }
        return idList;
    }

    public SongAdapter(List<Song> songList, int itemType) {
        this.songList = songList;
        this.itemType = itemType;
        idList = getIdList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (itemType) {
            case VIEW_HOLDER_SONG:
                return new SongViewHolder(inflater.inflate(R.layout.song_item, parent, false));
            case VIEW_HOLDER_ALBUM_DETAILS_SONG:
                return new AlbumSongViewHolder(inflater.inflate(R.layout.album_song_item, parent, false));
            case VIEW_HOLDER_ARTIST_DETAILS_SONG:
                return new ArtistSongViewHolder(inflater.inflate(R.layout.artist_song_item, parent, false));
            default:
                return new SongViewHolder(inflater.inflate(R.layout.song_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO add more views | maybe switch
        Song song = songList.get(position);
        if (song != null) {
            if (holder instanceof SongViewHolder) {
                SongViewHolder songViewHolder = (SongViewHolder) holder;
                songViewHolder.songNameTextView.setText(song.title);
                songViewHolder.songArtistNameTextView.setText(song.artistName);
                ImageLoader.getInstance().displayImage(
                        Utils.getAlbumImageUri(song.albumId).toString(),
                        songViewHolder.songImageView,
                        new DisplayImageOptions.Builder()
                                .cacheInMemory(true)
                                .showImageOnLoading(R.drawable.ic_note_48)
                                .resetViewBeforeLoading(true).build());
                Log.d("Adapter", "onBindViewHolder: " + song.path);

            } else if (holder instanceof AlbumSongViewHolder) {
                AlbumSongViewHolder albumSongViewHolder = (AlbumSongViewHolder) holder;
                albumSongViewHolder.albumDetailsSongNumberTextView.setText(String.valueOf(song.trackNumber));
                albumSongViewHolder.albumDetailsSongName.setText(song.title);
                albumSongViewHolder.albumDetailsSongArtistName.setText(song.artistName);
                Log.d("Adapter", "onBindViewHolder: " + song.path);
            } else if (holder instanceof ArtistSongViewHolder) {
                ArtistSongViewHolder artistSongViewHolder = (ArtistSongViewHolder) holder;
                artistSongViewHolder.artistDetailsSongNumberTextView.setText(String.valueOf(song.trackNumber));
                artistSongViewHolder.artistDetailsSongName.setText(song.title);
                artistSongViewHolder.artistDetailsSongAlbumName.setText(song.albumName);
                Log.d("Adapter", "onBindViewHolder: " + song.path);
            }
        }


    }

    @Override
    public int getItemCount() {
        if (songList != null) {
            return songList.size();
        }
        return 0;
    }

    class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView songImageView;
        private TextView songNameTextView;
        private TextView songArtistNameTextView;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songImageView = itemView.findViewById(R.id.song_image);
            songNameTextView = itemView.findViewById(R.id.song_name);
            songArtistNameTextView = itemView.findViewById(R.id.song_artist_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    class AlbumSongViewHolder extends RecyclerView.ViewHolder {

        private TextView albumDetailsSongNumberTextView;
        private TextView albumDetailsSongName;
        private TextView albumDetailsSongArtistName;

        public AlbumSongViewHolder(@NonNull View itemView) {
            super(itemView);
            albumDetailsSongNumberTextView = itemView.findViewById(R.id.album_details_song_number);
            albumDetailsSongName = itemView.findViewById(R.id.album_details_song_name);
            albumDetailsSongArtistName = itemView.findViewById(R.id.album_details_song_artist_name);
        }
    }

    //TODO create unique layout
    class ArtistSongViewHolder extends RecyclerView.ViewHolder {
        private TextView artistDetailsSongNumberTextView;
        private TextView artistDetailsSongName;
        private TextView artistDetailsSongAlbumName;

        public ArtistSongViewHolder(@NonNull View itemView) {
            super(itemView);
            artistDetailsSongNumberTextView = itemView.findViewById(R.id.artist_details_song_number);
            artistDetailsSongName = itemView.findViewById(R.id.artist_details_song_name);
            artistDetailsSongAlbumName = itemView.findViewById(R.id.artist_details_song_album_name);
        }
    }

}
