import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class sample {
    private static Writer csv;

    public static void main(String[] args) {}
    public static void writeDataAtOnce (String filename){
        File file = new File(filename);
        try {
            FileWriter outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(sample.csv);

            // adding header to csv
            String[] header = {"Погодные явления", "Температура", "Давление", "Влажность", "Ветер"};
            writer.writeNext(header);

            // add data to csv
            String[] evening = {"Пасмурно. (100%) Без осадков.", "+15..+17", "753", "75%", "[Ю] 1-3 м/с"};
            writer.writeNext(evening);
            String[] night = {"Небольшая облачность. (37%) Без осадков.", "+12..+14", "756", "66%", "[В] 7-9 м/с"};
            writer.writeNext(night);
            String[] morning = {"Переменная облачность (43%) Без осадков.", "+9..+11", "755", "74%", "[В] 6-8 м/с"};
            writer.writeNext(morning);
            String[] afternoon = {"Облачно. (88%) Без осадков.", "+15..+17", "754", "64%", "[В] 6-8 м/с"};
            writer.writeNext(afternoon);


            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



