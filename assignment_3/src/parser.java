import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
  private static Scanner scanner;
  private static String token;

  private static String get_token() {
    token = scanner.next();
    System.out.println(token);
    return token;
  }

  private static Boolean factor() {
    token = get_token();
    return token.equals("id") || token.equals("num");
  }

  private static Boolean expression_prime() {
    token = get_token();
    if (token.equals("+") || token.equals("-")) {
      return factor() && get_token().equals(";");
    }
    return token.equals(";");
  }

  private static Boolean expression() {
    if (factor()) {
      return expression_prime();
    }
    return false;
  }

  private static Boolean statement() {
    if (get_token().equals("id") && get_token().equals("=")) {
      return expression();
    }
    return false;
  }

  private static Boolean statement_list_prime() {
    if (statement_list()) {
      return true;
    }
    return token.equals("end");
  }

  private static Boolean statement_list() {
    if (statement()) {
      return statement_list_prime();
    }
    return false;
  }

  private static Boolean program() {

    if (get_token().equals("begin")) {
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
