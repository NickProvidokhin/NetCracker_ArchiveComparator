package ru.ncedu.providokhin.archivecomparator;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> filePaths = new ArrayList<>();
        for (String str:
             args) {
            filePaths.add(str);
        }
        Archive archive = new Archive();
        archive.toReadFilesName(filePaths);
        archive.toReadNamesInArchive();
        archive.archiveComparator();
        System.out.println("TO DO");


    }
}
