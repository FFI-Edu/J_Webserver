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
        //if lastchange=date dont change!
        LOG.write("Settings refreshed");
    }

    public void tickerevent()
    {
        refresh();
    }
    
    public int getPort(){
        return 80;
    }
    public int getMaxThreads(){
        return 1;
    }
}
