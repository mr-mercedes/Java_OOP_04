package ru.geekbrains.oop04.homework;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Generics {
    public static abstract class Fruit{
        public abstract float weight();
    }
    public static class Apple extends Fruit {

        @Override
        public float weight() {
            return 1.0f;
        }
    }
    public static class Orange extends Fruit{

        @Override
        public float weight() {
            return 1.5f;
        }
    }

    public static class Box<T extends Fruit> implements Iterable<Fruit> {
        private final List<T> box = new ArrayList<>();
        private final int size;

        public Box(int size) {
            this.size = size;
        }

        public void add(Fruit fruit){
            box.add((T) fruit);
        }
        public float getWeight(){
            float weight = 0;
            for (T t : box) {
                weight += t.weight();
            }
            return weight;
        }


        public  boolean compare(Box<? extends Fruit> comp){
            return this.getWeight() == comp.getWeight();
        }


        @Override
        public Iterator<Fruit> iterator() {
            return new Iterator<Fruit>() {
                private int counter = 0;
                @Override
                public boolean hasNext() {
                    return counter < size - 1;
                }

                @Override
                public Fruit next() {
                    return box.get(counter++);
                }
            };
        }

    }
    public static <T extends Fruit> void moveFruits(Box<? extends Fruit> source, Box<? extends Fruit> dest) {
        float w = 0;
        float focus = source.getWeight();
        for (Fruit fr : source) {
            w += fr.weight();
            if (w <= focus) {
                dest.add(fr);
            }
        }
    }

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>(5);
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        Box<Orange> orangeBox = new Box<>(7);
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        Box<Fruit> fruitBox = new Box<>(8);
        fruitBox.add(new Orange());
        fruitBox.add(new Orange());
        fruitBox.add(new Apple());
        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(fruitBox.getWeight());
        System.out.println(appleBox.compare(fruitBox));
        Box<Apple> newBoxApple = new Box<>(6);
        System.out.println(newBoxApple.getWeight());
        moveFruits(appleBox, newBoxApple);
        System.out.println(newBoxApple.getWeight());
    }
}
