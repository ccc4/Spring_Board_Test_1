package mvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import mvc.model.Board;
import mvc.util.JdbcCloser;

@Repository
public class BoardDAO {

	private Board getModel(ResultSet rs) throws SQLException {
		Board obj = new Board();
		
		obj.setIdx(rs.getInt(1));
		obj.setId(rs.getString(2));
		obj.setTitle(rs.getString(3));
		obj.setContents(rs.getString(4));
		obj.setWriteDate(rs.getTimestamp(5));
		obj.setRead_cnt(rs.getInt(6));
		
		return obj;
	}
	
	public int allCount(Connection conn) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT count(*) FROM board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				re = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcCloser.close(rs);
			JdbcCloser.close(pstmt);
		}
		
		return re;
	}
	
	public ArrayList<Board> list(Connection conn, int page, int onePage) {
		
		ArrayList<Board> list = new ArrayList<>();
		Board obj = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM board ORDER BY idx DESC LIMIT ?, ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page - 1) * onePage);
			pstmt.setInt(2, onePage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				obj = getModel(rs);
				list.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcCloser.close(rs);
			JdbcCloser.close(pstmt);
		}
		
		return list;
	}
	
	public int write(Connection conn, Board board) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO board (id, title, contents) values (?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContents());
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

	public Board view(Connection conn, int idx) {
		Board re = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql1 = "UPDATE board SET read_cnt = read_cnt + 1 WHERE idx = ?";
		String sql2 = "SELECT * FROM board WHERE idx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, idx);
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

	public int modify(Connection conn, Board board) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE board SET title = ?, contents = ? WHERE idx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContents());
			pstmt.setInt(3, board.getIdx());
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

	public int delete(Connection conn, int idx) {
		int re = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM board WHERE idx = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
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
	

}
