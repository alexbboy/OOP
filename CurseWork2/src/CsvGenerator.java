import javax.swing.table.TableModel;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.StringJoiner;

public class CsvGenerator {
    public static void generateCsvFile(TableModel model, String fileName) throws IOException {
        generateCsvFile(model, fileName, CsvSeparator.COMMA);
    }

    public static void generateCsvFile(TableModel model, String fileName, CsvSeparator separator) throws IOException {
        fileName=fileName+".xls";
        Path path = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)
        ) {
            // write column names
            StringJoiner joiner = new StringJoiner(separator.getValue());
            for (int i = 0; i < model.getColumnCount(); i++) {
                joiner.add(model.getColumnName(i));
            }
            writer.append(joiner.toString());
            writer.newLine();

            // write data
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                joiner = new StringJoiner(separator.getValue());
                for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                    joiner.add(model.getValueAt(rowIndex, colIndex).toString());
                }
                writer.append(joiner.toString());
                writer.newLine();
            }
        }
    }
}

enum CsvSeparator {
    COMMA(","),

    TAB("\t");

    private final String separator;

    CsvSeparator(String separator) {
        this.separator = separator;
    }

    public String getValue() {
        return separator;
    }
}
