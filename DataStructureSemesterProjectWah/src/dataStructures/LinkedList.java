/**
 * Ezekiel Hne Wah ewah1@dmacc.com
 * CIS171 Tuesday Afternoon
 * Oct 24, 2024 
 */
package dataStructures;

import models.Event;

/**
 * A custom implementation of a linked list to store events.
 * It provides methods to add, remove, get events, and check the size of the list.
 */
public class LinkedList {
    private Node head;  // Head of the list
    private int size;   // Tracks the size of the list for efficiency

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;  // Initially the list is empty
    }

    /**
     * Adds an event to the end of the list.
     *
     * @param event The event to be added to the list
     */
    public void add(Event event) {
        Node newNode = new Node(event);  // Create a new node
        if (head == null) {
            head = newNode;  // If the list is empty, the new node becomes the head
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;  // Traverse to the last node
            }
            current.next = newNode;  // Set the new node at the end
        }
        size++;  // Increment the size of the list
    }

    /**
     * Retrieves the event at the specified index.
     *
     * @param index The index of the event to retrieve
     * @return The event at the specified index
     */
    public Event get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;  // Traverse to the desired node
        }
        return current.event;  // Return the event at the specified index
    }

    /**
     * Removes the event at the specified index.
     *
     * @param index The index of the event to remove
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        if (index == 0) {
            head = head.next;  // If the head is being removed, set the next node as the new head
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;  // Traverse to the node just before the one to be removed
            }
            current.next = current.next.next;  // Bypass the node to be removed
        }
        size--;  // Decrement the size of the list
    }

    /**
     * Returns the size of the list.
     *
     * @return The number of events in the list
     */
    public int size() {
        return size;  // Return the tracked size of the list
    }

	/**
	 * @param i
	 * @param updatedEvent
	 */
	public void set(int i, Event updatedEvent) {
		// TODO Auto-generated method stub
		
	}
}
