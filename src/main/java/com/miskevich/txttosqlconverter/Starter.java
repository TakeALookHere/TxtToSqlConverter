package com.miskevich.txttosqlconverter;

import com.miskevich.txttosqlconverter.entity.Movie;
import com.miskevich.txttosqlconverter.entity.Review;
import com.miskevich.txttosqlconverter.entity.User;
import com.miskevich.txttosqlconverter.generator.SQLGenerator;
import com.miskevich.txttosqlconverter.reader.FileReaderTxt;
import com.miskevich.txttosqlconverter.writer.SQLFileWriter;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Starter {

    private static final String homeDirectory = "P:/Users/dp-ptcstd-10/Downloads/";

    public static void main(String[] args) {
        FileReaderTxt fileReaderTxt = new FileReaderTxt();
        SQLGenerator sqlGenerator = new SQLGenerator();
        SQLFileWriter sqlFileWriter = new SQLFileWriter();

        //get Posters
        Map<String, String> posters = fileReaderTxt.readFilePoster(Paths.get(homeDirectory, "poster.txt"));

        //movie.sql
        List<Movie> movies = fileReaderTxt.readFileMovie(Paths.get(homeDirectory, "movie.txt"));
        String movieSQL = sqlGenerator.generateMovie(movies, posters);
        sqlFileWriter.writeSQLFile("movie.sql", movieSQL);

        //genre.sql
        Map<String, Integer> genres = fileReaderTxt.readFileGenre(Paths.get(homeDirectory, "genre.txt"));
        String genreSQL = sqlGenerator.generateGenre(genres);
        sqlFileWriter.writeSQLFile("genre.sql", genreSQL);

        //movie_genre.sql
        String movieGenreSQL = sqlGenerator.generateMovieGenre(movies, genres);
        sqlFileWriter.writeSQLFile("movie_genre.sql", movieGenreSQL);

        //country.sql
        Map<String, Integer> countries = fileReaderTxt.generateCountriesFromScratch();
        String countrySQL = sqlGenerator.generateCountry(countries);
        sqlFileWriter.writeSQLFile("country.sql", countrySQL);

        //movie_country.sql
        String movieCountrySQL = sqlGenerator.generateMovieCountry(movies, countries);
        sqlFileWriter.writeSQLFile("movie_country.sql", movieCountrySQL);

        //user.sql
        List<User> users = fileReaderTxt.readFileUser(Paths.get(homeDirectory, "user.txt"));
        String userSQL = sqlGenerator.generateUser(users);
        sqlFileWriter.writeSQLFile("user.sql", userSQL);

        //review.sql
        List<Review> reviews = fileReaderTxt.readFileReview(Paths.get(homeDirectory, "review.txt"));
        String reviewSQL = sqlGenerator.generateReview(reviews, movies, users);
        sqlFileWriter.writeSQLFile("review.sql", reviewSQL);

    }
}
