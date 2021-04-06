package educanet;

import educanet.models.Square;
import educanet.utils.FileUtils;
import org.lwjgl.opengl.GL33;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Game {

    private static Square square;
    private static Square square2;
    private static String[] maze;
    public static ArrayList<Square> squares = new ArrayList<>();

    public static void init(long window) {

        //setup shaders
        Shaders.initShaders();
        maze = FileUtils.readFile("src/main/resources/maze1.txt").split("\n");
        float squareWidth = 2f / maze[0].length();
        float squareHeight = 2f / maze.length;
        for (int y = 0;y < maze.length;y++){
            String row = maze[y];
            for(int x = 0;x < row.length();x++){
                if (row.charAt(x) == '0') {
                    squares.add(new Square(x*squareWidth-1,(y*squareHeight)*-1+1,squareWidth,squareHeight));
                }
            }
        }
    }

    public static void render(long window) {
        GL33.glUseProgram(Shaders.shaderProgramId);


        for (Square square : squares){
            square.draw();
        }



    }

    public static void update(long window) {
    }



}

