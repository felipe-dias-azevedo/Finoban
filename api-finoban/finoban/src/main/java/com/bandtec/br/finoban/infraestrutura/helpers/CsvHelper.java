package com.bandtec.br.finoban.infraestrutura.helpers;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvHelper <T> {

    public List<T> read(String fileName, Class<T> classType) throws IOException, NoSuchFileException {
        return this.read(Paths.get(fileName), classType);
    }

    public List<T> read(Path file, Class<T> classType) throws IOException {
        try (
            Reader reader = Files.newBufferedReader(file);
        ) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(classType)
                    .withSeparator(';')
                    .withQuoteChar('|')
                    .withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .build();

            return csvToBean.parse();
        }
    }

    public void write(String fileName, List<T> dataToCsv) {
        this.write(Paths.get(fileName), dataToCsv);
    }

    public void write(Path file, List<T> dataToCsv) {
        try (
            Writer writer = java.nio.file.Files.newBufferedWriter(file);
        ) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(';')
                    .withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            beanToCsv.write(dataToCsv);

        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
