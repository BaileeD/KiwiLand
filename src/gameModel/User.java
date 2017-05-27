package gameModel;

/**
 * @author Chaitanya Varma
 * @version May 2017
 */
public class User
{
	private int    userId;
	private String playerName;
	private String userName;
	private String password;
	private static Database db = new Database();

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getUserId()
	{
		return this.userId;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	public String getPlayerName()
	{
		return this.playerName;
	}

	public int getHighestLevelReached()
	{
		return db.getHighestReachedLevel(userId);
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/*
	* checks whether a user exists
	* @Param userName - user name
	*/
	public static boolean isUserExists(String userName)
	{
		return db.checkUserName(userName);
	}

	/*
	* creates a new user
	* @Param playerName - player name
	* @Param userName - user name
	* @Param password - password
	*/
	public static boolean createNewUser(String playerName, String userName, String password)
	{
		return db.createNewUser(playerName, userName, password);
	}

	/*
	* logs in a user
	* @Param userName - user name
	* @Param password - password
	*/
	public static User loginUser(String userName, String password)
	{
		return db.loginUser(userName, password);
	}
}
