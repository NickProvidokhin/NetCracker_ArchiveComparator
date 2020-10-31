package ru.ncedu.providokhin.archivecomparator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadArchive {
    public Map<String, List<FileInArchive>> twoArchives = new HashMap<String, List<FileInArchive>>();
    //данное поле отвечает за представление обоих архивов
    // архив хранится в виде имени файла и последовательности файлов внутри
    List<File> files = new ArrayList<>();

    public void toReadFilesName(ArrayList filePaths) {
        files.add(new File(filePaths.get(0).toString()));
        files.add(new File(filePaths.get(1).toString()));
    }


    public void toReadNamesInArchive() {

        try {
            for (File file : files) {
                InputStream input = new FileInputStream(file);
                ZipInputStream zip = new ZipInputStream(input);
                ZipEntry entry;
                String name;
                long size;
                String nameOfArchive = file.getName();
                List<FileInArchive> allFilesInArchive = new ArrayList<>();
                while ((entry = zip.getNextEntry()) != null) {
                    FileInArchive temp = new FileInArchive(entry.getSize(), entry.getName());
                    allFilesInArchive.add(temp);
                }
                twoArchives.put(nameOfArchive, allFilesInArchive);
                zip.close();
                input.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void archiveComparator() {
        String firstArchive = files.get(0).toString();
        String secondArchive = files.get(1).toString();

        ArrayList<FileInArchive> arr1 = (ArrayList<FileInArchive>) twoArchives.get(firstArchive);
        ArrayList<FileInArchive> arr2 = (ArrayList<FileInArchive>) twoArchives.get(secondArchive);
        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.size(); j++) {
                if (arr1.get(i).nameOfFile.equals(arr2.get(j).nameOfFile)) {
                    if (arr1.get(i).sizeOfFile == arr2.get(j).sizeOfFile) {
                        //файлы равны полностью

                        System.out.println("файлы равны полностью "+arr1.get(i).nameOfFile+" "+arr2.get(j).nameOfFile);
                    } else {
                        //значит файл отредактировали
                        System.out.println("файл отредактировали "+arr1.get(i).nameOfFile+" "+arr2.get(j).nameOfFile);
                    }
                } else {
                    if (arr1.get(i).sizeOfFile == arr2.get(j).sizeOfFile) {
                        System.out.println("Переименовали "+arr1.get(i).nameOfFile+" "+arr2.get(j).nameOfFile);
                    }

                }
            }
        }
    }

}
