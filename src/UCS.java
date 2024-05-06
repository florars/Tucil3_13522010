import java.util.Map;
import java.util.HashMap;

public class UCS {
    private static int visited;
    private Map<String, Integer> checkedWords = new HashMap<String,Integer>();

    public HashMap<Boolean, String> run(TreeNode start, String end, DictReader dictionary) {
        UCS.visited = 0;
        long startTime = System.currentTimeMillis();
        Boolean found = false;
        String resString = "Tidak ditemukan jalan dari kata awal ke kata akhir!";
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
            resString = "Time elapsed: " + (System.currentTimeMillis()-startTime) +  "ms\nJawaban ditemukan: " 
            + temp.printPath() + "Panjang ladder yang ditemukan: " + temp.getPath().size() +
            "\nJumlah kata yang dikunjungi: " + visited;
        }
        else {
            resString += "\n" + "Time elapsed: " + (System.currentTimeMillis()-startTime) +  "ms" + "\n" + "Jumlah kata yang dikunjungi: " + visited + "\n";
        }
        HashMap<Boolean, String> hasil = new HashMap<Boolean, String>();
        hasil.put(found, resString);
        return (hasil);
    }
}
