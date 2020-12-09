package info.flexweather.easyplayer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.adapters.PlaylistAdapter;
import info.flexweather.easyplayer.dataloaders.PlaylistLoader;


public class PlaylistFragment extends Fragment {

    private RecyclerView recyclerView;
    private PlaylistAdapter adapter;

    public static PlaylistFragment newInstance() {
        PlaylistFragment fragment = new PlaylistFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);
        recyclerView = view.findViewById(R.id.playlist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        new LoadPlaylistsAsyncTask().execute();
        return view;
    }

    class LoadPlaylistsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (getActivity() != null) {
                adapter = new PlaylistAdapter(PlaylistLoader.getAllPlaylists(getActivity()));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (getActivity() != null) {
                recyclerView.setAdapter(adapter);
            }
        }
    }

}