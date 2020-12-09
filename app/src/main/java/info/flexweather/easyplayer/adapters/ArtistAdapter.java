package info.flexweather.easyplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.fragments.AlbumDetailsFragment;
import info.flexweather.easyplayer.fragments.ArtistDetailsFragment;
import info.flexweather.easyplayer.models.Artist;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    private List<Artist> artistList;
    private Context context;

    public ArtistAdapter(List<Artist> artistList, Context context) {
        this.artistList = artistList;
        this.context = context;
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false));
    }

    //TODO fix with placeholders
    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistViewHolder holder, int position) {
        Artist artist = artistList.get(position);
        if (artist != null) {
            holder.artistNameTextView.setText(artist.artistName);
            holder.artistSongAlbumCount.setText(artist.albumCount + " | " + artist.songCount);
        }
    }

    @Override
    public int getItemCount() {
        if (artistList != null) {
            return artistList.size();
        }
        return 0;
    }

    //TODO implement artist image
    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView artistNameTextView;
        private TextView artistSongAlbumCount;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            artistNameTextView = itemView.findViewById(R.id.artist_name);
            artistSongAlbumCount = itemView.findViewById(R.id.artist_song_album_count);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String key = artistList.get(getAdapterPosition()).id;
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            Fragment fragment = ArtistDetailsFragment.newInstance(key);
            transaction.hide(manager.findFragmentById(R.id.main_container));
            transaction.add(R.id.main_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
