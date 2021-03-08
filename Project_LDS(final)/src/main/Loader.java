package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Loader {
	private Register rg;
	
	
	public Register getRegister() {
		return rg;
	}
	
	public Loader(String name) {
		String path = "C:\\project/Register/"+name+"/usersav.sav";
		File f = new File(path);

		if(f.exists()) {
			FileInputStream fis=null;
			ObjectInputStream ois = null;
			
			// 파일로드
			try {
				fis= new FileInputStream(f);
				ois= new ObjectInputStream(fis);
				
				rg=(Register)ois.readObject();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
					fis.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}

	}
}
