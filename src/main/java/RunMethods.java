import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunMethods {

    public static List<String> getTests(String path) {
        Scanner in = null;
        List<String> tests = new ArrayList<String>();
        String temp = "";
        try {
            in = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            if (!in.hasNext()) {
                break;
            }
            temp = in.nextLine();
            try {
                tests.add(temp);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        return tests;
    }

    public static void runTestMethods(Object object, List<String> tests) {
        for (String testName: tests) {
            System.out.println("Test " + testName + " started");
            try {
                ChromeOptions options = new ChromeOptions();
                //options.setHeadless(true);
                ChromeDriver wd = new ChromeDriver(options);
                WebDriverRunner.setWebDriver(wd);

                object.getClass().getDeclaredMethod(testName).invoke(object);

                WebDriverRunner.closeWebDriver();
            } catch (NoSuchMethodException e) {
                System.out.println(e.getCause().toString());
            } catch (IllegalAccessException e) {
                System.out.println(e.getCause().toString());
            } catch (InvocationTargetException e) {
                System.out.println(e.getCause().toString());
            } finally {
                WebDriverRunner.closeWebDriver();
            }
            System.out.println("Test " + testName + " ended\n");
        }
    }

}
