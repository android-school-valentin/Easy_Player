package info.flexweather.easyplayer.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.adapters.FragmentAdapter;
import info.flexweather.easyplayer.dataloaders.SongLoader;
import info.flexweather.easyplayer.models.Song;


public class MainFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        toolbar =  view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar); //пиздец блять????
        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.view_pager);
        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        List<Song> songList = new SongLoader().getAllSongs(getActivity());
        for(Song song : songList) {
            Log.i("TAG", "initUi: " + song.title);
        }
        return view;
    }

    private void setViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new SongFragment(), "Songs");
        adapter.addFragment(new PlaylistFragment(), "Playlists");
        adapter.addFragment(new AlbumFragment(), "Albums");
        adapter.addFragment(new ArtistFragment(), "Artists");
        viewPager.setAdapter(adapter);
    }

}