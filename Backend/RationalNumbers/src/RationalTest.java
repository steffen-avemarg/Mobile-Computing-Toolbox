import java.util.Scanner;

public class RationalTest
{

	public static void main(String[] args)
	{
		RationalContainer.add( new Rational( 1, 3 ) );
		RationalContainer.add( new Rational( 1, 4 ) );
		RationalContainer.add( new Rational( 1, 5 ) );
		RationalContainer.add( new Rational( 20, 5 ) );
		RationalContainer.add( Rational.valueOf( "3/2" ) );
		RationalContainer.add( Rational.valueOf( "23/7" ) );
		RationalContainer.add( Rational.valueOf( "4/9" ) );
		RationalContainer.add( Rational.valueOf( "5/11" ) );

		/*
		 * 	Capturing User Input and add the Value
		 */
		Scanner scanner = new Scanner( System.in );
		String input = scanner.nextLine();
		RationalContainer.add( Rational.valueOf( input ) );

		System.out.println( "Minimum: " + RationalContainer.findMin() );
		System.out.println( "Maximum: " + RationalContainer.findMax() );

		System.out.println( "Gesamtsumme: " + RationalContainer.totalSum() );
		System.out.println( "Gesamtprodukt: " + RationalContainer.totalProduct() );

		System.out.println( "Aufsteigend" );
		RationalContainer.sort();
		RationalContainer.printAll();

		System.out.println( "Absteigend" );
		RationalContainer.sort( new ReverseOrderRationalComparator() );
		RationalContainer.printAll();

		/*
		 * Clone Test
		 */
		try
		{
			Rational r = new Rational( 1, 2 );
			Rational copyOfR = r.clone();

			System.out.println( "Equals according to '=='       :  " + ( r == copyOfR )  );
			System.out.println( "Equals according to 'equals()' :  " + r.equals( copyOfR )  );
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
	}

	/* Ausgabe Testrun

		Eingabe: 3/6
		Minimum: 1/5
		Maximum: 4/1
		Gesamtsumme: 152017/13860
		Gesamtprodukt: 23/693
		Aufsteigend
		1/5
		1/4
		1/3
		4/9
		5/11
		1/2
		3/2
		23/7
		4/1
		Absteigend
		4/1
		23/7
		3/2
		1/2
		5/11
		4/9
		1/3
		1/4
		1/5
		Equals according to '=='       :  false
		Equals according to 'equals()' :  true

	 */
}
