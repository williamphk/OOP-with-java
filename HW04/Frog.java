import java.text.DecimalFormat;

public class Frog {
    private String name;
    private int age;
    private double tongueSpeed;
    private boolean isFroglet;
    private static String species = "Rare Pepe";
    DecimalFormat formatter = new DecimalFormat("0.00");

    public static final String SPECIES_DEFAULT = "Rare Pepe";
    public static final double TONGUESPEED_DEFAULT = 5;
    public static final int AGE_DEFAULT = 5;

    public Frog(String name, int age, double tongueSpeed) {
        this.name = name;
        this.age = age;
        this.tongueSpeed = tongueSpeed;
        if (this.age < 7 && this.age > 1) {
            this.isFroglet = true;
        } else
            this.isFroglet = false;
    }

    public Frog(String name, double ageInYears, double tongueSpeed) {
        this(name, (int) ageInYears, tongueSpeed);
    }

    public Frog(String name) {
        this(name, AGE_DEFAULT, TONGUESPEED_DEFAULT);
    }

    public void grow(int months) {
        for (int i = 0; i < months; i++) {
            this.age++;
            if (this.age <= 12) {
                this.tongueSpeed++;
            } else if (this.age > 30 && this.tongueSpeed > 5) {
                this.tongueSpeed--;
            }
            if (this.age < 7 && this.age > 1) {
                this.isFroglet = true;
            } else
                this.isFroglet = false;
        }
    }

    public void grow() {
        this.age++;
        if (this.age < 12) {
            this.tongueSpeed++;
        } else if (this.age >= 30 && this.tongueSpeed > 5) {
            this.tongueSpeed--;
        }
        if (this.age < 7 && this.age > 1) {
            this.isFroglet = true;
        } else
            this.isFroglet = false;
    }

    public void eat(Fly fly) {
        if (fly.isDead())
            return;
        if (this.tongueSpeed > fly.getSpeed()) {
            if (fly.getMass() >= (this.age / 2)) {
                this.grow();
                fly.setMass(0);
            }
        } else {
            fly.grow(1);
        }
    }

    public String toString() {
        if (this.isFroglet) {
            return String.format(
                    "My name is %s and I’m a rare froglet! I’m %s months old and my tongue has a speed of %s.",
                    this.name, this.age, formatter.format(this.tongueSpeed));
        } else {
            return String.format(
                    "My name is %s and I’m a rare frog. I’m %s months old and my tongue has a speed of %s.",
                    this.name, this.age, formatter.format(this.tongueSpeed));
        }
    }

    public static String getSpecies() {
        return species;
    }

    public static void setSpecies(String species) {
        Frog.species = species;
    }
}
