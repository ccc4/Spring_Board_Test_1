package mvc.services;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.model.Board;
import mvc.model.Paging;
import mvc.repository.BoardDAO;
import mvc.util.JdbcCloser;
import mvc.util.JdbcConnection;

@Service
public class BoardService {
	
	private static final int ONE_PAGE = 5;
	private static final int ONE_SECTION = 5;
	
	@Autowired
	private BoardDAO dao;
	
	public Object list(int page) {
		Connection conn = JdbcConnection.getConnection();
		
		int allCount = dao.allCount(conn);
		int onePage = ONE_PAGE;
		int oneSection = ONE_SECTION;
		
		int totalPage = allCount / onePage + (allCount % onePage != 0 ? 1 : 0);
		
		if(page < 1 || page > totalPage) {
			return null;
		}
		
		int startPage = (page - 1) / oneSection * oneSection;
		if(startPage % oneSection == 0) startPage += 1;
		
		int endPage = startPage + oneSection - 1;
		if(endPage > totalPage) endPage = totalPage;
		
		
		ArrayList<Board> list = dao.list(conn, page, onePage);
		JdbcCloser.close(conn);
		
		Paging result = new Paging(list, page, totalPage, startPage, endPage);
		
		return result;
	}

	public Object view(int idx) {
		Connection conn = JdbcConnection.getConnection();
		Object result = dao.view(conn, idx);
		JdbcCloser.close(conn);
		
		return result;
	}

	public Object write(Board board) {
		Connection conn = JdbcConnection.getConnection();
		Object result = dao.write(conn, board);
		JdbcCloser.close(conn);
		
		return result;
	}

	public Object modify(Board board) {
		Connection conn = JdbcConnection.getConnection();
		Object result = dao.modify(conn, board);
		JdbcCloser.close(conn);
		
		return result;
	}

	public Object delete(int idx) {
		Connection conn = JdbcConnection.getConnection();
		Object result = dao.delete(conn, idx);
		JdbcCloser.close(conn);
		
		return result;
	}

	
	
}
