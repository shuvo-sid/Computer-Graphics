import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import static java.lang.StrictMath.abs;
import javax.swing.JFrame;
public class MPLD  implements GLEventListener {
  
    private GLU glu;
    
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }
    
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */
       
        
        //3
        
        DDA(gl, 10, -90, 60, 90);
        DDA(gl, 20, -80, 60, 80);
        DDA(gl, 10, -70, 60, 70);
        DDA(gl, 70, 60, 90, 60);
        
       
        //2
        
        DDA(gl, 30, -60, 70, 60);
        DDA(gl, 30, -50, 70, 50);
        DDA(gl, 30, -40, 70, 40);
        DDA(gl, 50, 70, 60, 70);
        DDA(gl, 40, 30, 50, 30);
        
        
        
        
    }
@Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }
    private void DDA(GL2 gl, int x1, int y1, int x2, int y2) {
        gl.glPointSize(2.0f);
        gl.glColor3d(1, 0, 0);
        gl.glBegin(GL2.GL_POINTS);

        int zone = findZone(x1, y1, x2, y2);
        drawLine(gl, zone, abs(x1), abs(y1), abs(x2), abs(y2));
        gl.glEnd();
    }
     private void drawLine(GL2 gl, int zone, int x1, int y1, int x2, int y2) {
        int dx, dy, d, dE, dNE, x, y;
        dx = x2 - x1;
        dy = y2 - y1;
        d = 2 * dy - dx;
        dE = 2 * dy;
        dNE = 2 * (dy - dx);
        y = y1;
        System.out.println("Zone = "+zone);
        for (x = x1; x <= x2; x++) {
            draw8wayPixel(gl,zone, x, y);
            if (d > 0) {
                d = d + dNE;
                y = y + 1;
            } else {
                d = d + dE;
            }
        }
    }
    private void draw8wayPixel(GL2 gl,int zone, int x, int y){
        if(zone==0){
            gl.glVertex2d(y, x);
        }
        else if(zone==1){
            gl.glVertex2d(x, y);
        }
        else if(zone==2){
            gl.glVertex2d(-x, y);
        }
        else if(zone==3){
            gl.glVertex2d(-y, x);
        }
        else if(zone==4){
            gl.glVertex2d(-y, -x);
        }
        else if(zone==5){
            gl.glVertex2d(-x, -y);
        }
        else if(zone==6){
            gl.glVertex2d(x, -y);
        }
        else {
            gl.glVertex2d(y, -x);
        }
        
    }
    private int findZone(int x1, int y1, int x2, int y2) {
        int zone;
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (dx >= 0 && dy >= 0) {
            if (dx >= dy) {
                zone = 0;
            } else {
                zone = 1;
            }
        } else if (dx < 0 && dy >= 0) {
            if (Math.abs(dx) > dy) {
                zone = 3;
            } else {
                zone = 2;
            }
        } else if (dx < 0 && dy < 0) {
            if (Math.abs(dx) > Math.abs(dy)) {
                zone = 4;
            } else {
                zone = 5;
            }
        } else {
            if (dx < Math.abs(dy)) {
                zone = 6;
            } else {
                zone = 7;
            }
        }
        return zone;
    }
    int convertX(int x, int y, int zone) {
        int convertedX = 0;
        return convertedX;
    }
    int convertY(int x, int y, int zone) {
        int convertedY = 0;
        return convertedY;
    }
    
    @Override
    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }
    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      MPLD l = new MPLD();
      glcanvas.addGLEventListener(l);
      glcanvas.setSize(400, 400);
      //creating frame
      final JFrame frame = new JFrame ("Midpoint Line Drawing");
   
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);                 
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
      
    }
}