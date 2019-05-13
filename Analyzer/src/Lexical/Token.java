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
	
	public TokenCategory getCategory () {
		return this.category;
	}
	
	@Override
	public String toString() {
		return "[" + this.line + ", " + this.column + "]" + " (" + this.category.getCategoryValue() + ", " + this.category + ")" + " {" + this.value + "}";
	}
	
	public void print() {
		System.out.printf("              [%04d, %04d] (%04d, %20s) {%s}\n", this.line, this.column, this.category.getCategoryValue(), this.category, this.value);
	}
	
	public int getLine() {
		return this.line;
	}	
	
	public int getColumn() {
		return this.column;
	}

}
