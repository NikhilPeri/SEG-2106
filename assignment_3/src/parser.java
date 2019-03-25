import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
  private static Scanner scanner;
  private static String token;

  private static Boolean factor() {
    token = scanner.readline();
    return token == "id" || token == "num";
  }

  private static Boolean expression_prime() {
    token = scanner.readline();
    if (token == "+" || token == "-") {
      return factor();
    }
    return token == "$";
  }

  private static Boolean expression() {
    if (factor()) {
      return expression_prime();
    }
    return false;
  }

  private static Boolean statement() {
    token = scanner.readline();
    if (token != "id") {
      return false;
    }
    token = scanner.readline();
    if (token != "=") {
      return false;
    }
    return expression();
  }

  private static Boolean statement_list_prime() {
    token = scanner.readline();
    return token == "end";
  }

  private static Boolean statement_list() {
    if (statement()) {
      token = scanner.readline();
      if (token != ";") {
        return false;
      }
      return statement_list_prime();
    }
    return false;
  }

  private static Boolean program() {
    if (token == "begin") {
      return statement_list();
    }
    return false;
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Missing input file argument");
      return;
    }
    try {
      scanner = new Scanner(new File(args[0]));

    } catch (FileNotFoundException e) {
      System.out.println("Could not locate file: " + args[0]);
      return;
    }

    try {
      token = scanner.readline();
      if (program()) {
        System.out.println("SUCCESS");
      } else {
        System.out.println("ERROR");
      }
      scanner.close();
    } catch (Exception e) {
      System.out.println("ERROR");
    }
  }
}
