import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;

public class DictReader {
    private File dict;
    private HashSet<String> words;
    private Map<String, ArrayList<String>> differentByOne;

    public DictReader(String path) {
        this.dict = new File(path);
        this.differentByOne = new HashMap<>();
    }

    public Boolean dictValid() {
        return (this.dict.exists());
    }

    public void makeList() {
        this.words = new HashSet<>();
        try {
            Scanner read = new Scanner(this.dict);
            while (read.hasNextLine()) {
                this.words.add(read.nextLine().toLowerCase());
            }
            read.close();
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private Boolean hasWord(String word) {
        return (this.words.contains(word));
    }

    public TreeNode findAdjacent(TreeNode n) {
        if (!differentByOne.containsKey(n.getRoot())) { // Jika kata yang dicari belum ada di map, cari manual dan masukkan
            Character temp;
            StringBuilder curWord = new StringBuilder(n.getRoot());
            ArrayList<String> children = new ArrayList<>();
            for (int j = 0; j < n.getRoot().length(); j++) {
                for (char i = 'a'; i <= 'z'; i++) {
                    if (curWord.charAt(j) != i){
                        temp = curWord.charAt(j);
                        curWord.setCharAt(j, i);
                        if (this.hasWord(curWord.toString())) {
                            n.AddChild(curWord.toString());
                            children.add(curWord.toString());
                        }
                        curWord.setCharAt(j, temp);
                    }
                }
            }
            differentByOne.put(n.getRoot(), children);
        }
        else { // Kata sudah ada di map, tinggal get
            for (String children : differentByOne.get(n.getRoot())) {
                n.AddChild(children);
            }
        }
        return n;
    }
}
