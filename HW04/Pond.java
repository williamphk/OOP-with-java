public class Pond {
    public static void main(String[] args) {
        Frog frogOne = new Frog("Peepo");
        Frog frogTwo = new Frog("Pepe", 10, 15);
        Frog frogThree = new Frog("Peepaw", 4.6, 5);
        Frog frogFour = new Frog("William", 4.6, 5);

        Fly flyOne = new Fly(1, 3);
        Fly flyTwo = new Fly(6);
        Fly flyThree = new Fly();

        Frog.setSpecies("1331 Frogs");
        System.out.println(frogOne.getSpecies());
        System.out.println(frogOne.toString());
        frogOne.eat(flyTwo);
        System.out.println(flyTwo.toString());
        frogOne.grow(8);
        frogOne.eat(flyTwo);
        System.out.println(flyTwo.toString());
        System.out.println(frogOne.toString());
        System.out.println(frogFour.toString());
        frogThree.grow(4);
        System.out.println(frogThree.toString());
        System.out.println(frogTwo.toString());
    }
}
