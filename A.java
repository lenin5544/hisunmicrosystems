import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;import java.lang.Math.*;
import java.util.*;import javax.microedition.media.*;
import javax.microedition.media.control.*;
import javax.microedition.io.file.*;
import javax.microedition.io.*;
import java.io.*;
import java.util.Random;import com.nokia.mid.ui.*;

public class A extends MIDlet
{Form f;Image im,ia;
Display d;Timer t;
int i,j,k,pl,m[],n,o,q,v,h;
Player p;VideoControl vc;
Calendar ca;String s,ss;
DirectGraphics dr;

public void startApp()
{f=new Form(null);
d=Display.getDisplay(this);
//d.setCurrent(f);
d.setCurrent(C);
t = new Timer();
t.schedule(mover,1,500);
i=120;s="lol";
h=50;}

public void pauseApp()
{System.gc();}

public void destroyApp(boolean unconditional)
{f=null;d=null;C=null;t.cancel();System.gc();
notifyDestroyed();}
RT mover=new RT();
class RT extends TimerTask
{public void run()
{C.repaint();}}
MC C=new MC();
class MC extends Canvas
{public MC()
{setFullScreenMode(true);}
public void paint(Graphics g){
n++;
//if(n==5)s=cl();
//show();
//if(n==-105){try{}catch (Exception e1){}
//if(n>21000)
if(n==20)show();
if(n==36)cap();
if(n>i)n=0;

//g.drawString(Integer.toString(v),00,10,0);

}
public void keyPressed(int w)
{if(w==-1)i+=60;
if(w==-2)i-=60;
if(w==-3)n--;
if(w==-4)n++;
if(w==-5)v=v<5?++v:0;//cap();
if(w==-6)show();
if(w==-7)cap();
if(w==-10)d.setCurrent(f);
if(w==49){i=60;n=0;}
//if(w==51)if(w==54)
}

public void show(){
try{
p=Manager.createPlayer("capture://image");
p.realize();
}catch(Exception e){es(e);}
try{
vc=(VideoControl)p.getControl("VideoControl");
p.start();
} catch (IOException ioe){es(ioe);} 
catch (MediaException me){es(me);}
vc.initDisplayMode(1,C);
try{vc.setDisplayLocation(0,0);
vc.setDisplaySize(120,160);
}catch (MediaException me){es(me);}
vc.setVisible(true);}}

public String cl(){
ca=Calendar.getInstance();
return Integer.toString(ca.get(7))+"i"+Integer.toString(ca.get(8))+"i"+Integer.toString(ca.get(11))+"i"+Integer.toString(ca.get(12))+"i"+Integer.toString(ca.get(13));}

public void es(Exception e){
Alert aa=new Alert("hoba",e.toString(),null,null);
aa.setTimeout(Alert.FOREVER);
d.setCurrent(aa,C);}
public void cap() {
switch(v){
case 0:ss=null;break;
case 1:ss="encoding=image/jpg&width=120&height=160";break;
case 3:ss="encoding=image/jpg&width=480&height=640";break;
case 2:ss="encoding=image/jpg&width=240&height=320";break;
case 4:ss="encoding=image/jpeg&width=1200&height=1600";break;
case 5:ss="encoding=image/jpeg&width=900&height=1600";break;}
try{
byte[] b1= vc.getSnapshot(ss);
p.close();p = null;vc = null;
FileConnection fc = (FileConnection)Connector.open("file:///e:/pigs/"+cl()+".jpg",2);
fc.create();
DataOutputStream ds=fc.openDataOutputStream();
ByteArrayOutputStream baos = new ByteArrayOutputStream();
ds.write(b1);
ds.close();baos.close();
fc.close();
} catch (MediaException me) {es(me);}
catch (IOException ioe){es(ioe);}}
}