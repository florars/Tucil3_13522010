import java.util.ArrayList;

public class TreeNode {
    private ArrayList<String> path;
    private String root;
    private ArrayList<String> children;
    private int gn;
    private int fn;

    public TreeNode(String root) {
        this.root = root;
        this.path = new ArrayList<>();
        this.path.add(root);
        this.children = new ArrayList<>();
        this.gn = 0;
        this.fn = 0;
    }

    public TreeNode(String root, ArrayList<String> path, int gn, int fn) {
        this.root = root;
        this.path = new ArrayList<>(path);
        this.path.add(root);
        this.children = new ArrayList<>();
        this.gn = gn;
        this.fn = fn;
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

    public void printPath() {
        for (int i=0; i<this.path.size()-1; i++) {
            System.out.print(this.path.get(i));
            System.out.print(" -> ");
        }
        System.out.println(this.path.getLast());
    }

    public int getGn() {
        return this.gn;
    }

    public int getFn() {
        return this.fn;
    }
}
