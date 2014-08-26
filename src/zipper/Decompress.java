package zipper;

import java.util.zip.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Decompress extends Thread {
  private JOptionPane jOptionPane1 = new JOptionPane();
  private File toDecompress;
  private String fileName;

  public Decompress(File toDecompress){
    this.toDecompress = toDecompress;
    fileName = toDecompress.getAbsolutePath();
  }
  public void run(){
    try{
      ZipInputStream zipIn = new ZipInputStream(new FileInputStream(fileName));
      ZipEntry entry = zipIn.getNextEntry();
      String name = entry.getName();
      String decompressedName = fileName.substring(0,fileName.lastIndexOf("\\")).concat("\\"+name);
      File outputFile = new File(decompressedName);
      OutputStream out = new FileOutputStream(outputFile);
      int len;
      byte[] buffer = new byte[4096];
      while((len = zipIn.read(buffer))>0){
        out.write(buffer,0,len);
      }
      out.close();
      zipIn.close();
      }catch(IOException e){System.out.println("IOException in Decompress run(): " + e);}
      JOptionPane.showMessageDialog(jOptionPane1, "Decompression Complete!");
  }
}