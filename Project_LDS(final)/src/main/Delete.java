package main;

import java.io.File;

public class Delete {
	private Register rg;
	
	public Register Delete() {
		return rg;
	}
	
	
	public Delete(String name) {
		
		
		String path ="C:\\project\\Register/"+name;
		
		File f = new File(path);
		
		try {
		    while(f.exists()) {
			File[] folder_list = f.listFiles(); //���ϸ���Ʈ ������
					
			for (int j = 0; j < folder_list.length; j++) {
				folder_list[j].delete(); //���� ���� 
						
			}
					
			if(folder_list.length == 0 && f.isDirectory()){ 
				f.delete(); //������� ����
			}
	            }
		 } catch (Exception e) {
			e.getStackTrace();
		}
		
		
		
		
	}

	
	
}
