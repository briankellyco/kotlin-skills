package co.bk.kotlinskills.interoperability;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;


public class CarTwo {

    public static int carCount = 5;

    private String color;
    private String model;
    private int year;
    private Object anObject;

    public CarTwo(String color, String model, int year) {
        this.color = color;
        this.model = model;
        this.year = year;
    }

    public void demoMethod(Runnable r) {

       new Thread(r).start();

       // Runnable is a SAM (Single Abstract Method) interface as it has ONLY one method e.g run()

       // SAM.... instance created via lambda expression... newer approach since Java 8
        //new Thread( () -> System.out.println("I'm in a thread!") ).start();

        // SAM... instance created via anonymous instance... old approach
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("I'm in a thread!");
//            }
//        }).start();
    }

    public void variableMethod(int num, String... strings) {
        for (String string: strings) {
            System.out.println(string);
        }
    }

    public static String carCountString() {
        return "This is carCount: " + carCount++;
    }

    public Object getAnObject() {
        return anObject;
    }

    public void setAnObject(Object anObject) {
        this.anObject = anObject;
    }

    public String getColor() {
        return color;
    }

    public void setColor(@Nullable String color) {
        this.color = color;
    }

    public @Nullable String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void wantsIntArray(int[] theArray) {
        for (int i: theArray) {
            System.out.println("Here's the int: " + i);
        }
    }

    @Override
    public String toString() {
        return "CarTwo{" +
                "color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
