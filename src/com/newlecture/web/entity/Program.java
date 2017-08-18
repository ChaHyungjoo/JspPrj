package com.newlecture.web.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT * FROM Notice";
		
		//jdbc 드라이버 로드
		Class.forName("com.mysql.jdbc.Driver");
		
		//연결 /인증
		Connection con = DriverManager.getConnection(url, "sist", "cclass");
		
		//실행
		Statement st = con.createStatement();
		
		//결과 가져오기
		ResultSet rs = st.executeQuery(sql);
		
		
		// Model--------------------------------------------------------------------------------------
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()) {
			Notice n = new Notice();
			n.setId(rs.getString("ID"));
			n.setTitle(rs.getString("TITLE"));
			n.setContent(rs.getString("CONTENT"));
			n.setHit(rs.getInt("HIT"));
			list.add(n);
		}
		
		rs.close();
		st.close();
		con.close();
		
		for(Notice n: list)
			System.out.println(n.getId());

	}

}
