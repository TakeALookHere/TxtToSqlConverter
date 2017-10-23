package com.miskevich.txttosqlconverter.generator

import com.miskevich.txttosqlconverter.reader.FileReaderTxt
import com.miskevich.txttosqlconverter.writer.SQLFileWriter
import org.testng.annotations.Test

import java.nio.file.Paths

class SQLGeneratorTest {
    SQLGenerator sqlGenerator = new SQLGenerator()
    FileReaderTxt fileReaderTxt = new FileReaderTxt()
    SQLFileWriter sqlFileWriter = new SQLFileWriter()

    @Test
    void testGenerateMovie() {
        def movieSQL = sqlGenerator.generateMovie(fileReaderTxt.readFileMovie(Paths.get('/home/user/JULIA/MovieLand/movie.txt')))
        sqlFileWriter.writeSQLFile('movie.sql', movieSQL)
    }

    @Test
    void testGenerateGenre() {
        def genreSQL = sqlGenerator.generateGenre(fileReaderTxt.readFileGenre(Paths.get('/home/user/JULIA/MovieLand/genre.txt')))
        sqlFileWriter.writeSQLFile('genre.sql', genreSQL)
    }

    @Test
    void testGenerateMovieGenre() {
        def movies = fileReaderTxt.readFileMovie(Paths.get('/home/user/JULIA/MovieLand/movie.txt'))
        def genres = fileReaderTxt.readFileGenre(Paths.get('/home/user/JULIA/MovieLand/genre.txt'))
        def movieGenreSQL = sqlGenerator.generateMovieGenre(movies, genres)
        sqlFileWriter.writeSQLFile('movie_genre.sql', movieGenreSQL)
    }

    @Test
    void testGenerateMovieCountry() {
        def movies = fileReaderTxt.readFileMovie(Paths.get('/home/user/JULIA/MovieLand/movie.txt'))
        def countries = fileReaderTxt.generateCountriesFromScratch()
        def movieCountrySQL = sqlGenerator.generateMovieCountry(movies, countries)
        sqlFileWriter.writeSQLFile('movie_country.sql', movieCountrySQL)
    }

    @Test
    void testGenerateUser() {
        def users = fileReaderTxt.readFileUser(Paths.get('/home/user/JULIA/MovieLand/user.txt'))
        def userSQL = sqlGenerator.generateUser(users)
        sqlFileWriter.writeSQLFile('user.sql', userSQL)
    }

    @Test
    void testGenerateReview() {
        def reviews = fileReaderTxt.readFileReview(Paths.get('/home/user/JULIA/MovieLand/review.txt'))
        def movies = fileReaderTxt.readFileMovie(Paths.get('/home/user/JULIA/MovieLand/movie.txt'))
        def users = fileReaderTxt.readFileUser(Paths.get('/home/user/JULIA/MovieLand/user.txt'))
        def reviewSQL = sqlGenerator.generateReview(reviews, movies, users)
        sqlFileWriter.writeSQLFile('review.sql', reviewSQL)
    }
}
