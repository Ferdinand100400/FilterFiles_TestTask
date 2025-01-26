package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class OutputFiles implements OutputFile {

    private String outputPath;
    private String outputPrefix;

    public OutputFiles(String outputPath, String outputPrefix) {
        this.outputPath = outputPath;
        this.outputPrefix = outputPrefix;
    }

    @Override
    public Path createOutputPathFile(String nameFile) throws DifferentMyException {
        Path path;
        if (outputPath.equals("")) {
            try {
                path = Path.of(ProcessingFileImpl.getDirFiles() + outputPrefix
                        + nameFile + ".txt");
            } catch (InvalidPathException e) {
                throw new DifferentMyException("Неудалось создать префикс '" + outputPrefix +
                        "' префикс содержит недопустимые символы, поэтому имена файлов созданы без префикса");
            }
        } else {
            try {
                path = Path.of(ProcessingFileImpl.getDirFiles() + outputPath + "/" + outputPrefix
                        + nameFile + ".txt");
            } catch (InvalidPathException e) {
                throw new DifferentMyException("Неудалось создать префикс '" + outputPrefix + "' или директорию '" + outputPath +
                        "' , т.к. они содержат недопустимые символы, поэтому имена файлов созданы без префикса и в текущей директории");
            }
        }
        return path;
    }

    @Override
    public void createOutputFile(Path path, List<String> textFile, boolean arg_a) throws IOException, DifferentMyException {
        if (textFile.size() != 0) {
            String text = String.join("\n", textFile) + "\n";
            Files.createDirectories(Path.of(ProcessingFileImpl.getDirFiles() + outputPath).toAbsolutePath());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            try (OutputStream outputStream = new FileOutputStream(path.toFile(), arg_a)) {
                outputStream.write(text.getBytes());
            } catch (Exception e) {
                throw new DifferentMyException("Ошибка записи в выходной файл: " + path.getFileName());
            }
        }
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public void setOutputPrefix(String outputPrefix) {
        this.outputPrefix = outputPrefix;
    }
}
