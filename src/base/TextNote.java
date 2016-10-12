package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.HashMap;

public class TextNote extends Note {
	public String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote(File f){
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	public TextNote(String title){
		super(title);
	}
	public String getcontent(){
		return content;
	}
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	private String getTextFromFile(String absolutePath){
		String result = "";
		try{
			BufferedReader br = new BufferedReader (new FileReader(absolutePath));
			String line = br.readLine();
			while(line != null){
				result += line +'\n';
				line = br.readLine();
			}
			br.close();
			
		} catch (IOException e){
			System.out.println("#1 IOE");
		} catch (Exception e){
			e.printStackTrace();
		}
	
		return result;
	}
	public void exportTextToFile(String pathFolder)
	{
		String title = getTitle().replaceAll(" ","_");
		
		String filen = "";
		if(!pathFolder.equals("")) filen = pathFolder + File.separator;
		
		
		
		File file = new File(filen + title);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		}catch(IOException ioe){
			System.out.println("#2 IOE");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getcontent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
}
				}
			if (count.get(c) > b) {
				b = count.get(c);
				r = c;
			
			}
		}
		
		return r;
	}
}
