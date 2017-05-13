/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameModel;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * This class handles database operations
 *
 * @author Chaitanya Varma
 * @version April 2017
 */
public class Database
{

	private Connection connection = null;

	public static String dbUrl    = "";
	public static String userName = "";
	public static String password = "";

	/**
	 * Constructor. Reads database connection properties from kiwiIsland.cfg file.
	 */
	public Database()
	{
		try
		{
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File("kiwiIsland.cfg")));

			if (!(properties.getProperty("dburl") == null || properties.getProperty("dburl").equals("")))
			{
				dbUrl = properties.getProperty("dburl");
			}
			if (!(properties.getProperty("username") == null || properties.getProperty("username").equals("")))
			{
				userName = properties.getProperty("username");
			}
			if (!(properties.getProperty("password") == null || properties.getProperty("password").equals("")))
			{
				password = properties.getProperty("password");
			}
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	/**
	 * Opens a MySQL database connection
	 */
	private void openConnection()
	{
		try
		{
			String dbDriverURL = "com.mysql.jdbc.Driver";
			Class.forName(dbDriverURL);

			connection = DriverManager.getConnection(dbUrl, userName, password);
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	/**
	 * Gets facts from database for specified occupant
	 *
	 * @param occupant occupant like Kiwi or Rat etc
	 */
	public ArrayList<String> getFacts(String occupant)
	{
		ArrayList<String> facts = null;
		openConnection();
		try
		{
			String sqlQuery = "SELECT fact FROM facts Where occupant='" + occupant + "'";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			facts = new ArrayList<String>();
			while (resultSet.next())
			{
				facts.add(resultSet.getString(1));
			}
			resultSet.close();
			statement.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return facts;
	}

	/**
	 * Gets all different occupants from the database
	 */
	public ArrayList<String> getOccupants()
	{
		ArrayList<String> occupants = null;
		openConnection();
		try
		{
			String sqlQuery = "SELECT DISTINCT occupant FROM facts";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			occupants = new ArrayList<String>();
			while (resultSet.next())
			{
				occupants.add(resultSet.getString(1));
			}
			resultSet.close();
			statement.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return occupants;
	}

	/*
	* Saves a user game to database
	* @param playerName - player name
	* @param saveName - player save name
	* @param level - game level
	* @param date - current date
	*/
	public boolean saveGame(String playerName, String saveName, int level, Date date)
	{
		boolean insert = false;
		openConnection();
		try
		{
			PreparedStatement st = connection
					.prepareStatement("insert into gamesaves(playername, savename, level, date) values(?, ?, ?, ?)");
			st.setString(1, playerName);
			st.setString(2, saveName);
			st.setInt(3, level);
			st.setTimestamp(4, new java.sql.Timestamp(date.getTime()));

			int result = st.executeUpdate();
			if (result == 1)
			{
				insert = true;
			}
			st.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return insert;
	}

	/*
	* gets all game saves of a user ordered by last game saved date
	* @param playerName - player name
	*/
	public ArrayList<GameSave> getPlayerGameSaves(String playerName)
	{
		ArrayList<GameSave> gameSaves = null;
		openConnection();
		try
		{
			String sqlQuery =
					"SELECT * FROM gamesaves Where playername='" + playerName + "' ORDER BY gamesaves.date DESC";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			gameSaves = new ArrayList<GameSave>();
			while (resultSet.next())
			{
				GameSave gameSave = new GameSave();
				gameSave.setGameSaveId(resultSet.getInt(1));
				gameSave.setPlayerName(resultSet.getString(2));
				gameSave.setSaveName(resultSet.getString(3));
				gameSave.setLevel(resultSet.getInt(4));
				gameSave.setSaveDate(resultSet.getTimestamp(5));
				gameSaves.add(gameSave);
			}
			resultSet.close();
			statement.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return gameSaves;
	}

	/*
	* gets all game saves of a user ordered by last game saved date
	*/
	public ArrayList<GameSave> getAllGameSaves()
	{
		ArrayList<GameSave> gameSaves = null;
		openConnection();
		try
		{
			String sqlQuery = "SELECT * FROM gamesaves ORDER BY gamesaves.date DESC";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			gameSaves = new ArrayList<GameSave>();
			while (resultSet.next())
			{
				GameSave gameSave = new GameSave();
				gameSave.setGameSaveId(resultSet.getInt(1));
				gameSave.setPlayerName(resultSet.getString(2));
				gameSave.setSaveName(resultSet.getString(3));
				gameSave.setLevel(resultSet.getInt(4));
				gameSave.setSaveDate(resultSet.getTimestamp(5));
				gameSaves.add(gameSave);
			}
			resultSet.close();
			statement.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return gameSaves;
	}

	/*
	* gets last game saved date
	* @param playerName - player name
	*/
	public GameSave getLastGameSave(String playerName)
	{
		GameSave gameSave = null;
		openConnection();
		try
		{
			String sqlQuery = "SELECT * FROM gamesaves WHERE playername='" + playerName + "'";
			sqlQuery += " ORDER BY gamesaves.date DESC LIMIT 1";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while (resultSet.next())
			{
				gameSave = new GameSave();
				gameSave.setGameSaveId(resultSet.getInt(1));
				gameSave.setPlayerName(resultSet.getString(2));
				gameSave.setSaveName(resultSet.getString(3));
				gameSave.setLevel(resultSet.getInt(4));
				gameSave.setSaveDate(resultSet.getTimestamp(5));
			}
			resultSet.close();
			statement.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return gameSave;
	}

	/*
	* deletes a game save
	* @param id - gamesaveid in gamesaves table
	*/
	public boolean deleteGameSave(int id)
	{
		boolean delete = false;
		openConnection();
		try
		{
			String sqlQuery = "DELETE FROM gamesaves WHERE gamesaveid='" + id + "'";

			Statement statement = connection.createStatement();
			int result = statement.executeUpdate(sqlQuery);
			if (result == 1)
			{
				delete = true;
			}
			statement.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return delete;
	}

	/*
	* Updates a game save
	* @param id - gamesaveid in gamesaves table
	* @param level - game level
	* @param date - current date
	*/
	public boolean updateGame(int id, int level, Date date)
	{
		boolean insert = false;
		openConnection();
		try
		{
			PreparedStatement st = connection
					.prepareStatement("update gamesaves set level=?, date=? where gamesaveid=?");
			st.setInt(1, level);
			st.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
			st.setInt(3, id);

			int result = st.executeUpdate();
			if (result == 1)
			{
				insert = true;
			}
			st.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		finally
		{
			closeConnection();
		}
		return insert;
	}

	/**
	 * Closes database connection
	 */
	private void closeConnection()
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (Exception ex)
		{
			java.util.logging.Logger.getLogger(Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
}