package Lexical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexical {
	
	private BufferedReader br;
	private String line;
	private Character c;
	private Character fallbackC;
	private Integer currentCol;
	private Integer tkLine = -1;
	private Integer tkCol;
	private String tkValue;
	private Token previousToken;
	
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
		
		// No line analyzed or File is empty
		if (this.line == null) {
			this.readLine();
			if (this.line == null) return null;			
		}
		
		// Line is empty
		if (this.line.isEmpty()) {
			this.readLine();
			return this.nextToken();
		}
		
		// Is a new line
		if (this.currentCol == -1) {
			System.out.printf("%4d  %s\n", this.tkLine + 1, this.line);
		}

		this.tkValue = "";
		this.tkCol++;
		
		this.readChar();
		
		// End of line
		if (this.c == '\n') {
			this.readLine();
			return this.nextToken();
		}
		
		// Ignores empty spaces		
		if (this.c == ' ' || this.c == '\t') return this.nextToken();
		
		// Ignores comments		
		if (this.c == '$') {
			this.readLine();
			return this.nextToken();
		}
		
		this.tkValue += this.c;
	
		// Id, decimal numerical constant, integer numerical constant, reserved word 
		if (!Table.symbolList.contains(this.c)) {
			while(!Table.symbolList.contains(this.c)) {
				this.readChar();
				this.tkValue += this.c;
			}
			this.tkValue = this.tkValue.substring(0, this.tkValue.length()-1);
			this.c =  this.fallbackC;
			this.currentCol--;
		}
		// String		
		else if (this.c.equals('"')) {	
			this.readChar();
			this.tkValue += this.c;
			while(!this.c.equals('"')) {
				this.readChar();
				this.tkValue += this.c;
			}
		}
		// Char		
		else if (this.c.equals('\'')) {
			this.readChar();
			if (this.c == '\'') this.tkValue += this.c;
			else {
				this.readChar();
				if (this.c == '\'') { 
					this.tkValue += this.fallbackC;
					this.tkValue += this.c;
				}
			}
		}
		else {
			this.readChar();			
			// >= <= ++
			if (Table.operators.containsKey(this.tkValue + this.c)) this.tkValue += this.c;
			// Other things
			else {
				this.c = this.fallbackC;
				this.currentCol--;
			}
		}
		
		this.previousToken = new Token(this.tkLine, this.tkCol, this.categoryze(), this.tkValue);
		
		
		return this.previousToken;
	}
	
	private void readLine() {
		try {
			this.line = this.br.readLine();
			tkLine++;
			this.currentCol = -1;
			tkCol = -1;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readChar() {
		this.currentCol++;
		if (this.currentCol <= this.line.length()-1) {
			this.fallbackC = this.c;
			this.c = this.line.charAt(this.currentCol);
		}
		else this.c = '\n';
	}
	
	
	private TokenCategory categoryze() {
		if (this.isNegativeUnary()) return TokenCategory.NEGUNOP;
		else if (this.isDecNumConst()) return TokenCategory.DECNUMCONST;
		else if (this.isIntNumConst()) return TokenCategory.INTNUMCONST;
		else if (this.isReservedWord()) return Table.reservedWords.get(this.tkValue);
		else if (this.isDelimiter()) return Table.delimiters.get(this.tkValue);
		else if (this.isId()) return TokenCategory.ID;
		else if (this.isTerminator()) return TokenCategory.TERM;
		else if (this.isSeparator()) return Table.separators.get(this.tkValue);
		else if (this.isOperator()) return Table.operators.get(this.tkValue);
		else if (this.isChar()) return TokenCategory.CHARCONST;
		else if (this.isString()) return TokenCategory.STRINGCONST;
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
		return this.tkValue.matches("([a-z])+(([a-zA-Z])*(\\_)*(\\d)*)*");
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
	
	private boolean isChar() {
		return this.tkValue.matches("\\'[a-zA-Z]{0,1}\\'") || this.tkValue.matches("\\'\\d{0,1}\\'");
	}

	private boolean isString() {
		return this.tkValue.matches("\".*\"");
	}

	private boolean isNegativeUnary() {
		return (
				this.tkValue.matches("-") && (
					this.fallbackC == null || (
							this.previousToken != null &&
							this.previousToken.getCategory() != TokenCategory.ID &&
							this.previousToken.getCategory() != TokenCategory.INTNUMCONST && 
							this.previousToken.getCategory() != TokenCategory.DECNUMCONST
							// )
							// ]
					)
				)
			);
	}
	
}
