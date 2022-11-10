import java.util.Comparator;

public class FileData implements Comparable<FileData> {
    private String fileName = "UnknownFile.txt";
    private String filePath = "/UnknownPath";
    private int fileSize = 0;

    //region Constructors
    public FileData() {
    }

    public FileData(String fileName) {
        this.fileName = fileName;
    }

    public FileData(String fileName, String filePath, int fileSize) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
    //endregion

    @Override
    public String toString() {
        return "{Name: " + getFileName() + ", path: " + getFilePath() + ", size: " + getFileSize() + "}";
    }

    @Override
    public int compareTo(FileData o) {
        return (int) (this.getFileSize() - o.getFileSize());
    }

    //region Getters/Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    //endregion
}
