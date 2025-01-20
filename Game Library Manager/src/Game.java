import java.io.Serializable;

public class Game implements Serializable, Comparable<Game> {

    private String name;
    private String publisher;
    private int year;

    private Genre genre;
    public Game(String name, String publisher, int year, Genre genre){
        this.name = name;
        this.publisher = publisher;
        this.year = year;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public int compareTo(Game otherGame) {
        return this.name.compareTo(otherGame.name);
    }

    @Override
    public String toString() {
        return "Game: " + name + ", publisher: " + publisher + ", year: "+ year + ", genre: " + genre;
    }
}