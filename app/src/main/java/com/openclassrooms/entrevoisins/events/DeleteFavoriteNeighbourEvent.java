package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteFavoriteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour favNeighbour;
    public int fragPosition;

    /**
     * Constructor.
     * @param favNeighbour
     */
    public DeleteFavoriteNeighbourEvent(Neighbour favNeighbour, int fragPosition) {
        this.favNeighbour = favNeighbour;
        this.fragPosition=fragPosition;
    }
}
