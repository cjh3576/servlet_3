package com.jh.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jh.page.SearchRow;
import com.jh.util.DBConnector;

public class noticeDAO {
	//열 총 갯수
	public int getTotalCount(SearchRow searchRow) throws Exception {
		int result;
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice where "+searchRow.getSearch().getKind()+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		result = rs.getInt(1);
		
		DBConnector.disConnection(con, st, rs);
		return result;
	}
	
	
	//selectList
	public ArrayList<noticeDTO> selectList(SearchRow searchRow) throws Exception{
		ArrayList<noticeDTO> ar = new ArrayList<noticeDTO>();
		
		Connection con =  DBConnector.getConnect();
		String sql = "select * from " + 
				"(select rownum R, p.* from " + 
				"(select * from notice where "+searchRow.getSearch().getKind()+" like ? order by num desc) p) " + 
				"where R between ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		st.setInt(2, searchRow.getStartRow());
		st.setInt(3, searchRow.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			noticeDTO dto = new noticeDTO();
			dto.setNum(rs.getInt("num"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setHit(rs.getInt("hit"));
			ar.add(dto);
		}
		DBConnector.disConnection(con, st, rs);
		return ar;
	}
	
	//selectOne
	public noticeDTO selectOne(int num) throws Exception{
		noticeDTO dto = new noticeDTO();
		Connection con = DBConnector.getConnect();
		
		String sql = "select * from notice where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			dto.setNum(rs.getInt("num"));
			dto.setTitle(rs.getString("title"));
			dto.setContents(rs.getString("contents"));
			dto.setWriter(rs.getString("writer"));
			dto.setReg_date(rs.getString("reg_date"));
			dto.setHit(rs.getInt("hit"));
		}
		DBConnector.disConnection(con, st, rs);
		return dto;
	}
	
	//delete
	public int delete(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete notice where num=?";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		
		int result = st.executeUpdate();
		
		DBConnector.disConnection(con, st);
		return result;
	}
	
	//update
	public int update(noticeDTO dto) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "update notice set title=?, contents=? where num=? ";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, dto.getTitle());
		st.setString(2, dto.getContents());
		st.setInt(3, dto.getNum());
		
		int result = st.executeUpdate();
		DBConnector.disConnection(con, st);
		return result;
	}
	//조회수 update
	public int updateHit(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql ="update notice set hit = hit+'1' where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnection(con, st);
		return result;
	}
	public int getNum() throws Exception{
	      int result = 0;
	      Connection con = DBConnector.getConnect();
	      String sql = "select notice_seq.nextval from dual";
	      PreparedStatement st = con.prepareStatement(sql);
	      ResultSet rs = st.executeQuery();
	      rs.next();
	      result = rs.getInt(1);
	      
	      DBConnector.disConnection(con, st, rs);
	      return result;
		
	}
	
	//insert
	public int insert(noticeDTO dto, Connection con) throws Exception{
		String sql = "insert into notice values(?,?,?,?,sysdate,0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, dto.getNum());
		st.setString(2, dto.getTitle());
		st.setString(3, dto.getContents());
		st.setString(4, dto.getWriter());
		
		int result = st.executeUpdate();
		st.close();
		return result;
	}
}
