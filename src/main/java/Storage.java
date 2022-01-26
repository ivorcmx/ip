import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void restoreList(ArrayList<Task> storeList) throws IOException {
        String currentDirectory = Paths.get("Duke.java").toAbsolutePath().getParent().toString();
        String newFilePath = currentDirectory + "/duke.txt";
        // create the file if do not exist yet
        try {
            Files.createFile(Paths.get(newFilePath));
        } catch (FileAlreadyExistsException e) {
            File f = new File(newFilePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String typeOfTask = s.nextLine();
                if (typeOfTask.equals("e")) {
                    Event created = new Event(s.nextLine(), s.nextLine());
                    if (s.nextLine().equals("1")) {
                        created.setMark();
                    } else {
                        created.setUnmark();
                    }
                    storeList.add(created);
                    continue;
                } else if (typeOfTask.equals("d")) {
                    Deadline created = new Deadline(s.nextLine(), s.nextLine());
                    if (s.nextLine().equals("1")) {
                        created.setMark();
                    } else {
                        created.setUnmark();
                    }
                    storeList.add(created);
                    continue;
                } else if (typeOfTask.equals("t")) {
                    Todo created = new Todo(s.nextLine());
                    if (s.nextLine().equals("1")) {
                        created.setMark();
                    } else {
                        created.setUnmark();
                    }
                    storeList.add(created);
                    continue;
                }
            }
        }
    }

    public static void saveList(ArrayList<Task> arrlist) throws IOException {
        int sizeOfList = arrlist.size();
        String currentDirectory = Paths.get("Duke.java").toAbsolutePath().getParent().toString();
        String newFilePath = currentDirectory + "/duke.txt";
        eraseList(newFilePath);
        FileWriter fw = new FileWriter(newFilePath, true);
        for (int i = 0; i < sizeOfList; i++) {
            Task t = arrlist.get(i);
            if (t.getType() == 'e') {
                Event e = (Event) t;
                fw.write("e");
                fw.write("\n");
                fw.write(t.description);
                fw.write("\n");
                fw.write(e.at);
                fw.write("\n");
                fw.write(t.isDone ? "1" : "0");
                fw.write("\n");
            } else if (t.getType() == 'd') {
                Deadline d = (Deadline) t;
                fw.write("d");
                fw.write("\n");
                fw.write(t.description);
                fw.write("\n");
                fw.write(d.by);
                fw.write("\n");
                fw.write(t.isDone ? "1" : "0");
                fw.write("\n");
            } else if (t.getType() == 't') {
                Todo td = (Todo) t;
                fw.write("t");
                fw.write("\n");
                fw.write(t.description);
                fw.write("\n");
                fw.write(t.isDone ? "1" : "0");
                fw.write("\n");
            }
        }
        fw.close();
    }

    public static void eraseList(String path) throws IOException {
        PrintWriter writer = new PrintWriter(new File(path));
        writer.print("");
        writer.close();
    }
}
