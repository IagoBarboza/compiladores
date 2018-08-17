package Lexical;

public class Token {
	
	private String value;
	private TokenCategory category;
	private int line;
	private int column;
	
	public Token(int line, int column, TokenCategory category, String value) {
		this.line = line;
		this.column = column;
		this.category = category;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[" + this.line + ", " + this.column + "]" + " (" + this.category.getCategoryValue() + ", " + this.category + ")" + " {" + this.value + "}";
	}

}
