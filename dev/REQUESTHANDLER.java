/*
 * 
 * String[] getIndexFileNames()
 * String[] getIndexFileTypes()
 * boolean getFolderIndexingPermission()
 * 
 * Linend: \r\n
 * 
 * Request\r\n
 * Headers: Data\r\n
 * \r\n
 * Data
 * 
 */
import java.io.*;

public class REQUESTHANDLER
{
    private String request;
    public REQUESTHANDLER(String request)
    {
        this.request = request;
    }
    
    public byte[] getOutputBytes()
    {
        return "HTTP/1.0 200 OK\r\n\r\n<html><body>ok</body></html>".getBytes();
    }
}
