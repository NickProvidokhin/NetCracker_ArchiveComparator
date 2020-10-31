package ru.ncedu.providokhin.archivecomparator;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> filePaths = new ArrayList<>();
        for (String str:
             args) {
            filePaths.add(str);
        }
        ReadArchive readArchive = new ReadArchive();
        readArchive.toReadFilesName(filePaths);
        readArchive.toReadNamesInArchive();
        readArchive.archiveComparator();
        System.out.println("TO DO");


    }
}
