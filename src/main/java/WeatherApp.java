
import java.io.FileWriter;
import java.io.IOException;

    public class WeatherApp {
        public static void main(String[] args) throws IOException {
// данные о погоде, полученные ранее
            String city = "Краснодар";
            String weather = "Пасмурно. (100%) Без существенных осадков. (0.3 мм.)";
            double temp = 20;
            int pres =  750;
            double hum = 0.88;
            String win = "[Ю-В] 2-4 м/с";

// путь к файлу CSV
            String filename = "weather.csv";
// запись данных в CSV файл
            FileWriter csvWriter = new FileWriter(filename);
            csvWriter.append("City");
            csvWriter.append(",");
            csvWriter.append("Weather conditions");
            csvWriter.append(",");
            csvWriter.append("Temperature");
            csvWriter.append(",");
            csvWriter.append("Pressure");
            csvWriter.append(",");
            csvWriter.append("Humidity");
            csvWriter.append(",");
            csvWriter.append("Wind");
            csvWriter.append("\n");

            csvWriter.append(city);
            csvWriter.append(",");
            csvWriter.append(weather);
            csvWriter.append(",");
            csvWriter.append(Double.toString(temp));
            csvWriter.append(",");
            csvWriter.append(Integer.toString(pres));
            csvWriter.append(",");
            csvWriter.append(Double.toString(hum));
            csvWriter.append(",");
            csvWriter.append(win);
            csvWriter.append("\n");


            csvWriter.flush();
            csvWriter.close();
        }
    }
