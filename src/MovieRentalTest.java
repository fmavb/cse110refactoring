import org.junit.*;

public class MovieRentalTest extends junit.framework.TestCase {

    Movie theManWhoKnewTooMuch, mulan, slumdogMillionaire;

    @Before
    public void setUp() {
        theManWhoKnewTooMuch = new RegularMovie("The Man Who Knew Too Much");
        mulan = new ChildrensMovie("Mulan");
        slumdogMillionaire = new NewReleaseMovie("Slumdog Millionaire");
    }

    @Test
    public void testGetDaysRented() {
        assertEquals(2, new MovieRental(theManWhoKnewTooMuch, 2).getDaysRented());
    }

    @Test
    public void testGetMovie() {
        assertEquals(theManWhoKnewTooMuch, new MovieRental(theManWhoKnewTooMuch, 2).getMovie());
    }

    @Test
    public void testGetName() {
        String name = "John Doe"; 
        assertEquals(name, new Customer(name)._name);
    }

    @Test
    public void testStatementRegularMovieOnly() {
        // regular movies cost $2.00 for the first 2 days, and $1.50/day thereafter
        // a rental earns 1 frequent-renter point no matter how many days
        Customer johnDoe = new Customer("John Doe");
        johnDoe.addMovieRental(new MovieRental(theManWhoKnewTooMuch, 1));
        assertEquals("Rental Record for John Doe\n" +
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter points",
                johnDoe.statement());

        johnDoe.addMovieRental(new MovieRental(theManWhoKnewTooMuch, 4));

        assertEquals("Rental Record for John Doe\n" +
                        //the first movie rental above ( for 1 day )
                        "\tThe Man Who Knew Too Much\t2.0\n" +
                        //the current movie rental ( for 2 days )
                        "\tThe Man Who Knew Too Much\t5.0\n" +
                        //Total of first movie rental cost and second movie rental cost
                        "Amount owed is 7.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 2 frequent renter points",
                johnDoe.statement());


    }

    @Test
    public void testStatementChildrensMovieOnly() {
        // childrens' movies cost $1.50 for the first 3 days, and $1.25/day thereafter
        // a rental earns 1 frequent-renter point no matter how many days
        Customer johnDoeJr = new Customer("Johnny Doe, Jr.");

        johnDoeJr.addMovieRental(new MovieRental(mulan, 1));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        "\tMulan\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points",
                johnDoeJr.statement());

        johnDoeJr.addMovieRental(new MovieRental(mulan, 6));
        assertEquals("Rental Record for Johnny Doe, Jr.\n" +
                        //the first movie rental above ( for 1 day )
                        "\tMulan\t1.5\n" +
                        //the current movie rental ( for 2 days )
                        "\tMulan\t5.25\n" +
                        //Total of first movie rental cost and second movie rental cost
                        "Amount owed is 6.75\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 2 frequent renter points",
                johnDoeJr.statement());
    }

    @Test
    public void testStatementNewReleaseOnly() {
        // new releases cost $3.00/day
        // a rental earns 1 frequent-renter point 1 day; 2 points for 2 or more days
        Customer janeDoe = new Customer("Jane Doe");

        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 1));
        assertEquals("Rental Record for Jane Doe\n" +
                        "\tSlumdog Millionaire\t3.0\n" +
                        "Amount owed is 3.0\n" +
                        "You earned 1 frequent renter points",
                janeDoe.statement());

//rental #2, renting additional copy of slumdogMillionaire
        janeDoe.addMovieRental(new MovieRental(slumdogMillionaire, 2));
        assertEquals("Rental Record for Jane Doe\n" +
                        //the first movie rental above ( for 1 day )
                        "\tSlumdog Millionaire\t3.0\n" +
                        //the current movie rental ( for 2 days )
                        "\tSlumdog Millionaire\t6.0\n" +
                        //Total of first movie rental cost and second movie rental cost
                        "Amount owed is 9.0\n" +
                        //notice it is the 1 point from before and 2 points for 2 or more days for the current movie.
                        "You earned 3 frequent renter points",
                janeDoe.statement());
    }
}