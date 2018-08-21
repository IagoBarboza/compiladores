import Lexical.Lexical;
import Lexical.Token;

public class Analyzer {
	
	public static Lexical lexical;

	public static void main(String[] args) {
		lexical = new Lexical(args[0]);
		Token tk = lexical.nextToken();
		while (tk != null) {
			System.out.println(tk);
			tk = lexical.nextToken();
		}
	}	

}
