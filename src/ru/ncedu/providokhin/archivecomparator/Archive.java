package ru.ncedu.providokhin.archivecomparator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Archive {
    public Map<String, List<FileInArchive>> twoArchives = new HashMap<String, List<FileInArchive>>();
    //данное поле отвечает за представление обоих архивов
    // архив хранится в виде имени файла и последовательности файлов внутри
    List<File> files = new ArrayList<>();
    File report = new File("Report.txt");

    public void toReadFilesName(ArrayList filePaths) {
        files.add(new File(filePaths.get(0).toString()));
        files.add(new File(filePaths.get(1).toString()));
    }

    public void toStartBuildReport() {
        String firstArchive = files.get(0).toString();
        String secondArchive = files.get(1).toString();

        ArrayList<FileInArchive> arr1 = (ArrayList<FileInArchive>) twoArchives.get(firstArchive);
        ArrayList<FileInArchive> arr2 = (ArrayList<FileInArchive>) twoArchives.get(secondArchive);
        report.delete();

        try {
            FileWriter writer = new FileWriter(report, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(firstArchive + "\n");
            for (int i = 0; i < arr1.size(); i++) {
                bufferWriter.write(arr1.get(i).nameOfFile + "\n");
            }
            bufferWriter.write("------------/////------------" + "\n");
            bufferWriter.write(secondArchive + "\n");
            for (int i = 0; i < arr2.size(); i++) {
                bufferWriter.write(arr2.get(i).nameOfFile + "\n");
            }
            bufferWriter.write("------------/////------------" + "\n");
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }


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
        ArrayList<FileInArchive> newFiles = (ArrayList<FileInArchive>) twoArchives.get(secondArchive);
        toStartBuildReport();
        try {
            FileWriter writer = new FileWriter(report, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);


            for (int i = 0; i < arr1.size(); i++) {
                boolean flag = false;
                for (int j = 0; j < arr2.size(); j++) {

                    if (arr1.get(i).nameOfFile.equals(arr2.get(j).nameOfFile)) {
                        if (arr1.get(i).sizeOfFile == arr2.get(j).sizeOfFile) {
                            bufferWriter.write("файлы одинаковые " + arr1.get(i).nameOfFile + " " + arr2.get(j).nameOfFile + "\n");
                            flag = true;
                            newFiles.remove(j);

                        } else {
                            bufferWriter.write("файл во втором архиве отредактировали " + arr1.get(i).nameOfFile + " " + arr2.get(j).nameOfFile + "\n");
                            flag = true;
                            newFiles.remove(j);
                        }
                    } else if (arr1.get(i).sizeOfFile == arr2.get(j).sizeOfFile) {
                        bufferWriter.write(arr1.get(i).nameOfFile + " Переименовали в " + arr2.get(j).nameOfFile + "\n");
                        flag = true;
                        newFiles.remove(j);
                    }

                }
                if (flag == false) {
                    bufferWriter.write(arr1.get(i).nameOfFile + " Был удален " + "\n");
                }

            }
            if (newFiles.size() != 0) {
                bufferWriter.write("Во второй архив добавили файл(ы)\n");
                for (int i = 0; i < newFiles.size(); i++) {
                    bufferWriter.write(newFiles.get(i).nameOfFile);
                }
            }
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
