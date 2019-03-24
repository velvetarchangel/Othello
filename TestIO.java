import java.io.*;
import java.util.*;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TestIO{

    public static void usingFileWriter() throws IOException{
        File aFile = new File("./test.txt");
        FileWriter f = new FileWriter(aFile);
        Board b = new Board();
        String [] line = new String[10];
        String[][] board = b.getArray();
        for (int i = 0; i < 10; i++){
            line = board[i];
            String line_wb = Arrays.toString(line).replace("[","");
            String line_without_bracket = line_wb.replace("]","");
            String filecontent = line_without_bracket+ "\n";
            f.write(filecontent);
            filecontent = "";
        }
        f.close();
    }

    public static void usingFileReader() throws IOException{

        Scanner sc = new Scanner(new BufferedReader(new FileReader("./test.txt")));
        String[][] board = new String[10][10];
        while(sc.hasNextLine()){
            for(int i = 0; i <= 9; i++){
                String[] line = sc.nextLine().trim().split(",");
                board[i] = line;
            }
        }
        System.out.println(Arrays.deepToString(board));
    }

    public static void main(String[] args)throws URISyntaxException, IOException{
        usingFileWriter();
        usingFileReader();
    }
}
     