import java.util.Scanner;
import java.util.Set;

public class Library {
    public static void main(String[] args) {
        TreeMap<String, String> books = new TreeMap<>();
        BidiMap<String, String> borrowedBooks = new BidiMap();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. New book");
            System.out.println("2. New reader");
            System.out.println("3. Give book to reader");
            System.out.println("4. Return book to library");
            System.out.println("5. Show book in library");
            System.out.println("6. Show book in reader");
            System.out.println("7. Clear library database");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID Book: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Name Book: ");
                    String title = scanner.nextLine();
                    books.put(isbn, title);
                    break;

                case 2:
                    System.out.print("ID reader: ");
                    String cardNumber = scanner.nextLine();
                    System.out.print("Name Reader: ");
                    String name = scanner.nextLine();
                    borrowedBooks.put(cardNumber, "");
                    break;

                case 3:
                    System.out.print("ID Book: ");
                    String bookIsbnToGive = scanner.nextLine(); // Поменяли имя переменной
                    System.out.print("ID reader: ");
                    String readerCardNumber = scanner.nextLine();
                    borrowedBooks.put(readerCardNumber, bookIsbnToGive); // Использовали новое имя
                    break;

                case 4:
                    System.out.print("ID Book: ");
                    String bookIsbnToReturn = scanner.nextLine();
                    String readerCardNumberToReturn = borrowedBooks.getKey(bookIsbnToReturn);
                    borrowedBooks.remove(bookIsbnToReturn);
                    break;

                case 5:
                    System.out.println("Books in Library:");
                    for (String bookIsbn : books.keySet()) {
                        if (!borrowedBooks.containsValue(bookIsbn)) {
                            System.out.println(bookIsbn + ": " + books.get(bookIsbn));
                        }
                    }
                    break;

                case 6:
                    System.out.println("Books taken by Readers:");
                    Set<String> borrowedIsbns = borrowedBooks.values();
                    for (String isbnBorrowed : borrowedIsbns) {
                        String card = borrowedBooks.getKey(isbnBorrowed);
                        System.out.println(isbnBorrowed + " taken by reader " + card);
                    }
                    break;

                case 7:
                    books.clear();
                    borrowedBooks.clear();
                    System.out.println("Library database cleared.");
                    break;

                case 0:
                    System.out.println("Exiting.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Error. Try Again");
                    break;
            }
        }
    }
}
