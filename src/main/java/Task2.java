import java.util.List;

public class Task2 {

    public static void main(String[] args) {
        RunMethods methodRunner = new RunMethods();
        String testsPath = "conf/jobs.yml";
        List<String> tests = methodRunner.getTests(testsPath);
        LkTest testsObject = new LkTest();
        methodRunner.runTestMethods(testsObject, tests);
    }
}
