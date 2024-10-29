import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FootballLeague {
    private String name;
    private String city;
    private int leaguePosition;
    private int wins;

    public FootballLeague(String name, String city, int leaguePosition, int wins) {
        this.name = name;
        this.city = city;
        this.leaguePosition = leaguePosition;
        this.wins = wins;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLeaguePosition() {
        return leaguePosition;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLeaguePosition(int leaguePosition) {
        this.leaguePosition = leaguePosition;
    }

    @Override
    public String toString() {
        return "Команда: " + name + ", Город: " + city + ", Позиция в лиге: " + leaguePosition + ", Победы: " + wins;
    }

    public static void main(String[] args) {
        List<FootballLeague> teams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество команд: ");
        int numberOfTeams = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        for (int i = 0; i < numberOfTeams; i++) {
            System.out.println("Введите данные для команды " + (i + 1) + ":");
            System.out.print("Название: ");
            String name = scanner.nextLine();
            System.out.print("Город: ");
            String city = scanner.nextLine();
            System.out.print("Позиция в лиге: ");
            int leaguePosition = scanner.nextInt();
            System.out.print("Количество побед: ");
            int wins = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            teams.add(new FootballLeague(name, city, leaguePosition, wins));
        }

        // Определение команды с самым большим количеством побед
        FootballLeague topTeam = teams.stream()
                .max(Comparator.comparingInt(FootballLeague::getWins))
                .orElse(null);
        System.out.println("nКоманда с самым большим количеством побед: " + topTeam);

        // Определение среднего количества побед
        double averageWins = teams.stream()
                .mapToInt(FootballLeague::getWins)
                .average()
                .orElse(0);

        System.out.println("Среднее количество побед: " + averageWins);

        // Команды с количеством побед больше среднего
        List<FootballLeague> aboveAverageTeams = new ArrayList<>();
        for (FootballLeague team : teams) {
            if (team.getWins() > averageWins) {
                aboveAverageTeams.add(team);
            }
        }
        System.out.println("Команды с количеством побед больше среднего: " + aboveAverageTeams);

        // Упорядочивание массива по убыванию мест в лиге
        teams.sort(Comparator.comparingInt(FootballLeague::getLeaguePosition).reversed());
        System.out.println("nКоманды после сортировки по убыванию мест в лиге:");
        for (FootballLeague team : teams) {
            System.out.println(team);
        }

        // Поиск и редактирование команды
        System.out.print("nВведите название команды для редактирования: ");
        String teamName = scanner.nextLine();

        Optional<FootballLeague> editedTeamOpt = teams.stream()
                .filter(team -> team.getName().equalsIgnoreCase(teamName))
                .findFirst();

        if (editedTeamOpt.isPresent()) {
            FootballLeague editedTeam = editedTeamOpt.get();
            System.out.print("Введите новый город: ");
            String newCity = scanner.nextLine();
            System.out.print("Введите новое место в лиге: ");
            int newLeaguePosition = scanner.nextInt();
            System.out.print("Введите новое количество побед: ");
            int newWins = scanner.nextInt();

            editedTeam.setWins(newWins);
            editedTeam.setLeaguePosition(newLeaguePosition);
            System.out.println("Команда после редактирования: " + editedTeam);
        } else {
            System.out.println("Команда не найдена.");
        }

        scanner.close();
    }
}
