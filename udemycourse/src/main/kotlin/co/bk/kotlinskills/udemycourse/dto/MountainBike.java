package co.bk.kotlinskills.udemycourse.dto;

public class MountainBike extends Bicycle {

    private int seatHeight;

    public MountainBike(int seatHeight,
                        int cadence,
                        int speed,
                        int gear) {
        super(cadence, speed, gear);
        this.seatHeight = seatHeight;
    }

    public int getSeatHeight() {
        return seatHeight;
    }

    public void setSeatHeight(int seatHeight) {
        this.seatHeight = seatHeight;
    }

    public void printDescription() {
        super.printDescription();
        System.out.println("The mountain bike has a seat height of " + seatHeight + " inches.");
    }
}

/**
 * Kotlin version of this file would:
 * 1. "open" the BicycleKotlin class so it could be extended and
 * 2. make super type the return type of the class as commented out below. Use super type constructor e.g delegate up to
 * the primary constructor
 */
//class MountainBikeKotlin(var seatHeight: Int, cadence: Int, speed: Int, gear: Int):
//        BicycleKotlin(cadence, speed, gear) {
//}
