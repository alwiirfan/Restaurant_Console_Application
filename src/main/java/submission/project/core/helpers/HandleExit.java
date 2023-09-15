package submission.project.core.helpers;

import java.util.Scanner;

public class HandleExit {

    public static boolean promptToExit(Scanner scanner) {
        System.out.print("Kembali ke menu awal? (y/n): ");
        String response = scanner.nextLine();
        return "y".equalsIgnoreCase(response.trim());
    }

}
