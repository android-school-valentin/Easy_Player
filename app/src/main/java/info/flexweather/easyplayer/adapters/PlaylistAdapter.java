package info.flexweather.easyplayer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.models.Playlist;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder> {

    private ArrayList<Playlist> playlists;

    public PlaylistAdapter(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public PlaylistAdapter.PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlaylistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.PlaylistViewHolder holder, int position) {
        holder.playlistNameTextView.setText(playlists.get(position).name);
        holder.playlistSongCountTextView.setText(playlists.get(position).name);
    }

    @Override
    public int getItemCount() {
        if (playlists != null) {
            return playlists.size();
        }
        return 0;
    }

    class PlaylistViewHolder extends RecyclerView.ViewHolder {

        private ImageView playlistImageView;
        private TextView playlistNameTextView;
        private TextView playlistSongCountTextView;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistImageView = itemView.findViewById(R.id.playlist_image);
            playlistNameTextView = itemView.findViewById(R.id.playlist_name);
            playlistSongCountTextView = itemView.findViewById(R.id.playlist_song_count);
        }
    }

}
