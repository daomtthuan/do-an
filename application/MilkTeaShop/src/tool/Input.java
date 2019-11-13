package tool;

public class Input {
	public static String fixString(String input) {
		if (input.equals("")) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (java.lang.String s : input.trim().replaceAll(" +", " ").split(" ")) {
			result.append(java.lang.String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
		}
		return result.toString().trim();
	}

	public static String createAcronym(String name) {
		StringBuilder result = new StringBuilder();
		for (java.lang.String s : name.split(" ")) {
			result.append(java.lang.String.valueOf(s.charAt(0)).toLowerCase());
		}
		return result.toString();
	}
}
