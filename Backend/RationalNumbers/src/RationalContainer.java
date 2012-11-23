import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RationalContainer
{
	static List<Rational> rationals = new ArrayList<Rational>();

	public static void add( Rational rational )
	{
		rationals.add( rational );
	}

	public static void remove( Rational rational )
	{
		rationals.remove( rational );
	}

	public static void addAll( List<Rational> values )
	{
		rationals.addAll( values );
	}

	public static void sort()
	{
		Collections.sort( rationals );
	}

	public static void sort( Comparator<Rational> comparator)
	{
		Collections.sort( rationals, comparator );
	}

	public static Rational findMax()
	{
		return Collections.max( rationals );
	}

	public static Rational findMin()
	{
		return Collections.min( rationals );
	}

	public static Rational totalSum()
	{
		Rational sum = new Rational( 0,1 );

		for( Rational r : rationals )
		{
			sum = sum.add( r );
		}

		return sum;
	}

	public static Rational totalProduct()
	{
		Rational prod = new Rational( 1, 1 );

		for( Rational r : rationals )
		{
			prod = prod.mul( r );
		}

		return prod;
	}

	public static void printAll()
	{
		for( Rational r : rationals )
		{
			System.out.println( r );
		}
	}




}
