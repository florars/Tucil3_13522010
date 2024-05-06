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
                if (queue.get(i).getGn() >= n.getGn()) {
                    queue.add(i, n);
                    break;
                }   
            }
            if (queue.get(queue.size()-1).getGn() < n.getGn()) queue.addLast(n);
        }
    }

    public void pushFn(TreeNode n) {
        if (this.queue.isEmpty()) this.queue.add(0, n);
        else {
            for (int i=0; i<queue.size(); i++) {
                if (queue.get(i).getFn() >= n.getFn()) {
                    queue.add(i, n);
                    break;
                }   
            }
            if (queue.get(queue.size()-1).getFn() < n.getFn()) queue.addLast(n);
        }
    }

    public void pushGnFn(TreeNode n) {
        if (this.queue.isEmpty()) this.queue.add(0, n);
        else {
            for (int i=0; i<queue.size(); i++) {
                if ((queue.get(i).getGn() + queue.get(i).getFn()) >= (n.getGn() + n.getFn())) {
                    queue.add(i, n);
                    break;
                }   
            }
            if ((queue.get(queue.size()-1).getGn() + queue.get(queue.size()-1).getFn()) < (n.getGn() + n.getFn())) queue.addLast(n);
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
}
