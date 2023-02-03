package com.timesheet.processes.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.previous;

public class LocalDrive {


        private static String lastCreatedDir;
        private static LocalDate dirCreationTime;
        record PathAndTime(Path path, FileTime time) { }

        public static void getLastCreatedDir() throws Exception {

        Path path = Paths.get("C:\\Mythili\\Timesheets&Invoices\\Netrovert\\Timesheets");
        List<PathAndTime> list = new ArrayList<>();

        // listing all elements of a directory
        try (var files = Files.list(path)) {
            files.forEach(p -> {
                // filtering for directories
                if (Files.isDirectory(p)) {
                    try {
                        // get file creation time
                        BasicFileAttributes attr = Files.readAttributes(p,BasicFileAttributes.class);
                        FileTime fileTime = attr.creationTime();

                        // saving the path and time in a list
                        list.add(new PathAndTime(p, fileTime));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        // finding the minimum of the list with respect of time
        var result = list.stream().min((a, b) -> {
            return b.time.compareTo(a.time);
        });

        result.ifPresentOrElse(entry -> {
            lastCreatedDir = String.valueOf(entry.path);
            dirCreationTime = LocalDate.ofInstant(entry.time.toInstant(), ZoneId.systemDefault());
            System.out.println("Last created Folder path and time :: " + lastCreatedDir + " && " + dirCreationTime);
        }, () -> {  System.out.println("No directories found");
        });
            LocalDate thisPastSunday = dirCreationTime.with(previous(SUNDAY));
            System.out.println(thisPastSunday);
    }

        public static String createFolder () throws Exception {

        getLastCreatedDir();

        Integer a = 0, folderName;
        String folName, directory;

        //storing index to get the folder name
        a = lastCreatedDir.lastIndexOf("\\");

        //getting and converting last modified folder name as integer
        folderName = Integer.parseInt(lastCreatedDir.substring(a + 1));

        //incrementing the folder name and converting as a string
        folName = Integer.toString(folderName + 1);
        directory = "C:\\Mythili\\Timesheets&Invoices\\Netrovert\\Timesheets\\" + folName;

        //calculating days of difference
        Period period = Period.between(dirCreationTime, LocalDate.now());
        Integer diff = Math.abs(period.getDays());
        System.out.println("Days difference: " + diff);

        if (diff <= 11 && diff >= 15) {

            //creating new folder in the directory
            File file = new File(directory);
            file.mkdir();
            System.out.println("New folder " + folName + " has been created in directory: " + directory);

        } else folName = folderName.toString();

        System.out.println("Folder can be created once in two weeks!!!");

        return folName;
    }
}

