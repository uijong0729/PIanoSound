import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileSupporter {

	//파일 읽기
	public static String readTextFile(String directory, String fileName){
	        String fullpath = directory + "/" + fileName;
	        String content = "";
	        try(FileInputStream fis = new FileInputStream(fullpath);
		        BufferedReader br = new BufferedReader(new InputStreamReader(fis));){
	            content =  br.readLine();
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        return content;
	}
	
	public static void makeTextFile(String directory, String fileName, String value){
        File saveFile = new File(directory + "/" + fileName);
        if (!(saveFile.exists())){
        	saveFile.mkdirs();
        }
      
        try(FileOutputStream fos = new FileOutputStream(saveFile);){
            fos.write(value.getBytes());
        }
        catch (IOException e){
            e.printStackTrace();
        }
	}
}
