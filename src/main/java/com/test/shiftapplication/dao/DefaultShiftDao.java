package com.test.shiftapplication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.test.shiftapplication.model.MonthlyShiftModel;

public interface DefaultShiftDao extends CrudRepository<MonthlyShiftModel, Long> {

	Iterable<MonthlyShiftModel> findByMonthAndYear(@Param("month") String month, @Param("year") String year);

	Iterable<MonthlyShiftModel> findByMonthAndYearAndUserId(@Param("month") String month, @Param("year") String year,
			@Param("userId") int userId);

}
