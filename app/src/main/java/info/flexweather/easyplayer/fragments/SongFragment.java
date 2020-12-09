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
import info.flexweather.easyplayer.adapters.SongAdapter;
import info.flexweather.easyplayer.dataloaders.SongLoader;

public class SongFragment extends Fragment {

    private SongAdapter adapter;
    private RecyclerView recyclerView;

    public SongFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        recyclerView = view.findViewById(R.id.song_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        new LoadSongsAsyncTask().execute();
        return view;
    }

     class LoadSongsAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            if (getActivity() != null) {
                adapter = new SongAdapter(new SongLoader().getAllSongs(getActivity()), SongAdapter.VIEW_HOLDER_SONG);
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