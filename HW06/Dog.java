public class Dog extends Pet {
    private double droolRate;

    public Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        this.droolRate = droolRate <= 0 ? 0.5 : droolRate;
    }

    public Dog(String name, double health, int painLevel) {
        this(name, health, painLevel, 5.0);
    }

    public double getDroolRate() {
        return droolRate;
    }

    public void setDroolRate(double droolRate) {
        this.droolRate = droolRate;
    }

    public int treat() {
        int time = 0;
        if (droolRate < 3.5) {
            time = (int) (this.getPainLevel() * 2 / this.getHealth());
        } else if (droolRate >= 3.5 && droolRate <= 7.5) {
            time = (int) (this.getPainLevel() / this.getHealth());
        } else if (droolRate > 7.5) {
            time = (int) (this.getPainLevel() / (this.getHealth() * 2));
        }
        super.heal();
        return time;
    }

    public void speak() {
        super.speak();
        String sound = (this.getPainLevel() > 5) ? "BARK " : "bark ";
        String output = "";
        for (int i = 0; i < this.getPainLevel(); i++) {
            output += sound;
        }
        System.out.println(output.trim());
    }

    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog dog = (Dog) o;
            return super.equals(dog) && this.droolRate == dog.getDroolRate();
        }
        return false;
    }
}
