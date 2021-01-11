import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
//Mohammad Asifur Rahman Shuvo Sec: 01
public class MPCD implements GLEventListener {
    private GLU glu;

    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-700, -700, 700, 700);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-700.0, 700.0, -700.0, 700.0);
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        
        
        DC(gl, 50, 50, 50);     
        DC(gl, 70, 150, 50);
        DC(gl, 150, 210, 50);
        DC(gl, 250, 210, 50);
        DC(gl, 320, 140, 50);
        DC(gl, 320, 40, 50);
        DC(gl, 265, -40, 50);
        DC(gl, 190, -110, 50);
        DC(gl, 140, -190, 50);
        DC(gl, 95, -280, 50);
        DC(gl, 195, -280, 50);
        DC(gl, 295, -280, 50);
        DC(gl, 395, -280, 50);
        
        
    }
    
    

    private void DC(GL2 gl,int X, int Y, int r) {

    	
    
    	int x = 0;
        int y = r;
        int d = 1 - r;
        draw8SymmetricCurves(gl, x, X, y, Y);
        
        
        for(x=0; x<y; x++) {
            if(d<0) {
                d += 2*x+3;
            }
            else {
                y--;
                d += 2*(x-y)+5;
            }
            draw8SymmetricCurves(gl, x, X, y, Y);
        }
        gl.glEnd();
    }

    public void draw8SymmetricCurves(GL2 gl, int x, int X, int y, int Y) {

        gl.glColor3d(1, 0, 0);
        gl.glPointSize(2.0f);
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(X+y,Y+x);   //1
        gl.glVertex2d(X+x,Y+y);   //2
        gl.glVertex2d(-x+X,y+Y);  //3
        gl.glVertex2d(-y+X,x+Y);  //4
        gl.glVertex2d(-y+X,-x+Y); //5
        gl.glVertex2d(-x+X,-y+Y); //6
        gl.glVertex2d(x+X,-y+Y);  //7
        gl.glVertex2d(y+X,-x+Y);  //8
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    @Override
    public void dispose(GLAutoDrawable arg0){
    }



    
    public static void main(String args[]){
                
        final GLProfile profile=GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities=new GLCapabilities(profile);
        
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        MPCD l = new MPCD();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(700, 700);
        
        final JFrame frame = new JFrame("Midpoint Circle Drawing");
        
        frame.add(glcanvas);
        frame.setSize(700,700);
        frame.setVisible(true);      
    }
}
