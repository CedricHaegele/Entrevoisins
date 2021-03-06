package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class FavoriteFragment extends Fragment {

    private NeighbourApiService mApiService;
    List<Neighbour> favNeighbours;
    private RecyclerView mRecyclerView;



    public static FavoriteFragment newFavInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService=DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }


    private void initFavList() {
        favNeighbours = mApiService.getFavoritesNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(favNeighbours,1));
    }

    @Subscribe
    public void onDeleteFavoriteNeighbour(DeleteFavoriteNeighbourEvent event) {
        if (event.fragPosition == 1) {
            mApiService.deleteFavoritesNeighbours(event.favNeighbour);
        if (mApiService.getNeighbours().contains(event.favNeighbour)){
            mApiService.deleteNeighbour(event.favNeighbour);
        }
        initFavList();
        }}

    @Override
    public void onResume() {
        super.onResume();
        initFavList();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}

