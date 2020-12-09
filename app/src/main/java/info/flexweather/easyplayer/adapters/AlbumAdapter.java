package info.flexweather.easyplayer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.fragments.AlbumDetailsFragment;
import info.flexweather.easyplayer.models.Album;
import info.flexweather.easyplayer.util.Utils;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albumList;
    private Context context;

    public AlbumAdapter(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        if (album != null) {
            holder.albumNameTextView.setText(album.albumName);
            holder.albumArtistNameTextView.setText(album.artistName);
            ImageLoader.getInstance().displayImage(
                    Utils.getAlbumImageUri(album.id).toString(),
                    holder.albumImageView,
                    new DisplayImageOptions.Builder()
                            .cacheInMemory(true)
                            .showImageOnLoading(R.drawable.ic_note_48)
                            .resetViewBeforeLoading(true).build());
        }
    }

    @Override
    public int getItemCount() {
        if (albumList != null) {
            return albumList.size();
        }
        return 0;
    }

    //TODO add more views
    class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView albumImageView;
        private TextView albumNameTextView;
        private TextView albumArtistNameTextView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumImageView = itemView.findViewById(R.id.album_image);
            albumNameTextView = itemView.findViewById(R.id.album_name);
            albumArtistNameTextView = itemView.findViewById(R.id.album_artist_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            long albumId = albumList.get(getAdapterPosition()).id;
            FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            Fragment fragment = AlbumDetailsFragment.newInstance(albumId);
            transaction.hide(manager.findFragmentById(R.id.main_container));
            transaction.add(R.id.main_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
