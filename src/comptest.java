import java.io.File;

public class comptest {
    public static int compare(String str1, String str2) {
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

    public static void main(String[] args) {
        //File place = new File("/Users/j/Documents");
        System.out.println(compare("cheese", "esseball"));
        // System.out.println(place.length());
        // System.out.println(place.list().length);
        // System.out.println(place.listFiles().length);

        // for (String dir : place.list())
        //     System.out.println(dir);
        // for (File dir : place.listFiles())
        //     System.out.println(dir);
    }
}
