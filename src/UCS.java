import java.util.Map;
import java.util.HashMap;

public class UCS {
    private static int visited = 0;
    private Map<String, Integer> checkedWords = new HashMap<String,Integer>();

    public Boolean run(TreeNode start, String end, DictReader dictionary) {
        long startTime = System.currentTimeMillis();
        Boolean found = false;
        TreeNode temp = start;
        Prioqueue prioqueue = new Prioqueue();
        prioqueue.pushGn(start);
        while (!prioqueue.isEmpty()) {
            temp = prioqueue.pop();
            visited++;
            checkedWords.put(temp.getRoot(), 1);
            if (temp.getRoot().equals(end)) {
                found = true;
                break;
            }
            else {
                temp = dictionary.findAdjacent(temp);
                if (temp.getAllChildren().isEmpty()) {
                    break;
                }
                else {
                    // System.out.print("Jumlah children:"); System.out.println(temp.getAllChildren().size());

                    for (String child : temp.getAllChildren()) {
                        if (!checkedWords.containsKey(child)) {
                            TreeNode tempChild = new TreeNode(child, temp.getPath(), temp.getGn()+1, 0);
                            prioqueue.pushGn(tempChild);
                        }
                    }
                }
            }
        }
        if (found) {
            System.out.print("Time elapsed: "); System.out.print(System.currentTimeMillis()-startTime); System.out.println("ms");
            System.out.println("Jawaban ditemukan: ");
            temp.printPath();
            System.out.print("Panjang ladder yang ditemukan: ");
            System.out.println(temp.getPath().size());
            System.out.print("Jumlah kata yang dikunjungi: ");
            System.out.println(visited);
        }
        return found;
    }
}
