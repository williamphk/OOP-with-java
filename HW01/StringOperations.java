package HW01;
public class StringOperations {
    public static void main(String[] args) {
        String myName = new String("Hong Kan Poon");
        System.out.println(myName);

        String myNameReplaced1 = myName.replace("H", "A");
        String myNameReplaced2 = myNameReplaced1.replace("n", "Z");

        System.out.println(myNameReplaced2);

        String url = new String("www.name.tld");
        System.out.println(url);

        String urlCon = url.substring(4, url.length() - 4).concat("1331");
        System.out.println(urlCon);
    }
}
