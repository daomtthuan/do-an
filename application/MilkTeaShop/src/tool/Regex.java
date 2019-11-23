package tool;

public class Regex {
	public static final String ACCOUNT = "^\\w{2,50}$";
	public static final String PASSWORD = "^\\w{1,50}$";
	public static final String NAME = "^[a-zA-Z ]{1,50}$";
	public static final String ADDRESS = "^.{1,100}$";
	public static final String PHONE = "^(([+][0-9]{2})|0)[0-9]{9,12}$";
	public static final String EMAIL = "^([a-z][a-z0-9_.]{2,30}@[a-z0-9]{2,}(.[a-z0-9]{2,4})+)?$";
	public static final String DISCOUNT = "^.{1,50}$";
	public static final String NUMBER = "^\\d+(.\\d+)?$";
}
