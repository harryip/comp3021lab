package base;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;

public class Folder implements Comparable<Folder>, Serializable {
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;
	
	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}
	public void addNote(Note add){
		notes.add(add);
	}
	public String getName(){
		return name;
	}
	public ArrayList<Note> getNotes(){
		return notes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	public String toString(){
		int nText = 0;
		int nImage = 0;
		for(Note note : notes){
			if(note instanceof TextNote){
				nText += 1;
			}else {
				nImage +=1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	public int compareTo(Folder o){
		if(this.name.compareTo(o.name) == 0) return 0;
		if(this.name.compareTo(o.name) > 0) return 1;
		else return -1;
	}
	public void sortNotes(){
		Collections.sort(notes);	
	}
	public List<Note> searchNotes(String keywords){
		String[] karray = keywords.split(" ");
		List<Note> snote = new ArrayList<Note>();
		for (Note note : this.notes){
			boolean Flag=true;
			int i=0;
			while((i<karray.length)&&(Flag)){
				Flag=false;
				if(note instanceof TextNote)
				{
					do{
						if (karray[i].equalsIgnoreCase("or"))
						i++;
						if ((note.getTitle().toLowerCase().indexOf(karray[i].toLowerCase())>=0)||(((TextNote) note).content.toLowerCase().indexOf(karray[i].toLowerCase())>=0))
						{Flag=true;}
						i++;
					} while ((i<karray.length)&&(karray[i].equalsIgnoreCase("or")));
				
				}else {
					do {
						if (karray[i].equalsIgnoreCase("or"))
						i++;
						if ((note.getTitle().toLowerCase().indexOf(karray[i].toLowerCase())>=0))
						{Flag=true;}
						i++;
					} while ((i<karray.length)&&(karray[i].equalsIgnoreCase("or")));
					
				}
				
			}
			if (Flag)
				snote.add(note);
		}
		return snote;
	}


}