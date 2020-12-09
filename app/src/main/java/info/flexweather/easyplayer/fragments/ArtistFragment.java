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
import info.flexweather.easyplayer.adapters.ArtistAdapter;
import info.flexweather.easyplayer.dataloaders.ArtistLoader;

public class ArtistFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArtistAdapter adapter;

    public ArtistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);
        recyclerView = view.findViewById(R.id.artist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        new LoadArtistsAsyncTask().execute();
        return view;
    }

    //TODO fix leaks
    class LoadArtistsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (getActivity() != null) {
                adapter = new ArtistAdapter(ArtistLoader.getAllArtists(getActivity()), getActivity());
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