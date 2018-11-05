package mvc.services;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.model.Member;
import mvc.repository.MemberDAO;
import mvc.util.JdbcCloser;
import mvc.util.JdbcConnection;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	public Object join(Member member) {
		Connection conn = JdbcConnection.getConnection();
		Object result = dao.join(conn, member);
		JdbcCloser.close(conn);
		
		return result;
	}
	
	public Object login(Member member) {
		Connection conn = JdbcConnection.getConnection();
		int check = dao.login(conn, member);
		Member obj = null;
		
		if(check == 1) {
			obj = dao.getModel(conn, member.getId());
		}
		JdbcCloser.close(conn);
		
		return obj;
	}

	public Object modify(Member member) {
		Connection conn = JdbcConnection.getConnection();
		int check = dao.modify(conn, member);
		Member obj = null;
		if(check == 1) {
			obj = dao.getModel(conn, member.getId());
		}
		JdbcCloser.close(conn);
		
		return obj;
	}
	
}
