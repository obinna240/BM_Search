package com.pcg.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * This is a test class
 * @author oonyimadu
 *
 */
public class DBCaller {

	/**
	private static final class BirminghamEventObjectRowMapper implements RowMapper<BirminghamEventObject>
	{

		@Override
		public BirminghamEventObject mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			BirminghamEventObject b = new BirminghamEventObject();
			b.setEventId(rs.getString("eventId"));
			b.setEventName(rs.getString("eventName"));
			return b;
		}
		
	}
	
	private static final class BirminghamEventObjectRowMapper extends StoredProcedure
	{

		public BirminghamEventObject mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			BirminghamEventObject b = new BirminghamEventObject();
			b.setEventId(rs.getString("eventId"));
			b.setEventName(rs.getString("eventName"));
			return b;
		}
		
	}*/
	public static void main(String[] args)
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
	
		ds.setUrl("jdbc:sqlserver://sctest1.cloudapp.net;databaseName=bm2");
	//	ds.setUrl("jdbc:sqlserver://sctest1.cloudapp.net:1433;databaseName=bm2;integratedSecurity=true");//authenticationScheme=JavaKerberos");
	
		ds.setUsername("birminghampc");
		ds.setPassword("KvemRdYoEO0vx2ZZ");
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setInitialSize(5);			
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("BirminghamPostcode");
		
		/**Map<String, Object> resultMap =  new SimpleJdbcCall(jdbcTemplate).withProcedureName("BirminghamPostcode")
				.
			//	.returningResultSet("ss", new BirminghamEventObjectRowMapper()).execute(new MapSqlParameterSource());
		resultMap.entrySet().forEach(System.out::println);
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
	          System.out.println(entry.getKey() + ":" + entry.getValue());
	          
	   }*/
		
		Map<String,Object> retVal = jdbcCall.execute();
	
		for (Map.Entry<String, Object> entry : retVal.entrySet()) {
	          System.out.println(entry.getKey() + ":" + entry.getValue());
	          
	   }
		//System.out.println(retVal.get("result - set - 1"));
		//System.out.println(retVal.get("ttttttttttttttt"));
		
		
	}
}



//ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");jdbc:mysql://192.168.0.123:3306/MyDatabase
//ds.setUrl("jdbc:sqlserver://sctest1.cloudapp.net\bm2:1433");


//ds.setUrl("jdbc:sqlserver://sctest1.pcgeu.local:1433/bm2");
