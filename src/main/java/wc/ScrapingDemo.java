package wc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pramod
 * Founder: https://websitecrawler.org
 */
public class ScrapingDemo {

    private static final String FILE_PATH = "keyfile.txt";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            String apiKey = readApiKeyFromFile();

            if (apiKey == null || apiKey.isBlank()) {
                apiKey = JOptionPane.showInputDialog(
                        null,
                        "Enter your API Key (Create your free API key on websitecrawler.org from the settings page of websitecrawler.org):",
                        "API Key Required",
                        JOptionPane.PLAIN_MESSAGE
                );

                if (apiKey != null && !apiKey.isBlank()) {
                    saveApiKeyToFile(apiKey);
                } else {
                    JOptionPane.showMessageDialog(null, "API key is required. Create your free API key on websitecrawler.org "
                            + "and rerun the program. Exiting.");
                    System.exit(0);
                }
            }

            // Proceed with the API key
            System.out.println("Opening the main window");
            MainFrame frame = new MainFrame(apiKey);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setTitle("Websitecrawler.org Scraping Demo application");
            System.out.println("Using API Key: " + apiKey);
        });

    }

    private static String readApiKeyFromFile() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                return Files.readString(Paths.get(FILE_PATH)).trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveApiKeyToFile(String key) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
