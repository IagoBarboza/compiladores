import Lexical.Lexical;

public class Analyzer {
	
	public static Lexical lexical;

	public static void main(String[] args) {
		lexical = new Lexical(args[0]);
		lexical.nextToken();
	}	

}
