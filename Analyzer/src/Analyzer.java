import Lexical.Lexical;
import Lexical.Token;

public class Analyzer {
	
	public static Lexical lexical;

	public static void main(String[] args) {
		lexical = new Lexical(args[0]);
		Token tk = lexical.nextToken();
		System.out.println(tk);
		while (tk != null) {
			tk = lexical.nextToken();
			if (tk != null) System.out.println(tk);
		}
	}	

}
