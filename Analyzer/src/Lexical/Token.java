package Lexical;

public class Token {
	
	private String value;
	private TokenCategory category;
	private int line;
	private int column;
	
	public Token(int line, int column, TokenCategory category, String value) {
		this.line = line+1;
		this.column = column+1;
		this.category = category;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[" + this.line + ", " + this.column + "]" + " (" + this.category.getCategoryValue() + ", " + this.category + ")" + " {" + this.value + "}";
	}

}
