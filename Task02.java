import java.util.*;
class Book {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

record User(String name) {

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

class LibrarySystem {
    private final List<Book> availableBooks;
    private final List<Book> issuedBooks;
    private final List<User> issuedTo;

    public LibrarySystem() {
        availableBooks = new ArrayList<>();
        issuedBooks = new ArrayList<>();
        issuedTo = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        Book newBook = new Book(title, author);
        availableBooks.add(newBook);
        System.out.println("Book added: " + newBook);
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : availableBooks) {
            System.out.println(book);
        }
    }

    public void issueBook(User user, String bookTitle) {
        for (Book book : availableBooks) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                availableBooks.remove(book);
                issuedBooks.add(book);
                issuedTo.add(user);
                System.out.println("Book issued to " + user.name() + ": " + book);
                return;
            }
        }
        System.out.println("Book not found or already issued.");
    }

    public void returnBook(User user) {
        int index = -1;
        for (int i = 0; i < issuedTo.size(); i++) {
            if (issuedTo.get(i).name().equalsIgnoreCase(user.name())) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            Book returnedBook = issuedBooks.remove(index);
            availableBooks.add(returnedBook);
            issuedTo.remove(index);
            System.out.println("Book returned by " + user.name() + ": " + returnedBook);
        } else {
            System.out.println("No book issued to " + user.name());
        }
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome! Please Enter:\n\"1\" To Add a Book\n\"2\" To Display Available Books\n\"3\" To Issue a Book\n\"4\" To Return a Book\n\"0\" To Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;

                case 2:
                    library.displayAvailableBooks();
                    break;

                case 3:
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();
                    User user = new User(userName);
                    System.out.print("Enter the title of the book you want to issue: ");
                    String issueBookTitle = scanner.nextLine();
                    library.issueBook(user, issueBookTitle);
                    break;

                case 4:
                    System.out.print("Enter your name: ");
                    String returnUserName = scanner.nextLine();
                    User returnUser = new User(returnUserName);
                    library.returnBook(returnUser);
                    break;

                case 0:
                    System.out.println("Thank you for using our library Services");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
