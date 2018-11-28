public abstract class Movie {


    private String _title;

    public Movie(String title) {
        set_title(title);
    }


    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }
    abstract double getCharge(int _daysRented);

    abstract int getFrequentRenterPoints(int _daysRented);
}
