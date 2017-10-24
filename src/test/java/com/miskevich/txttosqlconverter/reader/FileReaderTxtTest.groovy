package com.miskevich.txttosqlconverter.reader

import org.testng.annotations.Test

import java.nio.file.Paths

class FileReaderTxtTest {
    FileReaderTxt fileReaderTxt = new FileReaderTxt()
    private static final String homeDirectory = "P:/Users/dp-ptcstd-10/Downloads"

    @Test
    void testReadFileMovie() {
        fileReaderTxt.readFileMovie(Paths.get(homeDirectory, 'movie.txt'))
    }

    @Test
    void testReadFileGenre(){
        fileReaderTxt.readFileGenre(Paths.get(homeDirectory, 'genre.txt'))
    }

    @Test
    void testGenerateCountriesFromScratch(){
        fileReaderTxt.generateCountriesFromScratch()
    }

    @Test
    void testReadFileUser() {
        fileReaderTxt.readFileUser(Paths.get(homeDirectory, 'user.txt'))
    }

    @Test
    void testReadFileReview() {
        fileReaderTxt.readFileReview(Paths.get(homeDirectory, 'review.txt'))
    }

    @Test
    void testReadFilePoster() {
        fileReaderTxt.readFilePoster(Paths.get(homeDirectory, 'poster.txt'))
    }
}
