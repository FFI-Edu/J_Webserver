
public class SETTINGS
{
    SETTINGSTICKER ticker;
    String lastchange="";
    
    public SETTINGS()
    {
        ticker = new SETTINGSTICKER (this, 750, true);
        refresh();
    }
    
    public void refresh()
    {
        //
        //LOG.write("Settings refreshed");
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
    
}
