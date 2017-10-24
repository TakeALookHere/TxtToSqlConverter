package com.miskevich.txttosqlconverter.generator

import com.miskevich.txttosqlconverter.reader.FileReaderTxt
import com.miskevich.txttosqlconverter.writer.SQLFileWriter
import org.testng.annotations.Test

import java.nio.file.Paths

class SQLGeneratorTest {
    SQLGenerator sqlGenerator = new SQLGenerator()
    FileReaderTxt fileReaderTxt = new FileReaderTxt()
    SQLFileWriter sqlFileWriter = new SQLFileWriter()
    private static final String homeDirectory = "P:/Users/dp-ptcstd-10/Downloads"

    @Test
    void testGenerateMovie() {
        def posters = fileReaderTxt.readFilePoster(Paths.get(homeDirectory, 'poster.txt'))
        def movies = fileReaderTxt.readFileMovie(Paths.get(homeDirectory, 'movie.txt'))
        def movieSQL = sqlGenerator.generateMovie(movies, posters)
        sqlFileWriter.writeSQLFile('movie.sql', movieSQL)
    }

    @Test
    void testGenerateGenre() {
        def genreSQL = sqlGenerator.generateGenre(fileReaderTxt.readFileGenre(Paths.get(homeDirectory, 'genre.txt')))
        sqlFileWriter.writeSQLFile('genre.sql', genreSQL)
    }

    @Test
    void testGenerateCountry() {
        def countrySQL = sqlGenerator.generateCountry(fileReaderTxt.generateCountriesFromScratch())
        sqlFileWriter.writeSQLFile('country.sql', countrySQL)
    }

    @Test
    void testGenerateMovieGenre() {
        def movies = fileReaderTxt.readFileMovie(Paths.get(homeDirectory, 'movie.txt'))
        def genres = fileReaderTxt.readFileGenre(Paths.get(homeDirectory, 'genre.txt'))
        def movieGenreSQL = sqlGenerator.generateMovieGenre(movies, genres)
        sqlFileWriter.writeSQLFile('movie_genre.sql', movieGenreSQL)
    }

    @Test
    void testGenerateMovieCountry() {
        def movies = fileReaderTxt.readFileMovie(Paths.get(homeDirectory, 'movie.txt'))
        def countries = fileReaderTxt.generateCountriesFromScratch()
        def movieCountrySQL = sqlGenerator.generateMovieCountry(movies, countries)
        sqlFileWriter.writeSQLFile('movie_country.sql', movieCountrySQL)
    }

    @Test
    void testGenerateUser() {
        def users = fileReaderTxt.readFileUser(Paths.get(homeDirectory, 'user.txt'))
        def userSQL = sqlGenerator.generateUser(users)
        sqlFileWriter.writeSQLFile('user.sql', userSQL)
    }

    @Test
    void testGenerateReview() {
        def reviews = fileReaderTxt.readFileReview(Paths.get(homeDirectory, 'review.txt'))
        def movies = fileReaderTxt.readFileMovie(Paths.get(homeDirectory, 'movie.txt'))
        def users = fileReaderTxt.readFileUser(Paths.get(homeDirectory, 'user.txt'))
        def reviewSQL = sqlGenerator.generateReview(reviews, movies, users)
        sqlFileWriter.writeSQLFile('review.sql', reviewSQL)
    }
}
