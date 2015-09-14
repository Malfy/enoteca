package DAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.jar.JarException;



import model.entrate;
import dbconnection.ConnectionManager;

public class Export {
	public void main() throws SQLException, Exception{
		System.out.println("hehe");
		exportDb();
	}

	public void exportDb() throws SQLException, Exception{
		FileWriter fw = null;                    

        String path="c:/backup.sql";
        String dumpCommand =" mysqldump.exe --user=root --password=root --max_allowed_packet=1G --host=localhost --port=3306 --default-character-set=utf8 --single-transaction=TRUE --routines --no-data enoteca"+path;
        Runtime.getRuntime().exec(dumpCommand);
        File data = new File(path);

          try{
              fw = new FileWriter(data);
              fw.close();
          }catch(IOException ex){
              ex.printStackTrace();
          }


          Runtime rt = Runtime.getRuntime();

          try {
                  Process proc = rt.exec(dumpCommand);
                  InputStream in = (InputStream) proc.getInputStream();
                  InputStreamReader read = new InputStreamReader(in,"latin1");
                  BufferedReader br = new BufferedReader(read);
                  BufferedWriter bw = new BufferedWriter(new FileWriter(data,true));
                  String line=null;
                  StringBuffer buffer = new StringBuffer();

                  while    ((line=br.readLine())!=null){
                	  buffer.append(line+"\n");
                  }
                  String toWrite = buffer.toString();
                  bw.write(toWrite);
                  bw.close();
                  br.close();
          } catch (IOException e) {
        	  System.out.println(e.getMessage());
	e.printStackTrace();
	}
	}
	
	
	
	
	
	
}
