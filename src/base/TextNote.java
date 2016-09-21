package base;

public class TextNote extends Note {
	public String content;
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

}
