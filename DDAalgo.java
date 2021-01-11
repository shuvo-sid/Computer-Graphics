import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

//Mohammad Asifur Rahman Shuvo-16301132 sec-01

public class DDAalgo implements GLEventListener{
   
	private GLU glu;
   @Override
   public void display(GLAutoDrawable drawable) {
      final GL2 gl = drawable.getGL().getGL2();
     // DDA(gl, -16,-30,11,32);
      
      //3
      DDA(gl, 0,70,20,70);
      DDA(gl, 20,50,20,70);
      DDA(gl, 3,50,20,50);
      DDA(gl, 20,30,20,50);
      DDA(gl, 0,30,20,30);
      //2
      DDA(gl, 30,70,50,70);
      DDA(gl, 50,70,50,50);
      DDA(gl, 50,50,30,50);
      DDA(gl, 30,50,30,30);
      DDA(gl, 30,30,50,30);
      
      
      
   }
   @Override
   public void dispose(GLAutoDrawable arg0) {
      //method body
   }
   
   @Override
   public void init(GLAutoDrawable gld) {
       GL2 gl = gld.getGL().getGL2();
       glu = new GLU();

       gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
       gl.glViewport(-100, -50, 50, 100);
       gl.glMatrixMode(GL2.GL_PROJECTION);
       gl.glLoadIdentity();
       glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
   }

   

   @Override
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // method body
   }
   
   
   public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {
       
       gl.glPointSize(3.0f);
       gl.glColor3d(1, 0, 0);
       
       
       float m,x,y;
       float dx,dy;
       dx=x2-x1;
       dy=y2-y1;
       m=dy/dx;
       x=x1;
	   y=y1;
       gl.glBegin(GL2.GL_POINTS);
       gl.glVertex2f(x1, y1);
       if(m<=1 && m>=-1) {
    	   
    	   while(x<x2) {
    		   x=x+1;
    		   y=y+m;
    		   gl.glVertex2f(x,y);
    		   
    	   }
    	   while(x2<x) {
    		   x=x-1;
    		   y=y-m;
    		   gl.glVertex2f(x,y);  
    	   }
       }
       else {
    	   
    	   while(y<y2) {
    		   y++;
    		   x=x+(1/m);
    		   gl.glVertex2f(x,y);
    	   }
    	   while(y2<y) {
    		   y=y-1;
    		   x=x-(1/m);
    		   gl.glVertex2f(x,y);
    	   } 	   
       }
    	   
       gl.glEnd();
       
  }
  
   public static void main(String[] args) {
      //getting the capabilities object of GL2 profile
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      DDAalgo l = new DDAalgo();
      glcanvas.addGLEventListener(l);
      glcanvas.setSize(400, 400);
      //creating frame
      final JFrame frame = new JFrame ("DDA Algo");
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
   }//end of main
}//end of classimport javax.media.opengl.GL2;