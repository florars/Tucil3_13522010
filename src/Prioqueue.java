import java.util.ArrayList;

public class Prioqueue {
    public ArrayList<TreeNode> queue;

    public Prioqueue() {
        this.queue = new ArrayList<TreeNode>();
    }

    public void pushGn(TreeNode n) {
        if (this.queue.isEmpty()) this.queue.add(0, n);
        else {
            for (int i=0; i<queue.size(); i++) {
                if (queue.get(i).getGn() > n.getGn()) {
                    queue.add(i, n);
                    break;
                }   
            }
            if (queue.get(queue.size()-1).getGn() <= n.getGn()) queue.add((queue.size()-1), n);
        }
    }

    public void pushHn(TreeNode n) {
        if (this.queue.isEmpty()) this.queue.add(0, n);
        else {
            for (int i=0; i<queue.size(); i++) {
                if (queue.get(i).getHn() > n.getHn()) {
                    queue.add(i, n);
                    break;
                }   
            }
            if (queue.get(queue.size()-1).getHn() <= n.getHn()) queue.add((queue.size()-1), n);
        }
    }

    public void pushGnHn(TreeNode n) {
        if (this.queue.isEmpty()) this.queue.add(0, n);
        else {
            for (int i=0; i<queue.size(); i++) {
                if ((queue.get(i).getGn() + queue.get(i).getHn()) > (n.getGn() + n.getHn())) {
                    queue.add(i, n);
                    break;
                }   
            }
            if ((queue.get(queue.size()-1).getGn() + queue.get(queue.size()-1).getHn()) <= (n.getGn() + n.getHn())) queue.add((queue.size()-1), n);
        }
    }

    public TreeNode pop() {
        TreeNode temp = this.queue.get(0);
        this.queue.remove(0);
        return temp;
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void clear() {
        this.queue.removeAll(queue);
    }
}
