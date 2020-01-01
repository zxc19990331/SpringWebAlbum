package server;

import dao.AlbumDAO;
import dao.statisticsDAO;
import entity.DataResult;
import model.Album;
import model.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsServer {

    public static DataResult getStatistics(String userId){
        DataResult dataResult = new DataResult();

        Statistics statistics = new Statistics(statisticsDAO.getStatisticsAll(userId));

        dataResult.setStatus(0);
        dataResult.setData(statistics);

        return dataResult;
    }

}
