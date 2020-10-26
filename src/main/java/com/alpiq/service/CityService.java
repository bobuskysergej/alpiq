package com.alpiq.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.alpiq.domain.City;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private static final String DATA_FILE_PATH = "data/cities15000.csv";
    private static final char COMMA_SEPARATOR = ',';

    private List<City> cities;

    public CityService() throws Exception {
        this.cities = new ArrayList<>();
        this.readCities();
    }

    public List<City> getCities() {
        return cities;
    }

    private void readCities() throws Exception {
        Reader reader = null;
        CSVReader csvReader = null;
        try {
            reader = getFileReader();
            csvReader = getCSVReader(reader);
            csvReader.forEach(line -> cities.add(unmarshalCity(line)));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            closeReaders(reader, csvReader);
        }
    }

    private City unmarshalCity(String[] line) {
        String firstValue = line[0];
        String lastValue = line[line.length - 1];
        String[] lastValueParts = lastValue.split("\t");
        String name = firstValue.split("\t")[1];
        String country = lastValueParts[lastValueParts.length - 2];
        return new City(name, country);
    }

    private CSVReader getCSVReader(Reader reader) {
        CSVParser parser = new CSVParserBuilder().withSeparator(COMMA_SEPARATOR).withIgnoreQuotations(true).build();
        return new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build();
    }

    private BufferedReader getFileReader() throws IOException, URISyntaxException {
        return Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(DATA_FILE_PATH).toURI()));
    }

    private void closeReaders(Reader reader, CSVReader csvReader) throws IOException {
        if (reader != null) {
            reader.close();
        }
        if (csvReader != null) {
            csvReader.close();
        }
    }
}
