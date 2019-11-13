package tool;

public class Input {
	public static java.lang.String fixString(java.lang.String string) {
		if (string.equals("")) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (java.lang.String s : string.trim().replaceAll(" +", " ").split(" ")) {
			result.append(java.lang.String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).append(" ");
		}
		return result.toString().trim();
	}

	public static java.lang.String createAcronym(java.lang.String string) {
		StringBuilder result = new StringBuilder();
		for (java.lang.String s : string.split(" ")) {
			result.append(java.lang.String.valueOf(s.charAt(0)).toLowerCase());
		}
		return result.toString();
	}
}
