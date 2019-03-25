import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Missing input file argument");
      return;
    }
    try {
      Scanner scanner = new Scanner(new File(args[0]));
      while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Could not locate file: " + args[0]);
      return;
    }
  }
}
