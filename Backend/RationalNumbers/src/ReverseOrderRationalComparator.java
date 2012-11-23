import java.util.Comparator;

public class ReverseOrderRationalComparator implements Comparator<Rational>
{
	@Override
	public int compare(Rational a, Rational b)
	{
		return -1 * a.compareTo( b );
	}

}
