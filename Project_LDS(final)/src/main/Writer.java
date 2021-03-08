package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class Writer {
		
	public Writer(Register rg) {
		
		String path = "C:\\project/Register/"+rg.getName()+"("+rg.getPhone().substring(7, 11)+")"+"/usersav.sav";
		
		File dir1 = new File("C:\\project/Register/");
		
		if(!dir1.exists()) {
			dir1.mkdirs();
		}
		
		File dir2 = new File(dir1,rg.getName()+"("+rg.getPhone().substring(7, 11)+")");
		if(!dir2.exists()) {
			dir2.mkdirs();
		}
		
		FileOutputStream fos =null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(rg);
				
		} catch (Exception e) {
			
			e.printStackTrace();

		}finally {
			try {
				oos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
