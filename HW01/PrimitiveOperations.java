package HW01;
public class PrimitiveOperations {
    public static void main(String[] args) {
        int variable1 = 5;
        double variable2 = 3.5;

        System.out.println(variable1);
        System.out.println(variable2);

        double outcome = variable1 * variable2;
        System.out.println(outcome);

        double variable1F = (double) variable1;
        System.out.println(variable1F);

        int variable2I = (int) variable2;
        System.out.println(variable2I);

        char variable3 = 'A';
        System.out.println(variable3);

        char variable4 = (char) ((int) variable3 + 32);
        System.out.println(variable4);
    }
}
