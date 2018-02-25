package com.loan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.loan.model.Lender;

/**
 * Reads a CSV file that contains a list of offers.
 */
public class CsvReader {

    /**
     * @param path the path to the CSV
     * @return a list of lenders that can provide loans
     * @todo handling for invalid CSV files
     */
    public Lender[] readCsv(String path) throws IOException, FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<Lender> lenders = new ArrayList<>();
        String line = null;

        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            // skip the headers
            if (row[0].equals("Lender") && row[1].equals("Rate") && row[2]
                    .equals("Available")) {

                continue;
            }
            Lender lender = new Lender(row[0], new BigDecimal(row[1]), new
                    Integer(row[2]).intValue());

            lenders.add(lender);
        }

        return lenders.toArray(new Lender[lenders.size()]);
    }

}
