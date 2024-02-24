interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private int publicationYear;
    private boolean borrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "[Book] " + title + " by " + author + " (" + publicationYear + ")";
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runningTime;
    private boolean borrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        borrowed = true;
    }

    @Override
    public void returnItem() {
        borrowed = false;
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }

    @Override
    public String toString() {
        return "[DVD] " + title + " directed by " + director + " (" + runningTime + " minutes)";
    }
}

abstract class LibraryUser {
    protected String name;
    protected String id;

    public LibraryUser(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void borrowItem(LibraryItem item);

    public abstract void returnItem(LibraryItem item);

    protected void printBorrowedHeader() {
        System.out.println("Items borrowed by " + name + ":");
        System.out.println("-----------------------------");
    }

    protected void printBorrowedFooter() {
        System.out.println("-----------------------------\n");
    }

    public abstract void printItemsBorrowed();

    @Override
    public String toString() {
        return "[LibraryUser] " + name + " (" + id + ")";
    }
}

class Student extends LibraryUser {
    private LibraryItem[] borrowedItems;
    private int numBorrowedItems;

    public Student(String name, String id) {
        super(name, id);
        borrowedItems = new LibraryItem[5];
        numBorrowedItems = 0;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (numBorrowedItems < borrowedItems.length) {
            item.borrowItem();
            borrowedItems[numBorrowedItems++] = item;
        } else {
            System.out.println("Cannot borrow more items.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < numBorrowedItems; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[numBorrowedItems - 1];
                borrowedItems[numBorrowedItems - 1] = null;
                numBorrowedItems--;
                break;
            }
        }
    }

    @Override
    public void printItemsBorrowed() {
        printBorrowedHeader();
        for (int i = 0; i < numBorrowedItems; i++) {
            System.out.println("- " + borrowedItems[i]);
        }
        printBorrowedFooter();
    }

    @Override
    public String toString() {
        return "[Student] " + name + " (" + id + ")";
    }
}

class Teacher extends LibraryUser {
    private LibraryItem[] borrowedItems;
    private int numBorrowedItems;

    public Teacher(String name, String id) {
        super(name, id);
        borrowedItems = new LibraryItem[10];
        numBorrowedItems = 0;
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (numBorrowedItems < borrowedItems.length) {
            item.borrowItem();
            borrowedItems[numBorrowedItems++] = item;
        } else {
            System.out.println("Cannot borrow more items.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        item.returnItem();
        for (int i = 0; i < numBorrowedItems; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[numBorrowedItems - 1];
                borrowedItems[numBorrowedItems - 1] = null;
                numBorrowedItems--;
                break;
            }
        }
    }

    @Override
    public void printItemsBorrowed() {
        printBorrowedHeader();
        for (int i = 0; i < numBorrowedItems; i++) {
            System.out.println("- " + borrowedItems[i]);
        }
        printBorrowedFooter();
    }

    @Override
    public String toString() {
        return "[Teacher] " + name + " (" + id + ")";
    }
}

public class App {
    public static void main(String[] args) {
        // Create books
        Book book1 = new Book("Basketball Fundamentals", "John Lloyd", 1972);
        Book book2 = new Book("My Life in Basketball", "Bill Bill", 2020);

        // Create DVDs
        DVD dvd1 = new DVD("PBA All-Star Highlights", "PBA", 120);
        DVD dvd2 = new DVD("Michael Pangilinan: The Last Song", "GMA", 300);

        // Create students
        Student student1 = new Student("STUDENT NIKUBANO", "NB12");
        Student student2 = new Student("STUDENT BRT SPIRIT", "BS13");

        // Create teachers
        Teacher teacher1 = new Teacher("TEACHER Alberto", "AB24");
        Teacher teacher2 = new Teacher("TEACHER Albert The Second", "AS21");

        // Demonstrate borrowing and returning
        student1.borrowItem(book1);
        student1.borrowItem(dvd1);
        student2.borrowItem(book2);
        student2.borrowItem(dvd2);
        teacher1.borrowItem(book2);
        teacher2.borrowItem(dvd2);

        student1.returnItem(book1);

        // Print items borrowed by each user
        System.out.println(student1);
        student1.printItemsBorrowed();
        System.out.println(student2);
        student2.printItemsBorrowed();
        System.out.println(teacher1);
        teacher1.printItemsBorrowed();
        System.out.println(teacher2);
        teacher2.printItemsBorrowed();
    }
}