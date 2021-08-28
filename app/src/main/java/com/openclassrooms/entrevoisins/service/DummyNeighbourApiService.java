package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.Favorites;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favoritesNeighbours = DummyNeighbourGenerator.generateFavoritesNeighbours();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        return favoritesNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void deleteFavoritesNeighbours(Neighbour neighbour) {
        favoritesNeighbours.remove(neighbour);

    }
    /**
     * Get position of a Neighbour.
     * @return {position}
     */
    @Override
    public Neighbour getPositionOfNeighbour(int position) {
        return neighbours.get(position);
    }
    public Neighbour getPositionOfFavoriteNeighbour(int position) {
        return favoritesNeighbours.get(position);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void createFavoritesNeighbours(Neighbour neighbour) {
        favoritesNeighbours.add(neighbour);

    }

}
