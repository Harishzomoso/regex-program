import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
 

public class RegexFileSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String homeDir = System.getProperty("user.home");
        File home = new File(homeDir);

        System.out.println("Welcome to Regex File Search in your home directory: " + homeDir);
        System.out.println("Type 'exit' to stop the program.\n");

        while (true) {
            System.out.print("Enter regex: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program.");
                break;
            }

            try {
                Pattern pattern = Pattern.compile(input);
                searchFiles(home, pattern);
            } catch (PatternSyntaxException e) {
                System.out.println("Invalid regex syntax. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Recursively searches through the directory for files matching the regex pattern.
     *
     * @param dir     the directory to search
     * @param pattern the regex pattern to match filenames
     */
    private static void searchFiles(File dir, Pattern pattern) {
        if (dir == null || !dir.exists()) return;

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(file, pattern); // Recursive call for subdirectories
            } else if (pattern.matcher(file.getName()).matches()) {
                System.out.println(file.getAbsolutePath()); // Print full path
            }
        }
    }
}
