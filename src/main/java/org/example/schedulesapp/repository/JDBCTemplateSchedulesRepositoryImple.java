package org.example.schedulesapp.repository;

import com.mysql.cj.protocol.Resultset;
import org.example.schedulesapp.dto.SchedulesRequestDto;
import org.example.schedulesapp.dto.SchedulesResponseDto;
import org.example.schedulesapp.entity.Schedules;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Date;

@Repository
public class JDBCTemplateSchedulesRepositoryImple implements SchedulesRepository {

    private final JdbcTemplate jdbcTemplate;
//    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public JDBCTemplateSchedulesRepositoryImple(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
    }


    //1. 생성
    @Override
    public SchedulesResponseDto saveSchedules(Schedules schedules) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Map은 인터페이스이기에, 구현체를 작성 해 줘야함.
        Map<String, Object> parmas = new HashMap<>();
        parmas.put("date", schedules.getDate());
        parmas.put("title", schedules.getTitle());
        parmas.put("work", schedules.getWork());
        parmas.put("userId", schedules.getUserId());
        parmas.put("passWord", schedules.getPassword());
        parmas.put("createdAt", schedules.getCreateDate());
        parmas.put("updatedAt", schedules.getUpdateDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parmas));

        return new SchedulesResponseDto(key.longValue(), schedules.getTitle(), schedules.getWork(), schedules.getUserId(), schedules.getDate(),schedules.getCreateDate(), schedules.getUpdateDate());
    }


    //2. 전체 조회
    @Override
    public List<SchedulesResponseDto> findAllSchedules(String userId, String updatedAt) {
        //
        if (userId != null && updatedAt != null) {
            return jdbcTemplate.query("select * from schedules where userId = ? order by updatedAt desc", schedulesMapper(), userId);
        } else if (userId != null && updatedAt == null) {
            return jdbcTemplate.query("select * from schedules where userId = ? order by updatedAt desc", schedulesMapper(), userId);
        } else if (userId == null && updatedAt != null) {
            return jdbcTemplate.query("select * from schedules where updatedAt LIKE ? order by updatedAt desc", schedulesMapper(), updatedAt + "%");
        } else {
            return jdbcTemplate.query("select * from schedules order by updatedAt desc", schedulesMapper());
        }

    }


    //3. 단건 조회
    @Override
    public Optional<Schedules> findScheduleById(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id =?", schedulesRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public Schedules findSchedulesByIdOrElseThrow(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id = ?  ", schedulesRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public int updateSchedules(Long id, String work, String userId, String passWord) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        return jdbcTemplate.update("update schedules set work = ?, userId = ?, updatedAt =? where id =? AND passWord = ?", work, userId,dateFormat.format(new Date()), id, passWord);
    }

    @Override
    public int updateWork(Long id, String work,String passWord) {
        return jdbcTemplate.update("update schedules set work = ? where id =? AND passWord = ?", work, id, passWord);
    }

    @Override
    public int updateUserId(Long id, String userId,String passWord) {
        return jdbcTemplate.update("update schedules set userId = ? where id = ? AND passWord = ? ", userId, id, passWord);
    }

    @Override
    public int deleteSchedulesById(Long id, String passWord) {
        return jdbcTemplate.update("delete from schedules where id =? and passWord =?", id, passWord);
    }

    private RowMapper<SchedulesResponseDto> schedulesMapper() {
        return new RowMapper<SchedulesResponseDto>() {
            @Override
            public SchedulesResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new SchedulesResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("work"),
                        rs.getString("userId"),
                        rs.getString("date"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt")
                );
            }
        };
    }

    private RowMapper<Schedules> schedulesRowMapperV2() {
        return new RowMapper<Schedules>() {
            @Override
            public Schedules mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedules(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("work"),
                        rs.getString("userId"),
                        rs.getString("date"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt")
                );
            }
        };
    }


}
