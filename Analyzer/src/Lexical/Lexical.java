package Lexical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexical {
	
	private BufferedReader br;
	private String line;
	private Character c;
	private Integer currentCol;
	private Integer tkLine = -1;
	private Integer tkCol;
	private String tkValue;
	
	public Lexical(String file) {
		this.readFile(file);
	}
	
	private void readFile(String file) {
		try {
			this.br = new BufferedReader(new FileReader(file));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Token nextToken() {
		
		// First line or line already explored		
		if (this.line == null || this.currentCol == this.line.length()-1) {
			this.readLine();
			if (this.line == null) return null;
			tkLine++;
			this.currentCol = -1;
			tkCol = -1;
		}
		
		this.tkValue = "";
		this.tkCol++;
		
		this.readChar();
		
		// Ignores empty spaces		
		while (this.c == ' ' || this.c == '\t') {
			this.tkCol++;
			this.readChar();
			if (this.c == '\n') return this.nextToken();
		}
		
		this.tkValue += this.c;
		this.readChar();
		if (Table.operators.containsKey(this.tkValue + this.c)) {
			this.tkValue += this.c;
		} else {
			this.currentCol--;
			this.c = this.tkValue.charAt(this.tkValue.length()-1);
		}
		
		// Reads the next token char while a symbol is not found
		while(!Table.symbolList.contains(this.c)) {
			this.readChar();
			if (!Table.symbolList.contains(this.c)) this.tkValue += this.c;
			else if(this.c != '\n') this.currentCol--;
		}
		
		return new Token(this.tkLine, this.tkCol, this.categoryze(), this.tkValue);
	}
	
	private void readLine() {
		try {
			this.line = this.br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readChar() {
		if (this.currentCol+1 <= this.line.length()-1) {
			this.currentCol++;
			this.c = this.line.charAt(this.currentCol);
		}
		else this.c = '\n';
	}
	
	private TokenCategory categoryze() {
		if (this.isDecNumConst()) return TokenCategory.DECNUMCONST;
		else if (this.isIntNumConst()) return TokenCategory.INTNUMCONST;
		else if (this.isReservedWord()) return Table.reservedWords.get(this.tkValue);
		else if (this.isDelimiter()) return Table.delimiters.get(this.tkValue);
		else if (this.isId()) return TokenCategory.ID;
		else if (this.isTerminator()) return TokenCategory.TERM;
		else if (this.isSeparator()) return Table.separators.get(this.tkValue);
		else if (this.isOperator()) return Table.operators.get(this.tkValue);
		else return TokenCategory.UNDEFINED;
	}
	
	private boolean isDecNumConst() {
		return this.tkValue.matches("\\d+\\.\\d+");
	}
	
	private boolean isIntNumConst() {
		return this.tkValue.matches("\\d+");
	}
	
	private boolean isReservedWord() {
		return Table.reservedWords.containsKey(this.tkValue);
	}

	private boolean isId() {
		return this.tkValue.matches("([a-z])+([a-zA-z\\_])*");
	}
	
	private boolean isDelimiter() {
		return Table.delimiters.containsKey(this.tkValue);
	}
	
	private boolean isTerminator() {

		return this.tkValue.contentEquals("#");
	}

	private boolean isSeparator() {
		return Table.separators.containsKey(this.tkValue);
	}
	
	private boolean isOperator() {
		return Table.operators.containsKey(this.tkValue);
	}
}
