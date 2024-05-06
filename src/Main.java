import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String start, end;
        int algo;
        Boolean isFound;
        DictReader dictionary = new DictReader("Dictionary.txt");
        if (!dictionary.dictValid()) {
            System.out.println("File dictionary src/Dictionary.txt tidak ditemukan!\n Pastikan file tersebut berada di tempat yang benar.");
            return;
        }
        dictionary.makeList();
        System.out.println("Selamat datang di word ladder solver!");
        Scanner sin = new Scanner(System.in);
        while (true) {
            isFound = false; 
            System.out.println("Masukkan kata awal: ");
            start = sin.nextLine();
            System.out.println("Masukkan kata akhir: ");
            end = sin.nextLine();
            while (end.length()!=start.length()) {
                System.out.println("Kata yang dituju harus memiliki jumlah karakter yang sama dengan kata awal!");
                System.out.println("Masukkan kata akhir: ");
                end = sin.nextLine();
            }
            System.out.println("Algoritma: ");
            System.out.println("1. UCS");
            System.out.println("2. GBFS");
            System.out.println("3. A*");
            algo = Integer.parseInt(sin.nextLine());
            while (algo < 1 || algo > 3) {
                System.out.println("Algoritma: ");
                System.out.println("1. UCS");
                System.out.println("2. GBFS");
                System.out.println("3. A*");
                algo = Integer.parseInt(sin.nextLine());
            }
        
            start = start.toLowerCase();
            end = end.toLowerCase();
            TreeNode startNode = new TreeNode(start);
            if (algo == 1) {
                UCS ucs = new UCS();
                isFound = ucs.run(startNode, end, dictionary);
            }
            else if (algo == 2) {
                GBFS gbfs = new GBFS();
                isFound = gbfs.run(startNode, end, dictionary);
            }
            else {
                Astar astar = new Astar();
                isFound = astar.run(startNode, end, dictionary);
            }

            if (!isFound) System.out.println("Tidak ditemukan jalan dari kata awal ke kata akhir!");
            System.out.println();
            System.out.println("Ketik \"y\" jika ingin melanjutkan software!");
            String cont = sin.nextLine();
            if (!cont.equals("y")) break;
        }
        sin.close();
    }
}