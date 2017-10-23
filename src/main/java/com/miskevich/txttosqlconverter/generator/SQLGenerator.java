package com.miskevich.txttosqlconverter.generator;

import com.miskevich.txttosqlconverter.entity.Movie;
import com.miskevich.txttosqlconverter.entity.Review;
import com.miskevich.txttosqlconverter.entity.User;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class SQLGenerator {
    private static final String MOVIE_PREFIX = "insert into movie (id, name_russian, name_native, released_date, plot, rating, price)\n" +
            "values(";
    private static final String GENRE_PREFIX = "insert into genre (id, name) values(";
    private static final String MOVIE_GENRE_PREFIX = "insert into movie_genre(movie_id, genre_id) values(";
    private static final String MOVIE_COUNTRY_PREFIX = "insert into movie_country (movie_id, country_id) values(";
    private static final String USER_PREFIX = "insert into user(id, nickname, email, password)\n" +
            "values(";
    private static final String REVIEW_PREFIX = "insert into review(id, movie_id, user_id, description)\n" +
            "values(";

    public String generateMovie(List<Movie> movies){
        StringBuilder movieSQL = new StringBuilder();
        for (Movie movie : movies){
            movieSQL.append(MOVIE_PREFIX)
                    .append(movie.getId())
                    .append(", \"")
                    .append(movie.getNameRussian())
                    .append("\", \"")
                    .append(movie.getNameNative())
                    .append("\", date'")
                    .append(movie.getReleasedDate())
                    .append("', \"")
                    .append(movie.getPlot())
                    .append("\", ")
                    .append(movie.getRating())
                    .append(", ")
                    .append(movie.getPrice())
                    .append(");\n");
        }
        return movieSQL.toString();
    }

    public String generateGenre(Map<String, Integer> genres){
        StringBuilder genreSQL = new StringBuilder();
        for(Map.Entry<String, Integer> genre : genres.entrySet()){
            genreSQL.append(GENRE_PREFIX)
                    .append(genre.getValue())
                    .append(", \"")
                    .append(genre.getKey())
                    .append("\");\n");
        }
        return genreSQL.toString();
    }

    public String generateMovieGenre(List<Movie> movies, Map<String, Integer> genres){
        StringBuilder movieGenreSQL = new StringBuilder();
        for (Movie movie : movies){
            List<String> genresInMovie = movie.getGenres();
            for (String genreInMovie : genresInMovie){
                movieGenreSQL.append(MOVIE_GENRE_PREFIX)
                        .append(movie.getId())
                        .append(", ")
                        .append(genres.get(genreInMovie))
                        .append(");\n");
            }
        }
        return movieGenreSQL.toString();
    }

    public String generateMovieCountry(List<Movie> movies, Map<String, Integer> countries){
        StringBuilder movieCountrySQL = new StringBuilder();
        for (Movie movie : movies){
            List<String> countriesInMovie = movie.getCountries();
            for (String countryInMovie : countriesInMovie){
                movieCountrySQL.append(MOVIE_COUNTRY_PREFIX)
                        .append(movie.getId())
                        .append(", ")
                        .append(countries.get(countryInMovie))
                        .append(");\n");
            }
        }
        return movieCountrySQL.toString();
    }

    public String generateUser(List<User> users){
        StringBuilder userSQL = new StringBuilder();
        for (User user : users){
            userSQL.append(USER_PREFIX)
                    .append(user.getId())
                    .append(", \"")
                    .append(user.getNickname())
                    .append("\", \"")
                    .append(user.getEmail())
                    .append("\", AES_ENCRYPT(\"")
                    .append(user.getPassword())
                    .append("\", \"tlas_dnal_eivom\"));\n");
        }
        return userSQL.toString();
    }

    public String generateReview(List<Review> reviews, List<Movie> movies, List<User> users){
        StringBuilder reviewSQL = new StringBuilder();
        for (Review review : reviews){
            reviewSQL.append(REVIEW_PREFIX)
                    .append(review.getId())
                    .append(", ")
                    .append(getMovieIdByNameNative(review.getMovie(), movies))
                    .append(", ")
                    .append(getUserIdByNickname(review.getUser(), users))
                    .append(", \"")
                    .append(review.getDescription())
                    .append("\");\n");
        }
        return reviewSQL.toString();
    }

    private int getMovieIdByNameNative(String nameRussian, List<Movie> movies){
        for (Movie movie : movies){
            if(movie.getNameRussian().equals(nameRussian)){
                return movie.getId();
            }
        }
        throw new NoSuchElementException("No movie was found with nameRussian: " + nameRussian);
    }

    private int getUserIdByNickname(String nickname, List<User> users){
        for (User user : users){
            if(user.getNickname().equals(nickname)){
                return user.getId();
            }
        }
        throw new NoSuchElementException("No user was found with nickname: " + nickname);
    }
}
