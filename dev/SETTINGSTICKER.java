
// By vertexxx 2012
// Install:
//          a) change parent classname in line 13 & 15
//          b) implement "void tickerevent()" in parentclass

import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

class SETTINGSTICKER implements ActionListener
{
    Timer timer;    
    SETTINGS parentclass;                                        // <- CLASS MUST BE EDITED HERE
    
    public SETTINGSTICKER (SETTINGS pc, int rate, boolean autostart)     // <- AND HERE 
    {
        timer = new Timer(rate, this);
        if(autostart){
            start();
        }
        parentclass = pc;
    }

    public void actionPerformed (ActionEvent evt)
    {
        parentclass.tickerevent();
    }

    public void start ()
    {
        timer.start ();
    }

    public void stop ()
    {
        timer.stop ();
    }
       
    public void changetickrate (int msec)
    {
        timer.setDelay(msec);
    }
    
     /*public void sleep(int msec)
    {
        // Beansprucht Hardware durch minamalen Überprüfungszeitraum !
        long start = System.currentTimeMillis();
        while(true){
            if( (long) start+msec <= System.currentTimeMillis()){
                break;
            }
        }
    }*/
    
}
