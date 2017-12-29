/**
 * Java2. HomeWork3-2.
 * @author Yury Mitroshin
 * @version dated Dec 29, 2017
 * @link https://github.com/yurchess/gb_java2_homework3-2
 */

import java.util.Arrays;

public class HomeWork3 {
    private static final String FILE_NAME = "phone_numbers.xml";


    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Ivanov", "81234567894");
        phoneBook.add("Petrov", "85493354825");
        phoneBook.add("Sidorov", "86549846123");
        phoneBook.add("Ivanov", "865484632101");

        System.out.println(Arrays.toString(phoneBook.get("Ivanov")));

        phoneBook.exportToXML(FILE_NAME);
    }
}
