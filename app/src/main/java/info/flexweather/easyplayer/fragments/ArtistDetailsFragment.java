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

import java.util.List;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.adapters.SongAdapter;
import info.flexweather.easyplayer.dataloaders.ArtistLoader;
import info.flexweather.easyplayer.dataloaders.SongLoader;
import info.flexweather.easyplayer.models.Artist;
import info.flexweather.easyplayer.models.Song;

public class ArtistDetailsFragment extends Fragment {

    public static final String KEY_ARTIST_KEY = "artist_key";
    private Artist artist;
    private String artistKey;

    CollapsingToolbarLayout collapsingToolbar;
    private ImageView artistDetailsBigArt;
    private ImageView artistDetailsSmallArt;
    private TextView artistDetailsArtistName;
    private TextView artistDetailsArtistDetails;
    private RecyclerView artistDetailsRecycler;

    public ArtistDetailsFragment() {
        // Required empty public constructor
    }

    public static ArtistDetailsFragment newInstance(String artistKey) {
        Bundle args = new Bundle();
        args.putString(KEY_ARTIST_KEY, artistKey);
        ArtistDetailsFragment fragment = new ArtistDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artistKey = getArguments().getString(KEY_ARTIST_KEY);
        artist = ArtistLoader.getArtistByKey(getActivity(), artistKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_details, container, false);
        collapsingToolbar = view.findViewById(R.id.artist_details_collapsing_toolbar);
        artistDetailsBigArt = view.findViewById(R.id.artist_details_big_art);
        artistDetailsSmallArt = view.findViewById(R.id.artist_details_small_art);
        artistDetailsArtistName = view.findViewById(R.id.artist_details_artist_name);
        artistDetailsArtistDetails = view.findViewById(R.id.artist_details_artist_details);
        artistDetailsRecycler = view.findViewById(R.id.artist_details_recycler);
        artistDetailsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        artistDetailsRecycler.setHasFixedSize(true);
        setSongList();
        setDetails();
        return view;
    }

    public void setSongList() {
        List<Song> songList = ArtistLoader.getAllArtistSongs(getActivity(), artistKey);
        SongAdapter adapter = new SongAdapter(songList, SongAdapter.VIEW_HOLDER_ARTIST_DETAILS_SONG);
        artistDetailsRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        artistDetailsRecycler.setAdapter(adapter);
    }

    //TODO fix with placeholders
    //TODO artist image load
    public void setDetails() {
        collapsingToolbar.setTitle(artist.artistName);
        artistDetailsArtistName.setText(artist.artistName);
        artistDetailsArtistDetails.setText(artist.albumCount + " | " + artist.songCount);
    }

}