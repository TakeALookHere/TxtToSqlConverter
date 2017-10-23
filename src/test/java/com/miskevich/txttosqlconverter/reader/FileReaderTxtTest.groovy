package com.miskevich.txttosqlconverter.reader

import org.testng.annotations.Test

import java.nio.file.Paths

class FileReaderTxtTest {
    FileReaderTxt fileReaderTxt = new FileReaderTxt()

    @Test
    void testReadFileMovie() {
        fileReaderTxt.readFileMovie(Paths.get('/home/user/JULIA/MovieLand/movie.txt'))
    }

    @Test
    void testReadFileGenre(){
        fileReaderTxt.readFileGenre(Paths.get('/home/user/JULIA/MovieLand/genre.txt'))
    }

    @Test
    void testGenerateCountriesFromScratch(){
        fileReaderTxt.generateCountriesFromScratch()
    }

    @Test
    void testReadFileUser() {
        fileReaderTxt.readFileUser(Paths.get('/home/user/JULIA/MovieLand/user.txt'))
    }

    @Test
    void testReadFileReview() {
        fileReaderTxt.readFileReview(Paths.get('/home/user/JULIA/MovieLand/review.txt'))
    }
}
