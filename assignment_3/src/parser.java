import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
  private static Scanner scanner;
  private static String token;

  private static Boolean factor() {
    token = scanner.next();
    return token.equals("id") || token.equals("num");
  }

  private static Boolean expression_prime() {
    token = scanner.next();
    if (token.equals("+") || token.equals("-")) {
      return factor();
    }
    return true;
  }

  private static Boolean expression() {
    if (factor()) {
      return expression_prime();
    }
    return false;
  }

  private static Boolean statement() {
    token = scanner.next();
    if (!token.equals("id")) {
      return false;
    }
    token = scanner.next();
    if (!token.equals("=")) {
      return false;
    }
    return expression();
  }

  private static Boolean statement_list_prime() {
    token = scanner.next();
    return token.equals("end");
  }

  private static Boolean statement_list() {

    if (statement()) {
      token = scanner.next();
      System.out.println("statement_list");
      if (!token.equals(";")) {
        return false;
      }
      return statement_list_prime();
    }
    return false;
  }

  private static Boolean program() {
    token = scanner.next();
    if (token.equals("begin")) {
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

    //try {
      if (program()) {
        System.out.println("SUCCESS");
      } else {
        System.out.println("ERROR");
      }
      scanner.close();
    //} catch (Exception e) {
    //  System.out.println("ERROR");
    //}
  }
}
