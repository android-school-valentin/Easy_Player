package info.flexweather.easyplayer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.adapters.SongAdapter;
import info.flexweather.easyplayer.dataloaders.AlbumLoader;
import info.flexweather.easyplayer.models.Album;
import info.flexweather.easyplayer.models.Song;
import info.flexweather.easyplayer.util.Utils;


public class AlbumDetailsFragment extends Fragment {

    public static final String KEY_ALBUM_ID = "album_id";
    private Album album;
    private long albumId;

    CollapsingToolbarLayout collapsingToolbar;
    private ImageView albumDetailsBigArt;
    private ImageView albumDetailsSmallArt;
    private TextView albumDetailsAlbumName;
    private TextView albumDetailsAlbumDetails;
    private RecyclerView albumDetailsRecycler;

    public AlbumDetailsFragment() {
        // Required empty public constructor
    }

    public static AlbumDetailsFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putLong(KEY_ALBUM_ID, id);
        AlbumDetailsFragment fragment = new AlbumDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumId = getArguments().getLong(KEY_ALBUM_ID);
        album = new AlbumLoader().getAlbumById(getActivity(), albumId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_details, container, false);
        collapsingToolbar = view.findViewById(R.id.album_details_collapsing_toolbar);
        albumDetailsBigArt = view.findViewById(R.id.album_details_big_art);
        albumDetailsSmallArt = view.findViewById(R.id.album_details_small_art);
        albumDetailsAlbumName = view.findViewById(R.id.album_details_album_name);
        albumDetailsAlbumDetails = view.findViewById(R.id.album_details_album_details);
        albumDetailsRecycler = view.findViewById(R.id.album_details_recycler);
        albumDetailsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        albumDetailsRecycler.setHasFixedSize(true);
        setSongList();
        setDetails();
        return view;
    }

    public void setSongList() {
        List<Song> songList = AlbumLoader.getSongsFromAlbum(getActivity(), albumId);
        SongAdapter adapter = new SongAdapter(songList, SongAdapter.VIEW_HOLDER_ALBUM_DETAILS_SONG);
        albumDetailsRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        albumDetailsRecycler.setAdapter(adapter);
    }

    //TODO wrap boilerplate | fix with placeholders
    public void setDetails() {
        collapsingToolbar.setTitle(album.albumName);
        albumDetailsAlbumName.setText(album.albumName);
        albumDetailsAlbumDetails.setText(album.artistName + " " + album.songNumber + " " + album.year);
        ImageLoader.getInstance().displayImage(
                Utils.getAlbumImageUri(album.id).toString(),
                albumDetailsBigArt,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.ic_note_48)
                        .resetViewBeforeLoading(true).build());

        ImageLoader.getInstance().displayImage(
                Utils.getAlbumImageUri(album.id).toString(),
                albumDetailsSmallArt,
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .showImageOnLoading(R.drawable.ic_note_48)
                        .resetViewBeforeLoading(true).build());
    }

}