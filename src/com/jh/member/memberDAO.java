package com.jh.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jh.util.DBConnector;

public class memberDAO {
	//선생님 중복확인
	public int idCheck(String id) throws Exception{
		int result = 0;
		Connection con = DBConnector.getConnect();
		String sql = "select id from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			result = 1;
		}
		
		DBConnector.disConnection(con, st, rs);
		return result;
	}
	
	//중복확인
	/*
	public memberDTO memberSelect(String id) throws Exception{
		memberDTO m = null;
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			m= new memberDTO();
			m.setId(rs.getString("id"));
			m.setPw(rs.getString("pw"));
			m.setName(rs.getString("name"));
			m.setPhone(rs.getString("phone"));
			m.setEmail(rs.getString("email"));
			m.setAge(rs.getInt("age"));
		}
		DBConnector.disConnection(con, st, rs);
		
		return m;
	}
	*/
	//delete
	public int memberDelete(String id) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		int result = st.executeUpdate();
		DBConnector.disConnection(con, st);
		return result;
	}
	
	//update
	public int memberUpdate(memberDTO dto) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "update member set pw=?, name=?, age=?, phone=?, email=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, dto.getPw());
		st.setString(2, dto.getName());
		st.setInt(3, dto.getAge());
		st.setString(4, dto.getPhone());
		st.setString(5, dto.getEmail());
		st.setString(6, dto.getId());
		
		int result = st.executeUpdate();
		DBConnector.disConnection(con, st);
		return result;
	}
	
	
	
	//login
	public memberDTO memberLogin(memberDTO dto) throws Exception{
		memberDTO m = null;
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, dto.getId());
		st.setString(2, dto.getPw());
		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			m= new memberDTO();
			m.setId(rs.getString("id"));
			m.setPw(rs.getString("pw"));
			m.setName(rs.getString("name"));
			m.setPhone(rs.getString("phone"));
			m.setEmail(rs.getString("email"));
			m.setAge(rs.getInt("age"));
		}
		DBConnector.disConnection(con, st, rs);
		
		return m;
	}
	
	
	//join
	public int memberJoin(memberDTO dto) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "insert into member values(?,?,?,?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setString(1, dto.getId());
		st.setString(2, dto.getPw());
		st.setString(3, dto.getName());
		st.setInt(4, dto.getAge());
		st.setString(5, dto.getPhone());
		st.setString(6, dto.getEmail());
		
		int result = st.executeUpdate();
		DBConnector.disConnection(con, st);
		return result;
	}
}
