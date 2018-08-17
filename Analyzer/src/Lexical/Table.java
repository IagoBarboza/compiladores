package Lexical;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Table {

	public static List<Character> symbolList = new ArrayList<>();
	public static Map<String, TokenCategory> reservedWords = new HashMap<>();
	public static Map<String, TokenCategory> delimiters = new HashMap<>();
	public static Map<String, TokenCategory> separators = new HashMap<>();
	public static Map<String, TokenCategory> operators = new HashMap<>();
	
	static {
		// Symbol List		
		symbolList.add(' ');
		symbolList.add(',');
		symbolList.add(';');
		symbolList.add('+');
		symbolList.add('-');
		symbolList.add('*');
		symbolList.add('\\');
		symbolList.add('/');
		symbolList.add('#');
		symbolList.add('$');
		symbolList.add('<');
		symbolList.add('>');
		symbolList.add('=');
		symbolList.add('~');
		symbolList.add('(');
		symbolList.add(')');
		symbolList.add('[');
		symbolList.add(']');
		symbolList.add('{');
		symbolList.add('}');
		symbolList.add('\'');
		symbolList.add('"');
		symbolList.add('\n');
		symbolList.add('\t');
		symbolList.add('^');
		
		// Reserved Words
		reservedWords.put("read", TokenCategory.RWREAD);
		reservedWords.put("print", TokenCategory.RWPRINT);
		reservedWords.put("if", TokenCategory.RWIF);
		reservedWords.put("else", TokenCategory.RWELSE);
		reservedWords.put("repeater", TokenCategory.RWREPEATER);
		reservedWords.put("while", TokenCategory.RWWHILE);
		reservedWords.put("return", TokenCategory.RWRETURN);
		reservedWords.put("none", TokenCategory.TNONE);
		reservedWords.put("int", TokenCategory.TINT);
		reservedWords.put("float", TokenCategory.TFLOAT);
		reservedWords.put("boolean", TokenCategory.TLOGIC);
		reservedWords.put("char", TokenCategory.TCHAR);
		reservedWords.put("string", TokenCategory.TSTRING);
		reservedWords.put("main", TokenCategory.MAIN);
		reservedWords.put("true", TokenCategory.LOGICCONST);
		reservedWords.put("false", TokenCategory.LOGICCONST);
		reservedWords.put("not", TokenCategory.NEGLOGICOP);
		reservedWords.put("and", TokenCategory.ANDLOGICOP);
		reservedWords.put("or", TokenCategory.ORLOGICOP);
		reservedWords.put("not", TokenCategory.NEGLOGICOP);
		
		// Delimiters
		delimiters.put("{", TokenCategory.SCOPEBEGIN);
		delimiters.put("}", TokenCategory.SCOPEEND);
		delimiters.put("(", TokenCategory.PARAMBEGIN);
		delimiters.put(")", TokenCategory.PARAMEND);
		delimiters.put("[", TokenCategory.ARRAYBEGIN);
		delimiters.put("]", TokenCategory.ARRAYEND);
		
		// Separators
		separators.put(",", TokenCategory.COMMA);
		separators.put(";", TokenCategory.SEMICOLON);
		
		// Operators
		operators.put("+", TokenCategory.ADDARITHOP);
		operators.put("-", TokenCategory.ADDARITHOP);
		operators.put("*", TokenCategory.MULTARITHOP);
		operators.put("/", TokenCategory.MULTARITHOP);
		operators.put("^", TokenCategory.EXPARITHOP);
		operators.put("+", TokenCategory.ADDARITHOP);
		operators.put(">", TokenCategory.INEQRELOP);
		operators.put(">=", TokenCategory.INEQRELOP);
		operators.put("<", TokenCategory.INEQRELOP);
		operators.put("<=", TokenCategory.INEQRELOP);
		operators.put("==", TokenCategory.EQRELOP);
		operators.put("~=", TokenCategory.EQRELOP);
		operators.put("=", TokenCategory.ATTRIBUTIVEOP);
		operators.put("++", TokenCategory.CONCOP);
	}
}
