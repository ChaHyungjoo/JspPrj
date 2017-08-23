import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.newlecture.web.entity.Notice;


//@WebServlet("/hello")
public class Nana extends HttpServlet{
   public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
   {
      OutputStream os = response.getOutputStream();

      PrintStream out= new PrintStream(os);
//      out.println("Hello Servlet");
      
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT * FROM Notice";
		
		//jdbc 드라이버 로드
		try {
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
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
      
   }
}