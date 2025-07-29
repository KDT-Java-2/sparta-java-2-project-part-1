package com.sparta.commerce_project_01.domain.category.repository;

import com.sparta.commerce_project_01.domain.category.entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor

// 실무에서 사용하지 않지만.. 내용 학습용 코드
public class CategoryJdbcRepository {

  private final DataSource dataSource;

  public Category save(Category category) throws SQLException {
    String sql = "INSERT INTO categories (name) VALUES (?);";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = getConnection();

      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, category.getName());
      preparedStatement.executeUpdate();

      return category;
    } catch (SQLException error) {
      log.error("insert : ", error);
      throw error;

    } finally {
      close(connection, preparedStatement, null);
    }
  }

  public Category findById(Connection connection, Long categoryId) throws SQLException {
    String sql = "SELECT * FROM categories WHERE id = ?";
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      pstmt = connection.prepareStatement(sql);
      pstmt.setLong(1, categoryId);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        return Category.builder()
            .name(rs.getString("name"))
            .build();

      } else {
        throw new NoSuchElementException("categoryId : " + categoryId);
      }
    } catch (SQLException error) {
      log.error("findById : ", error);
      throw error;

    } finally {
      JdbcUtils.closeResultSet(rs);
      JdbcUtils.closeStatement(pstmt);
    }
  }

  public void update(Connection connection, Long categoryId, String name) throws SQLException {
    String sql = "UPDATE categories SET name=? WHERE id = ?";

    PreparedStatement pstmt = null;
    try {
      pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, name);
      pstmt.setLong(2, categoryId);
      pstmt.executeUpdate();

    } catch (SQLException error) {
      log.error("update : ", error);
      throw error;

    } finally {
      JdbcUtils.closeStatement(pstmt);
    }
  }

  private void close(Connection connection, Statement statement, ResultSet resultSet) {
    JdbcUtils.closeResultSet(resultSet);
    JdbcUtils.closeStatement(statement);
    JdbcUtils.closeConnection(connection);
  }

  private Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

}
