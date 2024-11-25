package multithreading;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import view.*;

public class Main {
    private static final int MAX_THREADS = 2;
    private static final int MIN_THREADS = 2;
    private static final int MIN_DISTANCE = 50;
    private static final int MAX_DISTANCE = 10000;
    private static final int MIN_SLEEP = 2;
    private static final int MAX_SLEEP = 5;

    public static void main(String[] args) {
        InputOutput io = new StandardInputOutput();
        Item[] items = getItems();
        Menu menu = new Menu("Race Game", items);
        menu.perform(io);
    }

    private static Item[] getItems() {
        Item[] res = {
                Item.of("Start new race", Main::startRace),
                Item.ofExit()
        };
        return res;
    }

    static void startRace(InputOutput io) {
        int nThreads = io.readNumberRange("Enter number of the racers", "Wrong number of the racers",
                MIN_THREADS, MAX_THREADS).intValue();
        int distance = io.readNumberRange("Enter distance",
                "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE).intValue();
        Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
        Racer[] racers = new Racer[nThreads];
        startRacers(racers, race);
        rateRacers(racers);
        displayResult(race);
    }

    private static void displayResult(Race race) {
        List<ReportItem> report = race.getResult();
        for (int i = 0; i < report.size(); i++) {
            ReportItem item = report.get(i);
            System.out.printf(
                "# %d - racer: %d, time: %dms \n",
                i + 1,
                item.number(),
                item.time()
            );
        }
    }


    private static void startRacers(Racer[] racers, Race race) {
        race.setStartTime(LocalDateTime.now());
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }

    private static void rateRacers(Racer[] racers) {
        Arrays.stream(racers).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {

            }
        });
    }
}