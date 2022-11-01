package medialab.minesweeper.utilities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileParser {
    private final Path filePath;
    private final static Charset encoding = StandardCharsets.UTF_8;

    public FileParser(String fileName) throws InvalidPathException {
        this.filePath = Paths.get(fileName);
    }

    public ArrayList<String> getLines() throws IOException {
        ArrayList<String> res = new ArrayList<>();

        try (Scanner scanner = new Scanner(filePath, encoding)) {
            while (scanner.hasNextLine()) {
                res.add(scanner.nextLine());
            }
        }

        return res;
    }
}
