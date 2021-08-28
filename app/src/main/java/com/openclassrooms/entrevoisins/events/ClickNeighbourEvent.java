package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user click a Neighbour
 */
public class ClickNeighbourEvent {

    /**
     * Neighbour to Click
     */
    public Neighbour neighbour;
    public int position;

    /**
     * Constructor.
     * @param neighbour
     */
    public ClickNeighbourEvent(Neighbour neighbour, int position) {
        this.neighbour = neighbour;
        this.position = position;
    }
}