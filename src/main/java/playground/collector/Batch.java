package playground.collector;

import java.util.ArrayList;
import java.util.List;

public class Batch<T> {

    private List<T> elements = new ArrayList<>();
    private int maxSize;

    public Batch(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * returns true if the element was successfully added to batch.
     * returns false if the batch max size is reached.
     * @param element
     * @return
     */
    public boolean tryAdd(T element) {
        if (elements.size() < this.maxSize) {
            elements.add(element);
            return true;
        }
        return false;
    }

    public List<T> getAllElements() {
        return elements;
    }

    public int getSize() {
        return this.elements.size();
    }
}
