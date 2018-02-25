package com.loan;

import org.junit.Before;
import org.junit.Test;
import com.loan.model.Lender;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit tests for CsvReader.
 */
public class CsvReaderTest {
    private String path;
    private CsvReader reader;

    @Before
    public void setup() {
        path = getClass().getClassLoader().getResource("market-data.csv").getPath();
        reader = new CsvReader();
    }

    @Test
    public void testReadCsv() throws FileNotFoundException, IOException {
        Lender[] lenders = reader.readCsv(path);
        assertEquals(7, lenders.length);

        // check first row
        assertEquals("Bob", lenders[0].getLender());
        assertEquals(new BigDecimal("0.075"), lenders[0].getRate());
        assertEquals(640, lenders[0].getAvailable());

        // check the middle row
        assertEquals("Mary", lenders[3].getLender());
        assertEquals(new BigDecimal("0.104"), lenders[3].getRate());
        assertEquals(170, lenders[3].getAvailable());

        // check the last row
        assertEquals("Angela", lenders[6].getLender());
        assertEquals(new BigDecimal("0.071"), lenders[6].getRate());
        assertEquals(60, lenders[6].getAvailable());

    }
}
