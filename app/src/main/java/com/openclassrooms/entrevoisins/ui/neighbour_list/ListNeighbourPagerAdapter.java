package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    // Connect Fragment & View Pager
    @Override
    public Fragment getItem(int position) {
        if(position==0){
        return NeighbourFragment.newInstance();
        } else {
            return FavoriteFragment.newFavInstance();
        }
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
                return 2;
    }
}