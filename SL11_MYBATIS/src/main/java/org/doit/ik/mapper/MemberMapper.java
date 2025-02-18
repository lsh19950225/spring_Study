package org.doit.ik.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Commit;

public interface MemberMapper { // 인터페이스로 바꿈
	
	public MemberVO getMember(String id) throws ClassNotFoundException, SQLException;
	
	public int insert(MemberVO member) throws ClassNotFoundException, SQLException;
	
} // interface
