package lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {

    private List<T> fruits;

    private float weight;

    public Box(T inFruits) {
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public float getWeight() {
        if (fruits.get(0) instanceof Apple) {
            weight = fruits.size() * ((float) ((Apple) fruits.get(0)).getWeight());
        } else if (fruits.get(0) instanceof Orange) {
            weight = fruits.size() * ((float) ((Orange) fruits.get(0)).getWeight());
        }
        return weight;
    }

    public float setWeight() {
        if (fruits.get(0) instanceof Apple) {
            weight = fruits.size() * ((float) ((Apple) fruits.get(0)).getWeight());
        } else if (fruits.get(0) instanceof Orange) {
            weight = fruits.size() * ((float) ((Orange) fruits.get(0)).getWeight());
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return weight == box.weight ? true : false;
    }

    public void empty(Box<T> box) {
        if (fruits.get(0).equals(box.fruits.get(0)) && fruits.get(0) instanceof Apple) {
            box.fruits.addAll(fruits);
            fruits.clear();
        }
    }

    public void addFruit(T fruit) {
        if (fruits.get(0).equals(fruit)) {
            this.fruits.add(fruit);
            setWeight();
        }
    }
}
