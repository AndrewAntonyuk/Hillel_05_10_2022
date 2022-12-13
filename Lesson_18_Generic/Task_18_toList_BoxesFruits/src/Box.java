import fruits.Fruit;

import java.util.ArrayList;
import java.util.Collection;

public class Box<T extends Fruit> {
    private final ArrayList<T> internalList = new ArrayList<>();

    public void add(T fruit) {
        internalList.add(fruit);
    }

    public void add(Collection<T> fruits) {
        if (fruits != null) {
            internalList.addAll(fruits);
        }
    }

    public float getWeight() {
        return internalList.size() > 0 ? internalList.get(0).getWeight() * internalList.size() : 0.0f;
    }

    public boolean compare(Box box) {
        return getWeight() == box.getWeight();
    }

    public void merge(Box<T> box) {
        if (box != null) {
            add(box.getInternalList());
            box.getInternalList().clear();
        }
    }

    //region Getters/Setters
    public ArrayList<T> getInternalList() {
        return internalList;
    }
    //endregion
}
