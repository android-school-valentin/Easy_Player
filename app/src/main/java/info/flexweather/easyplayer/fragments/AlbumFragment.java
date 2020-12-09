package info.flexweather.easyplayer.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import info.flexweather.easyplayer.R;
import info.flexweather.easyplayer.adapters.AlbumAdapter;
import info.flexweather.easyplayer.dataloaders.AlbumLoader;
import info.flexweather.easyplayer.util.GridSpacingDecoration;


public class AlbumFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;

    public AlbumFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        recyclerView = view.findViewById(R.id.album_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 2));
        recyclerView.setHasFixedSize(true);
        new LoadAlbumsAsyncTask().execute();
        return view;
    }

    //TODO fix leaks
    class LoadAlbumsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (getActivity() != null) {
                adapter = new AlbumAdapter(new AlbumLoader().getAllAlbums(getActivity()), getActivity());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (getActivity() != null) {
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(new GridSpacingDecoration(2, 16, false));
            }
        }
    }

}