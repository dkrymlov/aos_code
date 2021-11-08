import java.util.Scanner;

// Main test application
public class Main
{
    public static void main(String[] args)
    {
        Facade facade = new Facade();
        facade.MethodA();
        facade.MethodB();
        // Wait for user
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
    }
}







