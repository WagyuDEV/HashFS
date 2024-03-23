import java.util.*;
import java.io.*;

public class App {
    private static HashMap<File, ArrayList<String>> filesystem = new HashMap<>();
    public static HashMap<File, ArrayList<String>> hashFS(File dir) throws Exception {
        try {
            for (String item : dir.list()) {
                ArrayList<String> tempDir = new ArrayList<>();
                if (new File(dir.toString() + "/" + item).exists()) {
                    // System.out.println(new File(dir.getPath()+"/"+item).isDirectory());
                    if (new File(dir.getPath() + "/" + item).isDirectory()) {
                        hashFS(new File(dir.getPath() + "/" + item));
                    } else if (!filesystem.containsKey(new File(item))) {
                        tempDir.add(new File(dir.toString() + "/" + item).toString());
                        filesystem.put(new File(item), tempDir);
                    } else {
                        for (String path : filesystem.get(new File(item))) {
                            tempDir.add(path);
                        }
                        tempDir.add(new File(dir.toString() + "/" + item).toString());
                        filesystem.put(new File(item), tempDir);
                        // System.out.println("else: \n\t" + item);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        // for (String item : dir.list()) {
        // ArrayList<String> tempDir = new ArrayList<>();
        // if (new File(dir.toString() + "/" + item).exists()) {
        // //System.out.println(new File(dir.getPath()+"/"+item).isDirectory());
        // if(new File(dir.getPath()+"/"+item).isDirectory()){
        // hashFS(new File(dir.getPath()+"/"+item));
        // }
        // else if (!filesystem.containsKey(new File(item))) {
        // tempDir.add(new File(dir.toString() + "/" + item).toString());
        // filesystem.put(new File(item), tempDir);
        // } else {
        // for (String path : filesystem.get(new File(item))) {
        // tempDir.add(path);
        // }
        // tempDir.add(new File(dir.toString() + "/" + item).toString());
        // filesystem.put(new File(item), tempDir);
        // // System.out.println("else: \n\t" + item);
        // }
        // }
        // }
        return filesystem;
    }

    public static void main(String[] args) throws Exception {
        HashMap<File, ArrayList<String>> fs = hashFS(new File("/Users"));
        System.out.println(fs.size());
        System.out.println(fs.keySet());
        System.out.println();
        System.out.println(fs.get(new File("")));
        System.out.println();
        // System.out.println(fs.toString());
    }
}
