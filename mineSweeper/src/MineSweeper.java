import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {      // Değerlendirme formu 5

    int row,column,rowSelect,colSelect,moveCount,sTotal;
    String[][] map;
    String[][] board;

    public void run(){

        while (!isPass(row,column)){   // Değerlendirme formu 7 - isPass metodu ve while kullanarak kullanıcının istediğim değerler girene kadar döngüde kalmasını sağladım

            Scanner inp = new Scanner(System.in);
            System.out.println("Dizinin satır sayısını giriniz : ");
            row = inp.nextInt();
            System.out.println("Dizinin sütun sayısını giriniz : ");
            column = inp.nextInt();
        }

        map = new String[row][column];    // girilen değerlere göre dizileri oluşturdum
        board = new String[row][column];

        prepareMap(row,column);   // değerlendirme formu 8 - prepareMap metodu ile map dizisine rastgele mayın yerleştirdim

        //printMap(map);
        System.out.println(" ");
        printMap(board);

        Scanner inp = new Scanner(System.in);
        while (moveCount < sTotal){

            do {
                System.out.println("Oluşturulan oyun sınırı içinden seçim yapınız");
                System.out.println("Satır Giriniz :");
                rowSelect = inp.nextInt();
                System.out.println("Sütun Giriniz :");
                colSelect = inp.nextInt();

            } while (!isValid(rowSelect, colSelect));   // Değerlendirme formu 9 - 10


            if (map[rowSelect][colSelect] == "-"){

                map[rowSelect][colSelect] = "s";    // Değerlendirme formu 11
                mineControl(rowSelect,colSelect);   // Değerlendirme formu 11 - 12
                moveCount++;
            } else if (map[rowSelect][colSelect].equals("s")){   // s ile duplicate kontrolü yaptım
                System.out.println("Bu konumu daha önce işaretlediniz. Lütfen başka bir konum seçiniz.");
            } else if (map[rowSelect][colSelect] == "*") {   // Değerlendirme formu 13 - mayın seçildiyse oyunun bitmesini kontrol ettim
                System.out.println("Kaybettin");    // Değerlendirme formu 15
                break;
            }

            //printMap(map);
            //System.out.println(" ");
            printMap(board);
            //System.out.println(rowSelect+" "+colSelect);
        }

        if (moveCount == sTotal){   // Değerlendirme formu 14 - toplam s sayısına yani doğru hamle sayısına ulaşırsa oyunu kazanmış olur
            System.out.println("Kazandın"); // Değerlendirme formu 15
        }

    }

    public static boolean isPass(int r,int c){  // Kullanıcının verdiği satır ve sütun değeri kontrolü yapar

        if (r >= 2 && c >= 2){
            return true;
        } else return false;
    }
    public boolean isValid(int r,int c){    // Değerlendirme formu 10
        if (r >= 0 && c >= 0 && r < row && c < column){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDuplicate(int[] arr,int value){     // rastgele sayıları tuttuğum dizide duplicate kontrolü yaptım

        for (int i:arr){
            if (i == value){
                return true;
            }
        }
        return false;
    }

    public void prepareMap(int r,int c){    // Değerlendirme formu 8

        int sum = r * c;
        int sumF = sum / 4;    // (elemanSayisi / 4) ile oluşturulması gereken mayın sayısını hesapladım
        sTotal = (sum-sumF);   // oyunun kazanılması için gerekli olan s sayısını hesapladım ( toplam hamle sayısından toplam mayın sayısını çıkarttım )
        int randomNum;

        Random rnd = new Random();
        int[] rndInt = new int[sumF];   // oluşturduğum rastgele sayıları rndInt dizisi ile tuttum

        for (int i=0;i<sumF;i++){                      // üretilecek mayın sayısı kadar döngü oluşturdum
            randomNum = rnd.nextInt(sum);              // rastge sayı ürettim
            while (isDuplicate(rndInt,randomNum)){     // üretilen sayının tekrar edip etmediği kontrol ettim
                randomNum = rnd.nextInt(sumF );
            }
            rndInt[i] = randomNum;                     // sayı tekrar etmiyorsa diziye ekledim
            //System.out.println(randomNum);
        }

        Arrays.sort(rndInt);                        // üretilen rastgele sayıları küçükten büyüğe sıraladım

        int count = 0;
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                if (Arrays.binarySearch(rndInt, count) >= 0){  // count ile rndInt indeksi eşitse oraya mayın koydum
                    this.map[i][j] = "*";
                    this.board[i][j] = "-";
                } else {
                    this.map[i][j] = "-";
                    this.board[i][j] = "-";
                }
                count++;
            }
        }

    }

    public void printMap(String[][] arr) {
        for (String[] row : arr) {
            for (String column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public void mineControl(int row, int col) { // Değerlendirme formu 12 - etrafındaki mayınları bulmasını sağlarken isValid metoduyla oluşturduğum haritanın dışına çıkılmamasını kontrol ettim
        int count = 0;

        if (isValid(row -1 , col)) {

            if (map[row-1][col] == "*") {
                count++;
            }
        }

        if (isValid(row -1 , col -1)) {
            if (map[row-1][col -1] == "*") {
                count++;
            }
        }

        if (isValid(row -1 , col+1)) {
            if (map[row-1][col+1] == "*") {
                count++;
            }
        }

        if (isValid(row , col-1)) {
            if (map[row][col-1] == "*") {
                count++;
            }
        }

        if (isValid(row , col+1)) {
            if (map[row][col+1] == "*") {
                count++;
            }
        }

        if (isValid(row +1  , col -1)) {
            if (map[row+1][col -1] == "*") {
                count++;
            }
        }

        if (isValid(row + 1 , col)) {
            if (map[row+1][col] == "*") {
                count++;
            }
        }

        if (isValid(row + 1 , col + 1)) {
            if (map[row+1][col+1] == "*") {
                count++;
            }
        }

        board[row][col] = String.valueOf(count);
    }
}
