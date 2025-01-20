import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ArrayList<Game> games;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        games = loadGames();
        displayMenu();
    }



    private static void displayMenu() {
        boolean menuIsWorking = true;
        while (menuIsWorking) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Games");
            System.out.println("2. Add Game");
            System.out.println("3. Save & Exit");
            System.out.println("4. Remove Game");
            System.out.println("5. Edit Game");
            System.out.println("6. Sort Games");
            System.out.println("7. Calculate Average Release Year");
            System.out.println("8. View Games by Year");
            System.out.print("Select an option: ");

            int option = getIntInput();

            switch (option) {
                case 1:
                    displayGames();
                    break;
                case 2:
                    addGame();
                    break;
                case 3:
                    saveGames(games); // Save before exit
                    System.out.println("Exiting...");
                    menuIsWorking = false;
                    break;
                case 4:
                    removeGame();
                    break;
                case 5:
                    editGame();
                    break;
                case 6:
                    sortGames();
                    break;
                case 7:
                    calculateAverageReleaseYear();
                    break;
                case 8:
                    viewGamesByYear();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void displayGames() {
        if (games.isEmpty()) {
            System.out.println("No games in the collection");
        } else {
            for (Game game : games) {
                System.out.println(game);
            }
        }
    }

    private static void addGame() {
        System.out.println("Enter game name: ");
        String name = scanner.nextLine().trim();
        System.out.println("Enter publisher: ");
        String publisher = scanner.nextLine().trim();
        System.out.println("Enter year: ");
        int year = getIntInput();
        Genre genre = Genre.values()[genreInput()];

        games.add(new Game(name, publisher, year, genre));
        System.out.println("Game added.");
    }

    private static void removeGame() {
        if (games.isEmpty()) {
            System.out.println("No games available to remove.");
        } else {
            System.out.println("Select a game you want to remove:");
            for (int i = 0; i < games.size(); i++) {
                System.out.println((i + 1) + ". " + games.get(i));
            }

            boolean validInput = false;
            while (!validInput) {
                System.out.println("\nSelect a game to remove:");
                for (int i = 0; i < games.size(); i++) {
                    System.out.println((i + 1) + ". " + games.get(i));
                }
                System.out.print("Enter the number of the game you want to remove: ");
                int gameNumber = getIntInput();

                if (gameNumber < 1 || gameNumber > games.size()) {
                    System.out.println("Invalid game number. Please try again.");
                } else {
                    games.remove(gameNumber - 1);
                    System.out.println("Game removed successfully.");
                    validInput = true;
                }
            }
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static int genreInput() {
        int genreIndex = -1;
        while (genreIndex < 0 || genreIndex >= Genre.values().length) {
            System.out.println("Select a genre:");
            for (int i = 0; i < Genre.values().length; i++) {
                System.out.println((i + 1) + ". " + Genre.values()[i]);
            }

            System.out.print("Choose a genre by entering its number: ");
            genreIndex = getIntInput() - 1;

            if (genreIndex < 0 || genreIndex >= Genre.values().length) {
                System.out.println("Invalid genre selection. Please try again.");
            }
        }
        return genreIndex;
    }

    private static void editGame() {
        int gameNumber = -1;
        if (games.isEmpty()) {
            System.out.println("No games available to edit.");
        } else {
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Select a game you want to edit:");
                for (int i = 0; i < games.size(); i++) {
                    System.out.println((i + 1) + ". " + games.get(i));
                }
                System.out.print("Enter the number of the game you want to edit: ");
                gameNumber = getIntInput();

                if (gameNumber < 1 || gameNumber > games.size()) {
                    System.out.println("Invalid game number. Please try again.");
                } else {
                    validInput = true;
                }
            }

            Game gameToEdit = games.get(gameNumber - 1);

            System.out.println("Current publisher: " + gameToEdit.getPublisher());
            System.out.print("Enter new publisher: ");
            String newPublisher = scanner.nextLine();
            gameToEdit.setPublisher(newPublisher);

            System.out.println("Game publisher updated successfully.");
        }
    }

    private static void sortGames() {
        if (games.isEmpty()) {
            System.out.println("No games available to sort.");
        } else {

            Collections.sort(games);

            System.out.println("Games have been sorted by name.");
        }
    }

    private static void calculateAverageReleaseYear() {
        if (games.isEmpty()) {
            System.out.println("No games in the collection to calculate the average release year.");
        } else {
            int sumYears = 0;
            for (Game game : games) {
                sumYears += game.getYear();
            }

            int average =  sumYears / games.size();

            System.out.printf("The average release year for the games in the collection is: "+ average);
        }
    }

    private static void viewGamesByYear() {
        if (games.isEmpty()) {
            System.out.println("No games in the collection.");
        } else {
            System.out.print("Enter the year to filter games: ");
            int filterYear = getIntInput();

            boolean found = false;
            for (Game game : games) {
                if (game.getYear() == filterYear) {
                    System.out.println(game);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No games from the year" + filterYear + "found");
            }
        }
    }

    public static void saveGames(ArrayList<Game> games) {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("save.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(games);
        } catch (FileNotFoundException e){
            System.out.println("Unable to save file: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Unable to create object output stream: " + e.getMessage());
        }
    }

    public static ArrayList<Game> loadGames(){
        try
        {
            FileInputStream fileInputStream = new FileInputStream("save.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<Game> games = (ArrayList<Game>)objectInputStream.readObject();
            return games;
        } catch (FileNotFoundException e){
            System.out.println("Unable to save file: " + e.getMessage());
        } catch (IOException e){
            System.out.println("Error converting data to object"+ e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Can't find class representing saved object: "+ e.getMessage());
        }
       return new ArrayList<Game>();
    }
}