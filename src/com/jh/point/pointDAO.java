package com.jh.point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jh.util.DBConnector;

public class pointDAO {

	//gettotalcount 총 갯수
	
	public int getTotalCount(String kind,String search) throws Exception {
		int totalCount;
		Connection con = DBConnector.getConnect();
		String sql = "select count(num) from point where "+kind+" like?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		totalCount = rs.getInt(1);
		
		DBConnector.disConnection(con, st, rs);
		return totalCount;
	}
	
	//selectList 범위지정 출력
	public ArrayList<pointDTO> selectList(String kind, String search, int startRow, int lastRow) throws Exception{
		ArrayList<pointDTO> ar = new ArrayList<pointDTO>();
		
		Connection con = DBConnector.getConnect();
		String sql = "select * from " + 
				"(select rownum R, p.* from " + 
				"(select * from point where "+kind+" like ? order by num desc) p) " + 
				"where R between ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search+"%");
		st.setInt(2, startRow);
		st.setInt(3, lastRow);
		
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			pointDTO dto = new pointDTO();
			dto.setNum(rs.getInt("num"));
			dto.setName(rs.getString("name"));
			dto.setKor(rs.getInt("kor"));
			dto.setEng(rs.getInt("eng"));
			dto.setMath(rs.getInt("math"));
			dto.setTotal(rs.getInt("total"));
			dto.setAvg(rs.getDouble("avg"));
			ar.add(dto);
		}
		DBConnector.disConnection(con, st, rs);
		return ar;
	}
	
	//selectOne
	public pointDTO selectOne(int num) throws Exception{
		pointDTO dto = new pointDTO();

		Connection con = DBConnector.getConnect();
		
		String sql = "select * from point where num =? ";
		
		//5. 미리 전송
		PreparedStatement st = con.prepareStatement(sql);
		
		//6. ? 세팅
		st.setInt(1, num);
		
		//7. 최종 전송 후 처리
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			dto.setNum(rs.getInt("num"));
			dto.setName(rs.getString("name"));
			dto.setKor(rs.getInt("kor"));
			dto.setEng(rs.getInt("eng"));
			dto.setMath(rs.getInt("math"));
			dto.setTotal(rs.getInt("total"));
			dto.setAvg(rs.getDouble("avg"));
		}
		DBConnector.disConnection(con, st, rs);
		return dto;
	}
	
	//delete
	public int delete(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		
		String sql = "delete point where num=? ";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, num);
		int result = st.executeUpdate();
		
		DBConnector.disConnection(con, st);
		return result;
	}
	//update
	public int update(pointDTO dto) throws Exception{
		Connection con = DBConnector.getConnect();
		//4. SQL문 생성
		String sql = "update point set name=?, kor=?, eng=?, math=?, total=?, avg=? where num=?";

		//5. 미리 전송
		PreparedStatement st = con.prepareStatement(sql);

		//6
		st.setString(1, dto.getName());
		st.setInt(2, dto.getKor());
		st.setInt(3, dto.getEng());
		st.setInt(4, dto.getMath());
		st.setInt(5, dto.getTotal());
		st.setDouble(6, dto.getAvg());
		st.setInt(7, dto.getNum());

		//7
		int result = st.executeUpdate();
		//8
		DBConnector.disConnection(con, st);
		return result;
	}
	
	
	//insert
	public int insert(pointDTO dto) throws Exception {
		Connection con = DBConnector.getConnect();
		//4. SQL문 생성
		String sql = "insert into point values(point_seq.nextval,?,?,?,?,?,?)";
		
		//5. 미리 전송
		PreparedStatement st = con.prepareStatement(sql);
		
		//6. ? 세팅
		st.setString(1, dto.getName());		
		st.setInt(2, dto.getKor());
		st.setInt(3, dto.getEng());
		st.setInt(4, dto.getMath());
		st.setInt(5, dto.getTotal());
		st.setDouble(6, dto.getAvg());
		
		//7. 최종 전송 후 처리
		int result = st.executeUpdate();
		
		//8. 연결 해제
		DBConnector.disConnection(con, st);
		
		return result;
	}
	
}
