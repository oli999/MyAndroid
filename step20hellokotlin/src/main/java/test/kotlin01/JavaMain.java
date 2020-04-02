package test.kotlin01;

public class JavaMain {
    public static void main(String[] args) {
        Util.Companion.download();
        Util.upload();

        System.out.println("Util version : "+Util.Companion.getVersion());
        System.out.println("Util author : "+Util.author);
    }
}
