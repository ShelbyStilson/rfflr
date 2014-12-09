package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RaffleEventDaoImpl implements RaffleEventDAO {
	
	private Connection conn;
	
	public RaffleEventDaoImpl() {
		
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://hax.cefatfwntgxp.us-west-2.rds.amazonaws.com/hackathon?" +
		                                   "user=haxuzr&password=SecretShit55");

		} catch (SQLException ex) {
			System.out.println("Crapped Out.");
		}
	}

	@Override
	public RaffleEvent getRaffleEvent(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM Events where eventsId = " + id;
		RaffleEvent re = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			re = new RaffleEvent(id, rs.getString("title"));
			re.setStartTime(rs.getTimestamp("startTime"));
			re.setEndTime(rs.getTimestamp("endTime"));
			re.setDescription(rs.getString("description"));
			re.setHost(rs.getString("host"));
			re.setImage(rs.getString("image"));
			re.setLink(rs.getString("link"));
			re.setContactPhone(rs.getString("contactPhone"));
			re.setContactEmail(rs.getString("contactEmail"));
			re.setLocation(rs.getString("location"));			
		}
		catch (SQLException ex) {
			System.out.println("Crapped Out 2.");
		}
		return re;
	}

	@Override
	public RaffleEvent createRaffleEvent(String title,
			Date startTime, 
			Date endTime, 
			String description,
			String host,
			String image,
			String link,
			String contactPhone,
			String contactEmail,
			String location) {
		
		RaffleEvent re = new RaffleEvent();
		re.setTitle(title);
		re.setStartTime(startTime);
		re.setEndTime(endTime);
		re.setDescription(description);
		re.setHost(host);
		re.setImage(image);
		re.setLink(link);
		re.setContactPhone(contactPhone);
		re.setContactEmail(contactEmail);
		re.setLocation(location);
		
		if (title != null) title = "'" + title + "'";
		
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sTime = "null";
		if (startTime != null) sTime = "'" + sdf.format(startTime) + "'";
		String eTime = "null";
		if (endTime != null) eTime = "'" + sdf.format(endTime) + "'";
		
		if (description != null) description = "'" + description + "'";
		if (host != null) host = "'" + host + "'";
		if (image != null) image = "'" + image + "'";
		if (link != null) link = "'" + link + "'";
		if (contactPhone != null) contactPhone = "'" + contactPhone + "'";
		if (contactEmail != null) contactEmail = "'" + contactEmail + "'";
		if (location != null) location = "'" + location + "'";
		
		String query = "INSERT INTO Events (title, startTime, endTime, description, host" +
		        ", image, link, contactPhone, contactEmail, location) " + 
				"VALUES (" + title + ", " + sTime + ", " + eTime +
				", " + description + ", " + host + ", " + image +
				", " + link + ", " + contactPhone + ", " + contactEmail + 
				", " + location + ")";

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int key = rs.getInt(1);
			re.setId(key);		
		}
		catch (SQLException ex) {
			System.out.println("Crapped out 3.");
			System.out.println(query);
		}
		System.out.println(query);
		return re;
	}

	@Override
	public List<RaffleEvent> getAllRaffleEvents() {
		List<RaffleEvent> all = new ArrayList<RaffleEvent>();
		RaffleEvent re;
		
		String query = "SELECT * FROM Events";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				re = new RaffleEvent();
				re.setId(rs.getInt("eventsId"));
				re.setTitle(rs.getString("title"));
				re.setStartTime(rs.getTimestamp("startTime"));
				re.setEndTime(rs.getTimestamp("endTime"));
				re.setDescription(rs.getString("description"));
				re.setHost(rs.getString("host"));
				re.setImage(rs.getString("image"));
				re.setLink(rs.getString("link"));
				re.setContactPhone(rs.getString("contactPhone"));
				re.setContactEmail(rs.getString("contactEmail"));
				re.setLocation(rs.getString("location"));
				all.add(re);
			}
		}
		catch (SQLException se) {
			System.out.println("Crapped out 4.");
		}
		return all;
	}

	@Override
	public RaffleEvent updateRaffleEvent(RaffleEvent re) {
		
		String title = "null";
		if (re.getTitle() != null) title = "'" + re.getTitle() + "'";
		
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String sTime = "null";
		if (re.getStartTime() != null) sTime = "'" + sdf.format(re.getStartTime()) + "'";
		String eTime = "null";
		if (re.getEndTime() != null) eTime = "'" + sdf.format(re.getEndTime()) + "'";
		
		String description = "null";
		if (re.getDescription() != null) description = "'" + re.getDescription() + "'";
		
		String host = "null";
		if (re.getHost() != null) host = "'" + re.getHost() + "'";
		
		String image = "null";
		if (re.getImage() != null) image = "'" + re.getImage() + "'";
		
		String link = "null";
		if (re.getLink() != null) link = "'" + re.getLink() + "'";
		
		String contactPhone = "null";
		if (re.getContactPhone() != null) contactPhone = "'" + re.getContactPhone() + "'";
		
		String contactEmail = "null";
		if (re.getContactEmail() != null) contactEmail = "'" + re.getContactEmail() + "'";
		
		String location = "null";
		if (re.getLocation() != null) location = "'" + re.getLocation() + "'";
		
		String query = "UPDATE Events SET " +
				"title = " + title + ", " +
				"startTime = " + sTime + ", " +
				"endTime = " + eTime + ", " +
				"description = " + description + ", " +
				"host = " + host + ", " +
				"image = " + image + ", " +
				"link = " + link + ", " +
				"contactPhone = " + contactPhone + ", " +
				"contactEmail = " + contactEmail + ", " +
				"location = " + location + " " +
				"WHERE eventsId = " + re.getId();
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException se) {
			System.out.println("Crapped out Update");
		}

		return re;
	}

	@Override
	public void deleteRaffleEvent(int id) {
		String query = "DELETE FROM Events WHERE eventsId = " + id;
		
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException se) {
			System.out.println("Crapped out delete");
		}
		
	}
	
	

}
