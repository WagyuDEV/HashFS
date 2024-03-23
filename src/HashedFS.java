import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HashedFS {
    public HashMap<File, ArrayList<String>> filesystem = new HashMap<>();

    public HashedFS() throws Exception {
        hashFS(new File("/"), true);
    }

    public HashedFS(File dir) throws Exception {
        hashFS(dir, true);
    }

    public HashedFS(File dir, boolean recursive) throws Exception {
        hashFS(dir, recursive);
    }

    private HashMap<File, ArrayList<String>> hashFS(File dir, boolean recursive) throws Exception {
        String[] items = dir.list();
        if (items != null) {
            for (String item : dir.list()) {
                ArrayList<String> tempDir = new ArrayList<>();
                if (new File(dir.toString() + "/" + item).exists()) {
                    // System.out.println(new File(dir.getPath()+"/"+item).isDirectory());
                    if (new File(dir.getPath() + "/" + item).isDirectory() && recursive) {
                        try {
                            hashFS(new File(dir.getPath() + "/" + item), true);
                        } catch (Exception e) {
                            // System.out.println(dir.getPath());
                            // System.out.println(item);
                            // System.out.println(e + "\t:\t" + new File(dir.getPath() + "/" + item));
                            e.printStackTrace();
                        }
                        // hashFS(new File(dir.getPath() + "/" + item), true);
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
        }

        return filesystem;
    }

    /*
     * @return returns the edit distance between 2 provided strings
     */
    public int compare(String str1, String str2) {
        int sum = 0;
        int minLength = Math.min(str1.length(), str2.length());
        
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                sum++;
            }
        }
        
        // Add the difference in lengths to the sum
        sum += Math.abs(str1.length() - str2.length());
        
        return sum;
    }
    
    public ArrayList<String> searchExact(File file) throws Exception {
        if (filesystem.containsKey(file)) {
            return filesystem.get(file);
        } else {
            throw new Exception("File not found");
        }
    }

    public ArrayList<String> searchFuzzy(String file) throws Exception {
        if (filesystem.containsKey(new File(file))) {
            return searchExact(new File(file));
        }

        ArrayList<String> dirs = new ArrayList<>();

        try {

            for (File search : filesystem.keySet()) {
                if (search.toString().toLowerCase().contains(file.toLowerCase())
                        ||
                        file.toLowerCase().contains(search.toString().toLowerCase())) {
                    dirs.add(searchExact(search).toString());
                } else if (compare(search.toString().toLowerCase(), file.toLowerCase()) <= 4) {
                    dirs.add(searchExact(search).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dirs.size() > 0) {
            return dirs;
        } else {
            throw new Exception("No file could be found");
        }
    }
}
