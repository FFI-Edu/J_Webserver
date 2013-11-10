import java.io.*;
import java.util.*;

public class INDEXER
{
    private FOLDERCONTENT content;
    private File f;
    private String sorting="NONE";
    
    public INDEXER(String path, String sorting)
    {
        content=new FOLDERCONTENT();
        f=new File(path);
        if(sorting!=null)
            this.sorting=sorting;
        setupContent();
    }

    public void setupContent()
    {
        File[] unsort=f.listFiles();
        List<FILE> filel=new LinkedList<FILE>();
        List<FOLDER> folderl=new LinkedList<FOLDER>();
        for(File f : unsort){
            if( f.isFile() )
                filel.add( new FILE( f.getAbsolutePath() ) );
            if( f.isDirectory() )
                folderl.add( new FOLDER( f.getAbsolutePath() ) );
        }
        
        FILECOMPARATOR fc=new FILECOMPARATOR( sorting );
        Collections.sort(filel,fc);
        content.files = filel.toArray( new FILE[ filel.size() ] );
        
        FOLDERCOMPARATOR foc=new FOLDERCOMPARATOR( sorting );
        Collections.sort(folderl,foc);
        content.folders = folderl.toArray( new FOLDER[ folderl.size() ] );
        
    }
}
