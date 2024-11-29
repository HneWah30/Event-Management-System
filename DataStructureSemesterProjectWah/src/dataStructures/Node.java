/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 25, 2024
 */
package dataStructures;

import models.Event;

/**
 * A helper class for creating nodes in a linked list.
 * Each node stores an event and a reference to the next node in the list.
 */
class Node {
    Event event; // The event stored in this node
    Node next;   // A reference to the next node in the linked list

    /**
     * Constructs a Node with the specified event.
     *
     * @param event the event to be stored in the node
     */
    Node(Event event) {
        this.event = event; // Assign the event to this node
        this.next = null;   // Initialize the next reference to null (end of list)
    }
}
