package com.ibk.pds;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import au.com.bytecode.opencsv.CSVWriter;
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobWorldTest {
	private String filename = "mycsv.csv";
	@Test
	public void Test() {
		//private
		List<String[]> listArray = new ArrayList<String[]>();

		String[] strArray = {"1","2"};
		String[] strArray2 = {"3","4"};
		
		listArray.add(strArray);
		
		writeCsv(listArray);
	}
	public void writeCsv(List<String[]> data) {
        try {
            CSVWriter cw = new CSVWriter(new FileWriter(filename), ',', '"');
            Iterator<String[]> it = data.iterator();
            try {
                while (it.hasNext()) {
                    String[] s = (String[]) it.next();
                    cw.writeNext(s);
                }
            } finally {
                cw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}
