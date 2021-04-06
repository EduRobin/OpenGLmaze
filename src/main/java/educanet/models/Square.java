package educanet.models;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Square {

    private float[] vertices;
    public int[] indices = {
            0, 1, 3, // First triangle
            1, 2, 3 // Second triangle
    };

    private float[] color = {
            1.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 1.0f,
    };


    private int vaoID;
    private int vboID;
    private int eboID;
    private int colorID;

    private FloatBuffer cb;

    public Square(float x, float y, float width, float height) {

        vaoID = GL33.glGenVertexArrays();
        vboID = GL33.glGenBuffers();
        eboID = GL33.glGenBuffers();
        colorID = GL33.glGenBuffers();


        //set verticies
        this.vertices = new float[] {  //square origin point is in Bottom Left
                x + width  ,y               , 0.0f, // 0 -> Top    Right
                x + width  ,y  - width      , 0.0f, // 1 -> Bottom Right
                x          ,y  - width      , 0.0f, // 2 -> Bottom Left
                x          ,y               , 0.0f, // 3 -> Top    Left
        };

        // Tell OpenGL we are currently using this object (vaoId)
        GL33.glBindVertexArray(vaoID);

        // Tell OpenGL we are currently writing to this buffer (eboId)
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, eboID);
        IntBuffer ib = BufferUtils.createIntBuffer(indices.length)
                .put(indices)
                .flip();
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, ib, GL33.GL_STATIC_DRAW);

        // Change to VBOs...
        // Tell OpenGL we are currently writing to this buffer (vboId)
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboID);

        FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                .put(vertices)
                .flip();

        // Send the buffer (positions) to the GPU
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glEnableVertexAttribArray(0);

        // Clear the buffer from the memory (it's saved now on the GPU, no need for it here)
        MemoryUtil.memFree(fb);

        // Change to Color...
        // Tell OpenGL we are currently writing to this buffer (colorsId)
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, colorID);

        cb = BufferUtils.createFloatBuffer(color.length)
                .put(color)
                .flip();

        // Send the buffer (positions) to the GPU
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, cb, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(1, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glEnableVertexAttribArray(1);

        // Clear the buffer from the memory (it's saved now on the GPU, no need for it here)
        //MemoryUtil.memFree(cb);

    }

    public Square(float[] arr, float[] arr2, float[] arr1) {
    }

    public void draw() {
        //GL33.glUseProgram(Shaders.shaderProgramId);
        GL33.glBindVertexArray(vaoID);
        GL33.glDrawElements(GL33.GL_TRIANGLES, indices.length, GL33.GL_UNSIGNED_INT, 0);

        //update colors
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, colorID);
        cb.put(color).flip();
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, cb, GL33.GL_STATIC_DRAW);
        //MemoryUtil.memFree(cb);
    }

}

