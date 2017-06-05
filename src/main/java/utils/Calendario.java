package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public final class Calendario {
    public static int diasHabilesHasta(Date hasta){
        Date desde = new Date();
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(desde);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(hasta);

        int habiles = 0;

        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(hasta);
            endCal.setTime(desde);
        }

        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++habiles;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return habiles;
    }

    public static Date getFechaMasNdiasHabiles(int dias){
        Calendar calendar = Calendar.getInstance();
        for(int i=0;i<dias;)
        {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            if(calendar.get(Calendar.DAY_OF_WEEK)<=5)
            {
                i++;
            }

        }
        return calendar.getTime();
    }
}
