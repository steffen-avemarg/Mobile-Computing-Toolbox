package de.emgress.android.resttemplate;

import java.util.List;

public interface NetworkHandlerCallback
{
	public void onResult( int requestID, List<?> results );
}
