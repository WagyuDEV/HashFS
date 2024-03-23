import java.io.File;

public class test {

    public static void main(String[] args) throws Exception{
        HashedFS fs = new HashedFS(new File("/Users"));
        System.out.println(fs.searchExact(new File("b60dfdd49c67d0d8_0")));
        //System.out.println(fs.searchFuzzy("hamsandwich"));
    }
}
