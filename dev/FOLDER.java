import java.io.*;
import java.util.*;

public class FOLDER
{
    private File f;

    public FOLDER(String pathname)
    {
        f=new File(pathname);
    }
    
    public long getSize(){return f.length();}
    public long getDate(){return f.lastModified();}
    public String getName(){return f.getName();}
    public boolean isHidden(){return f.isHidden();} 
    public boolean isVisible(){return !f.isHidden();} 
    public boolean exists(){return f.isDirectory();}
}
