package de.emgress.backend.rest.model.constants;

public enum DayOfWeek
{
	MONDAY( "Montag" ),
	TUESDAY( "Dienstag" ),
	WEDNESDAY( "Mittwoch" ),
	THURSDAY( "Donnerstag" ),
	FRIDAY( "Freitag" ),
	SATURDAY( "Samstag" ),
	SUNDAY( "Sonntag" );

	private String value;

	private DayOfWeek( String value )
	{
		this.value = value;
	}
}
