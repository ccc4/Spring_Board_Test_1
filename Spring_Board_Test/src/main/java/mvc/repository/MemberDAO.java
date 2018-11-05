package mvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.service.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.springframework.stereotype.Repository;

import mvc.model.Member;
import mvc.util.JdbcCloser;

@Repository
public class MemberDAO {
	
	private Member getModel(ResultSet rs) throws SQLException {
		Member obj = new Member();
		
		obj.setId(rs.getString(1));
		obj.setPw(rs.getString(2));
		obj.setName(rs.getString(3));
		
		return obj;
	}
	
	public Member getModel(Connection conn, String id) {
		Member re = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM member WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				re = getModel(rs);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcCloser.close(rs);
			JdbcCloser.close(pstmt);
		}
		
		return re;
	}
	
	public int join(Connection conn, Member member) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO member (id, pw, name) values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			
			int check = pstmt.executeUpdate();
			if(check == 0) re = 0;
			else re = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcCloser.close(pstmt);
		}
		return re;
	}

	public int login(Connection conn, Member member) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT pw FROM member WHERE id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(member.getPw())) {
					re = 1;
				} else {
					re = 0;
				}
			} else {
				re = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcCloser.close(rs);
			JdbcCloser.close(pstmt);
		}
		return re;
	}

	public int modify(Connection conn, Member member) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE member SET pw = ?, name = ? WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getId());
			
			int check = pstmt.executeUpdate();
			if(check == 0) re = 0;
			else re = 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcCloser.close(pstmt);
		}
		return re;
	}

//	public int pwChange(Connection conn, String id, String newPw) {
//		int re = 0;
//		
//		PreparedStatement pstmt = null;
//		
//		String sql = "UPDATE member SET password = ? WHERE id = ?";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, newPw);
//			pstmt.setString(2, id);
//			
//			int check = pstmt.executeUpdate();
//			if(check == 0) re = 0;
//			else re = 1;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcCloser.close(pstmt);
//		}
//		return re;
//	}
	
	
}
