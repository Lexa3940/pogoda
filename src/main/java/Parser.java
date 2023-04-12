import java.io.IOException;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// индекс - номер строки(элемента) в листе values, с которого надо начать печать и на котором сейчас находимся. Изначально это 0ой.
// iterationCount - количество итераций
// page - страница
// эл. tableWth - погодная таблица
// все элементы tr с классом wt - названия столбцов на сайте
//wt - названия столбцов, top - значения
//values (строки утро, день, вечер, ночь) хранится на сайте в tr с параметром valign/top

public class Parser {


    public static void main(String[] args) throws Exception {
        Document page = getPage();  // запрашиваем всю страницу с погодой
        // css query language из библиотеки jsoup. Позволяет делать запросы к стр и с их помощью доставать из стр нужные данные
        Element tableWth = page.select("table[class=wt]").first(); // из стр page достаем элемент tableWth с классом wt
        //select - выборка данных - выбор со стр первого (.first) элемента table с классом wt (класс wt в коде сайта)
        System.out.println(tableWth);
        Elements names = tableWth.select("tr[class=wth]");// запрос элементов Names (нужная нам инф)
        //Выбор из таблицы только тех tr, у которых класс wth
        Elements values = tableWth.select("tr[valign=top]");
        // Выбор из таблицы всех tr, у которых valign = top
        int index = 0;

        for (Element name : names) {
            String dateString = name.select("th[id=dt]").text(); // все, что лежит в th блоке, будет записано в переменную date
            String date = getDateFromString(dateString); // Дата, после работы Pattern и Matcher. Ее сохранение в переменную date
            System.out.println(date + "   Погодные явления    Температура    Давление    Влажность    Ветер");
            int iterationCount = printPartValues(values, index);
            index = index = iterationCount; // индекс (точка старта, с которой надо печатать) увеличится ровно на столько, на сколько было напечатано в этом блоке элементов
        }
    }


    public static Document getPage() throws IOException { // возвращает документ, ошибки выбрасываются наверх
        String url = ("http://www.nepogoda.ru/russia/krasnodar/");
        Document page = Jsoup.parse(new URL(url), 3000); // передача объекта и время ожидания ответа
        return page;  //метод возвращает page
    }
    // Надо получить из "04.04 Суббота вечер сегодня" - "04.04"
    // 04.04
    // \d{2}.\d{2} - регулярное выражение по дате
    // \d - символьный знак. \d{2} - 2 симв. знака
    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}"); // Поиск нужной инф в тексте. для регулярного выражения
    // В класс Pattern записываем само регулярное выражение. Здесь будет храниться паттерн (шаблон), по которому нужно искать инф.
    public static String getDateFromString(String stringDate) throws Exception { // возвращает строку с нужным текстом, получает строку с лишним текстом и вырезает ее
        Matcher matcher = pattern.matcher(stringDate);
        // Создаем класс Matcher для поиска нужной инф (вхождения рег. выражения). Из Pattern запрашиваем Matcher в стр stringDate
        if (matcher.find()) {
            // если что-то нашлось
            return matcher.group();
            // то Matcher найденное группирует, возвращает
        }
        throw new Exception("Can't extract date from string!");// Исключение - если не ничего не нашлось
    }



    public static int printPartValues(Elements values, int index) { // метод, который будет печатать часть значений
        // индекс - номер строки(элемента) в листе values, с которого надо начать печать и на котором сейчас находимся. Изначально это 0ой.
        int iterationCount = 4;
        if (index == 0) { // с чего начинать печать
            Element valueLn = values.get(0);// забираем влайн элементы по индексу 0. Индекс - что надо печатать + то, сколько уже напечатали

            switch (valueLn.text().split(" ")[0]) {
                case ("День"):
                    iterationCount = 3; // проверка текста: утро ли это: если есть утро, то всего 3 операции (обед, вечер, ночь)
                    break;
                case ("Вечер"):
                    iterationCount = 2;
                    break;
                case ("Ночь"):
                    iterationCount = 1;
                    break;
            }
        }
        for (int i = 0; i < iterationCount; i++) {
            // i
            Element valueLine = values.get(index + i); // забираем влайн элементы по индексу(0,1,2). Индекс - что надо печатать + то, сколько уже напечатали
            for (Element td : valueLine.select("td")) { // печатаем значения для утра с индекса
                System.out.print(td.text() + "    "); // вывод элементов при итерации, где индекс = 0
            }
            System.out.println();// пропуск строки
        }
        return iterationCount;
    }
}

