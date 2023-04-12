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
            String[] header = {"�������� �������", "�����������", "��������", "���������", "�����"};
            writer.writeNext(header);

            // add data to csv
            String[] evening = {"��������. (100%) ��� �������.", "+15..+17", "753", "75%", "[�] 1-3 �/�"};
            writer.writeNext(evening);
            String[] night = {"��������� ����������. (37%) ��� �������.", "+12..+14", "756", "66%", "[�] 7-9 �/�"};
            writer.writeNext(night);
            String[] morning = {"���������� ���������� (43%) ��� �������.", "+9..+11", "755", "74%", "[�] 6-8 �/�"};
            writer.writeNext(morning);
            String[] afternoon = {"�������. (88%) ��� �������.", "+15..+17", "754", "64%", "[�] 6-8 �/�"};
            writer.writeNext(afternoon);


            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



