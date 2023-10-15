package co.bk.kotlinskills.interoperability;

import org.jetbrains.annotations.Nullable;

/*
 * Needs to be located under src/main/java for the compiler to find it.
 *
 * The @Nullable annotation is used to mark a variable as being able to be null.
 *
 * This is useful for Java interoperability with Kotlin.
 *
 * The Kotlin compiler sees the @NotNull and @Nullable annotations uses them to map to a non-null type and a nullable type respectively.
 */
public class Car {

    private String color;
    private String model;
    private int year;

    public Car(String color, String model, int year) {
        this.color = color;
        this.model = model;
        this.year = year;
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

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
