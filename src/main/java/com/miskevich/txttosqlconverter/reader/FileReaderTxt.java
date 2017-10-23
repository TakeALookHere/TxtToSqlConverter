package com.miskevich.txttosqlconverter.reader;

import com.miskevich.txttosqlconverter.entity.Movie;
import com.miskevich.txttosqlconverter.entity.Review;
import com.miskevich.txttosqlconverter.entity.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileReaderTxt {

    private static final String UTF8_BOM = "\uFEFF";

    public List<Movie> readFileMovie(Path path) {
        List<Movie> movies = new ArrayList<>();
        List<String> lines;
        int lineCounter = 0;
        int idCounter = 1;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Movie movie = null;
        for (String line : lines) {
            line = removeBOM(line);

            if (lineCounter == 0 && line.contains("/")) {
                movie = new Movie();
                movie.setId(idCounter);
                idCounter++;

                int indexOfSlash = line.indexOf("/");
                String nameRussian = line.substring(0, indexOfSlash);
                String nameNative = line.substring(indexOfSlash + 1);
                movie.setNameRussian(nameRussian.trim());
                movie.setNameNative(nameNative.trim());
                lineCounter++;
            } else if (lineCounter == 1 && !line.isEmpty()) {
                movie.setReleasedDate(line + "-01-01");
                lineCounter++;
            } else if (lineCounter == 2 && !line.isEmpty()) {
                movie.setCountries(Arrays.asList(line.split(", ")));
                lineCounter++;
            } else if (lineCounter == 3 && !line.isEmpty()) {
                movie.setGenres(Arrays.asList(line.split(", ")));
                lineCounter++;
            } else if (lineCounter == 4 && !line.isEmpty()) {
                movie.setPlot(line);
                lineCounter++;
            } else if (lineCounter == 5 && !line.isEmpty()) {
                movie.setRating(Double.valueOf(line.substring(line.indexOf(":") + 1)));
                lineCounter++;
            } else if (lineCounter == 6 && !line.isEmpty()) {
                movie.setPrice(Double.valueOf(line.substring(line.indexOf(":") + 1)));
                lineCounter++;
            }
            if (lineCounter == 7) {
                movies.add(movie);
                lineCounter = 0;
            }
        }

        return movies;
    }

    public Map<String, Integer> readFileGenre(Path path) {
        Map<String, Integer> genres = new HashMap<>();
        int lineCounter = 1;
        List<String> lines;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            if (!line.isEmpty()) {
                line = removeBOM(line);
                genres.put(line.trim(), lineCounter);
                lineCounter++;
            }
        }
        return genres;
    }

    public Map<String, Integer> generateCountriesFromScratch() {
        Map<String, Integer> countries = new HashMap<>();
        int lineCounter = 1;
        countries.put("США", lineCounter++);
        countries.put("Франция", lineCounter++);
        countries.put("Великобритания", lineCounter++);
        countries.put("Италия", lineCounter++);
        countries.put("Германия", lineCounter++);
        countries.put("Япония", lineCounter++);
        countries.put("Испания", lineCounter);
        return countries;
    }

    public List<User> readFileUser(Path path) {
        List<User> users = new ArrayList<>();
        List<String> lines;
        int lineCounter = 0;
        int idCounter = 1;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = null;
        for (String line : lines) {
            line = removeBOM(line);

            if (lineCounter == 0 && !line.isEmpty()) {
                user = new User();
                user.setId(idCounter);
                user.setNickname(line.trim());
                idCounter++;
                lineCounter++;
            } else if (lineCounter == 1 && !line.isEmpty()) {
                user.setEmail(line.trim());
                lineCounter++;
            } else if (lineCounter == 2 && !line.isEmpty()) {
                user.setPassword(line.trim());
                lineCounter++;
            }

            if (lineCounter == 3) {
                users.add(user);
                lineCounter = 0;
            }
        }
        return users;
    }

    public List<Review> readFileReview(Path path) {
        List<Review> reviews = new ArrayList<>();
        List<String> lines;
        int lineCounter = 0;
        int idCounter = 1;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Review review = null;
        for (String line : lines) {
            line = removeBOM(line);

            if (lineCounter == 0 && !line.isEmpty()) {
                review = new Review();
                review.setId(idCounter);
                review.setMovie(line.trim());
                idCounter++;
                lineCounter++;
            } else if (lineCounter == 1 && !line.isEmpty()) {
                review.setUser(line.trim());
                lineCounter++;
            } else if (lineCounter == 2 && !line.isEmpty()) {
                review.setDescription(line.trim());
                lineCounter++;
            }

            if (lineCounter == 3) {
                reviews.add(review);
                lineCounter = 0;
            }
        }
        return reviews;
    }


    private String removeBOM(String line) {
        if (line.startsWith(UTF8_BOM)) {
            line = line.substring(1);
        }
        return line;
    }
}
