package es.raro.model;

public class UnmatchingDescription extends Exception {

	private String regex;
	private String description;

	public UnmatchingDescription(String regex, String description) {
		this.regex = regex;
		this.description = description;
	}

	@Override
	public String getMessage() {
		return "Description '"+description+"' doesn't match regex '"+regex+"'";
	}
	
}
