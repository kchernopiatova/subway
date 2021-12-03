package com.solvd.subway.persistence;

import com.solvd.subway.domain.Subway;
import com.solvd.subway.domain.Train;
import org.apache.ibatis.annotations.Param;

public interface TrainRepository {
    void create(@Param("train") Train train, @Param("subway") Subway subway);

    void delete(Train train);
}
