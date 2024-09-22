import java.text.DecimalFormat;

public class Fly {

    private double mass;
    private double speed;
    DecimalFormat formatter = new DecimalFormat("0.00");

    public static final double DEFAULT_MASS = 5;
    public static final double DEFAULT_SPEED = 10;

    public Fly(double mass, double speed) {
        this.speed = speed;
        this.mass = mass;
    }

    public Fly(double mass) {
        this(mass, DEFAULT_SPEED);
    }

    public Fly() {
        this(DEFAULT_MASS, DEFAULT_SPEED);
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getMass() {
        return this.mass;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String toString() {
        if (this.mass == 0) {
            return String.format("I’m dead, but I used to be a fly with a speed of %s.", formatter.format(this.speed));
        } else {
            return String.format("I’m a speedy fly with %s speed and %s mass.", formatter.format(this.speed),
                    formatter.format(this.mass));
        }
    }

    public void grow(int mass) {
        for (int i = 0; i < mass; i++) {
            this.mass++;
            if (this.mass <= 20) {
                this.speed++;
            } else {
                this.speed -= 0.5;
            }
        }
    }

    public boolean isDead() {
        if (this.mass == 0) {
            return true;
        }
        return false;
    }
}
