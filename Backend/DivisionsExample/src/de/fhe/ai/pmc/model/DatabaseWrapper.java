package de.fhe.ai.pmc.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseWrapper 
{
	private static List<Division> divisions = new ArrayList<Division>();
	
	static
	{
		for( int i = 1; i <= 10; i++ )
		{
			Division d = new Division( "Name " + i, "Address - Very Long - " + (i*3) );
			
			for( int j = 1; j <= (i*10)%7; j++ )
			{
				Employee e = new Employee( "First " + j, "Last " + j, j%4 + 1, (1200.0 * j)  );
				
				d.getEmployees().add( e );
			}
			
			divisions.add( d );
		}
	}

	public static List<Division> getDivisions() {
		return divisions;
	}

	public static void setDivisions(List<Division> divisions) {
		DatabaseWrapper.divisions = divisions;
	}
	
	public static void printAll()
	{
		for( Division d : divisions )
		{	
			System.out.println( d );
			
			for( Employee e : d.getEmployees() )
			{
				System.out.println( e );
			}
		}
	}
	
}
