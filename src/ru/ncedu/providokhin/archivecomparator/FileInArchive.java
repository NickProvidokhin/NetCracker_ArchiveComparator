package ru.ncedu.providokhin.archivecomparator;

//
//класс описывабщий один файл в архиве
public class FileInArchive {
    public long sizeOfFile;
    public String nameOfFile;

    public FileInArchive(long sizeOfFile, String nameOfFile) {
        this.sizeOfFile = sizeOfFile;
        this.nameOfFile = nameOfFile;
    }
}
