import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    List<Security> portfolio = new ArrayList<>();

    portfolio.add(new CommonStock("1234567", "Apple, Inc.", 100, 100.0, 134.89));
    portfolio.add(new Bond("7654321", "U.S. Govt. 2 Year", 10_000.0, 0.0113, 2));
    portfolio.add(new CommonStock("1234566", "Amazon, Inc.", 50, 1100.0, 1116.5));
    portfolio.add(new Bond("7654320", "U.S. Govt. 5 Year", 5_000.0, 0.022, 4));
    portfolio.add(new CommonStock("1234567", "Apple, Inc.", 25, 135, 134.89));
    portfolio.add(new Bond("7654321", "U.S. Govt. 2 Year", 10_000.0, 0.01, 1));


    System.out.println("~~~Unsorted List~~~");
    for (Security s : portfolio)
      System.out.println(s);

    Collections.sort(portfolio);
    System.out.println("\n~~~Sorted List~~~");
    for (Security s : portfolio)
      System.out.println(s);

  }

}
