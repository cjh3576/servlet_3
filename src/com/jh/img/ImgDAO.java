package com.jh.img;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jh.util.DBConnector;

public class ImgDAO {

	//insert
	public int insert(ImgDTO imgDTO, Connection con) throws Exception{
		int result = 0;
		String sql = "insert into img values(point_seq.nextval,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, imgDTO.getId());
		st.setString(2, imgDTO.getOname());
		st.setString(3, imgDTO.getFname());
		
		result = st.executeUpdate();
		st.close();
		return result;
	}
	
	//select
	public ImgDTO selectOne(String id) throws Exception{
		ImgDTO imgDTO = null;
		Connection con = DBConnector.getConnect();
		String sql = "select * from img where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			imgDTO = new ImgDTO();
			imgDTO.setPnum(rs.getInt("pnum"));
			imgDTO.setId(rs.getString("id"));
			imgDTO.setOname(rs.getString("oname"));
			imgDTO.setFname(rs.getString("fname"));
		}
		DBConnector.disConnection(con, st, rs);
		
		return imgDTO;
	}
}
