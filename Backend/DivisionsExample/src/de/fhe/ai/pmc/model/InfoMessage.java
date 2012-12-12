package de.fhe.ai.pmc.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InfoMessage 
{
	private String text;
	private Date timestamp;
	
	public InfoMessage() {}
	
	public InfoMessage( String text )
	{
		this.text = text;
		this.timestamp = new Date();
	}

	@XmlElement
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@XmlAttribute
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}

