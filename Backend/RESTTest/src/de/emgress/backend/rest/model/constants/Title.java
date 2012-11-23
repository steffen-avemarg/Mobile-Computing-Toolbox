package de.emgress.backend.rest.model.constants;

public enum Title
{
	HERR( "Herr" ),
	FRAU( "Frau" ),
	DR( "Dr." ),
	PROF( "Prof." );

	private String titleString;

	Title( String titleString )
	{
		this.titleString = titleString;
	}

	@Override
	public String toString()
	{
		return this.titleString;
	}

}
