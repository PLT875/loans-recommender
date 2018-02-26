package com.loan;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Unit tests for Main.
 */
public class MainTest {
    private final ByteArrayOutputStream os = new ByteArrayOutputStream();
    private final ByteArrayOutputStream er = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(os));
        System.setErr(new PrintStream(er));
    }

    @Test
    public void testMain_whenCsvNotFound() {
        Main.main(new String[]{"no-such.csv", "1000"});

        // then
        String exp = "no-such.csv (No such file or directory)\n";
        assertTrue("the error message should be printed", exp.equals(er
                .toString()));
    }

    @Test
    public void testMain_whenInvalidLoanRequest() {
        String path = getClass().getClassLoader().getResource("market-data.csv").getPath();
        Main.main(new String[]{path, "900"});

        // then
        assertTrue("the invalid loan message should be printed", Main
                .INVALID_LOAN_QUEST.equals(er.toString()));
    }

    @Test
    public void testMain_whenNoQuoteAvailable() {
        String path = getClass().getClassLoader().getResource("market-data.csv").getPath();
        Main.main(new String[]{path, "3000"});

        // then
        assertTrue("the no quote available message should be printed",
                Main.NO_QUOTE_AVAILABLE.equals(er.toString()));
    }

    @Test
    public void testMain_whenQuoteAvailable() {
        String path = getClass().getClassLoader().getResource("market-data.csv").getPath();
        Main.main(new String[]{path, "1000"});

        String exp = "Requested amount: £1000\n" +
                "Rate: 7.0%\n" +
                "Monthly repayment: £30.89\n" +
                "Total repayment: £1112.04\n";

        // then
        assertTrue("a quote should be returned",
                exp.equals(os.toString()));
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

}
