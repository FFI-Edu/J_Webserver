import java.util.Date;

public final class STATISTIC
{
    private long starttime;
    private int active,unused,ended;
    private int maxactive;
    
    public STATISTIC()
    {
        starttime=new Date().getTime();
    }

    public void giveThreads(Thread [] threadarr)
    {
        int length=threadarr.length;
        int actives=0;
        for(int i=0;i<length;i++){
            try{
                if(threadarr[i].isAlive()){
                    actives++;
                }
            }catch(Exception e){
                unused++;
            }
        }
        ended=length-(active+unused);
        if(active>maxactive)maxactive=active;
    }
    
    public void giveThreadTimes(long starttime,long requesttime,long processtime,long sendtime){
        
    }
}
