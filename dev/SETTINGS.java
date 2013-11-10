import java.io.*; 

public class SETTINGS
{
    SETTINGSTICKER ticker;
    long lastchange=0;
    String settingsfile = "";
    
    public SETTINGS()
    {
        ticker = new SETTINGSTICKER (this, 750, true);
        refresh();
    }
    
    public void refresh()
    {
        if(lastchange==new File(settingsfile).lastModified()){
            
        }else{
            updateVariables();
            lastchange = new File(settingsfile).lastModified();
        }
        
        
    }
    public void updateVariables(){
        
        //
        
        LOG.write("Settings refreshed");
    }
    public void tickerevent()
    {
        //check if 
        refresh();
    }
    
    public int getPort(){
        return 80;
    }
    public int getMaxThreads(){
        return 20;
    }
    public long getMaxThreadtime(){
        return 60*60*1000;
    }
    public String[] getIndexFileNames(){
        String [] s={"index"};
        return s;
    }
    public String[] getIndexFileTypes(){
        String [] s={"html","htm"};
        return s;
    }
    public boolean getFolderIndexingPermission(){
        return true;
    }
    
}
