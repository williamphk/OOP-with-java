public class Cat extends Pet {
    private int miceCaught;

    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        this.miceCaught = miceCaught < 0 ? 0 : miceCaught;
    }

    public Cat(String name, double health, int painLevel) {
        this(name, health, painLevel, 0);
    }

    public double getMiceCaught() {
        return miceCaught;
    }

    public void setMiceCaught(int miceCaught) {
        this.miceCaught = miceCaught;
    }

    public int treat() {
        int time = 0;
        if (miceCaught < 4) {
            time = (int) (this.getPainLevel() * 2 / this.getHealth());
        } else if (miceCaught >= 4 && miceCaught <= 7) {
            time = (int) (this.getPainLevel() / this.getHealth());
        } else if (miceCaught > 7) {
            time = (int) (this.getPainLevel() / (this.getHealth() * 2));
        }
        super.heal();
        return time;
    }

    public void speak() {
        super.speak();
        String sound = (this.getPainLevel() > 5) ? "MEOW " : "meow ";
        String output = "";
        for (int i = 0; i < this.getMiceCaught(); i++) {
            output += sound;
        }
        System.out.println(output.trim());
    }

    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat cat = (Cat) o;
            return super.equals(cat) && this.miceCaught == cat.getMiceCaught();
        }
        return false;
    }
}
