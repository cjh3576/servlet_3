package com.jh.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jh.util.DBConnector;

public class noticeDAO {
	//열 총 갯수
	public int getTotalCount(String kind,String search) throws Exception {
		int result;
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from notice where "+kind+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		result = rs.getInt(1);
		
		DBConnector.disConnection(con, st, rs);
		return result;
	}
	
	
	//selectList
	public ArrayList<noticeDTO> selectList(String kind, String search, int startRow, int lastRow) throws Exception{
		ArrayList<noticeDTO> ar = new ArrayList<noticeDTO>();
		
		Connection con =  DBConnector.getConnect();
		String sql = "select * from " + 
				"(select rownum R, p.* from " + 
				"(select * from notice where "+kind+" like ? order by num desc) p) " + 
				"where R between ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		st.setInt(2, startRow);
		st.setInt(3, lastRow);
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
	
	//insert
	public int insert(noticeDTO dto) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "insert into notice values(notice_seq.nextval,?,?,?,sysdate,0)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, dto.getTitle());
		st.setString(2, dto.getContents());
		st.setString(3, dto.getWriter());
		
		int result = st.executeUpdate();
		DBConnector.disConnection(con, st);
		
		return result;
	}
}
