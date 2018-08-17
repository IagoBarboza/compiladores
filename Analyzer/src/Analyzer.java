import Lexical.Lexical;

public class Analyzer {
	
	public static Lexical lexical;

	public static void main(String[] args) {
		lexical = new Lexical(args[0]);
		System.out.println(lexical.nextToken());
		System.out.println(lexical.nextToken());
		System.out.println(lexical.nextToken());
//		"(\\d+(\\.\\d+)?)"
	}	

}
