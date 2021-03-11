package educanet;

import educanet.models.Square;
import educanet.utils.FileUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Game {

    private static Square square;
    private static Square square2;

    public static void init(long window) {
        float[] vertices = {
                0.5f, 0.5f, 0.0f, // 0 -> Top right
                0.5f, -0.5f, 0.0f, // 1 -> Bottom right
                -0.5f, -0.5f, 0.0f, // 2 -> Bottom left
                -0.5f, 0.5f, 0.0f, // 3 -> Top left
        };

        float[] colors = {
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 0.0f,
        };

        int[] indices = {
                0, 1, 3, // First triangle
                1, 2, 3 // Second triangle
        };

        float[] vertices2 = {
                0.1f, 0.2f, 0.0f, // 0 -> Top right
                0.1f, -0f, 0.0f, // 1 -> Bottom right
                -0.1f, -0f, 0.0f, // 2 -> Bottom left
                -0.1f, 0.2f, 0.0f, // 3 -> Top left
        };

        square = new Square(vertices, indices, colors);
        square2 = new Square(vertices2, indices, colors);

        //String maze = FileUtils.readFile("maze.txt");

        // Setup shaders
        Shaders.initShaders();
    }

    public static void render(long window) {
        GL33.glUseProgram(Shaders.shaderProgramId);

        // Draw using the glDrawElements function

        GL33.glBindVertexArray(square2.vaoId);
        GL33.glDrawElements(GL33.GL_TRIANGLES, square2.indices.length, GL33.GL_UNSIGNED_INT, 0);
    }

    public static void update(long window) {

    }

}

