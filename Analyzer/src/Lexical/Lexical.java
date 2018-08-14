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
	
	private void readLine() {
		try {
			this.line = this.br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readChar() {
		this.currentCol += 1;
		this.c = this.line.charAt(this.currentCol);
	}
	
	public int nextToken() {
		if (this.line == null || this.currentCol == this.line.length()-1) {
			this.readLine();
			if (this.line == null) return 0;
			tkLine++;
			this.currentCol = -1;
			tkCol = 0;
		}
		
		this.readChar();
		while (this.c == ' ' || this.c == '\t') {
			this.tkCol++;
			if (this.currentCol < this.line.length()-1) this.readChar();
		}
		
		return 1;
		
	}
}
