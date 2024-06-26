import java.util.ArrayList;

public class TreeNode {
    private ArrayList<String> path;
    private String root;
    private ArrayList<String> children;
    private int gn;
    private int hn;

    public TreeNode(String root) {
        this.root = root;
        this.path = new ArrayList<>();
        this.path.add(root);
        this.children = new ArrayList<>();
        this.gn = 0;
        this.hn = 0;
    }

    public TreeNode(String root, ArrayList<String> path, int gn, int hn) {
        this.root = root;
        this.path = new ArrayList<>(path);
        this.path.add(root);
        this.children = new ArrayList<>();
        this.gn = gn;
        this.hn = hn;
    }

    public void AddChild(String child) {
        this.children.add(child);
    }

    public String getChild(int n) {
        return this.children.get(n);
    }

    public ArrayList<String> getAllChildren() {
        return this.children;
    }

    public String getRoot() {
        return this.root;
    }
    
    public ArrayList<String> getPath() {
        return this.path;
    }

    public String printPath() {
        String res = "";
        for (int i=0; i<this.path.size()-1; i++) {
            res += this.path.get(i);
            res += " -> ";
        }
        res += this.path.get(this.path.size()-1);
        res += "\n";
        return res;
    }

    public int getGn() {
        return this.gn;
    }

    public int getHn() {
        return this.hn;
    }
}
