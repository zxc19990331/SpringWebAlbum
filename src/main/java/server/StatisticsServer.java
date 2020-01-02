package server;

import dao.StatisticsDAO;
import entity.DataResult;
import model.Statistics;

public class StatisticsServer {

    public static DataResult getStatistics(String userId){
        DataResult dataResult = new DataResult();

        Statistics statistics = new Statistics(StatisticsDAO.getStatisticsAll(userId));

        dataResult.setStatus(0);
        dataResult.setData(statistics);

        return dataResult;
    }

}
