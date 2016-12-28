package time;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateX implements Serializable {
    private Date mDate;

    public DateX() {
        mDate = new Date(System.currentTimeMillis());
    }

    public DateX(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            mDate = simpleDateFormat.parse(dateString);
            if (mDate == null) {
                throw new IllegalArgumentException("DateX contruction illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("DateX contruction illegal argument");
        }
    }

    public DateX(String dateString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            mDate = simpleDateFormat.parse(dateString);
            if (mDate == null) {
                throw new IllegalArgumentException("DateX contruction illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("DateX contruction illegal argument");
        }
    }

    public void update() {
        mDate = new Date(System.currentTimeMillis());
    }

    public void update(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        try {
            mDate = simpleDateFormat.parse(dateString);
            if (mDate == null) {
                throw new IllegalArgumentException("DateX update illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("DateX update illegal argument");
        }
    }

    public void update(String dateString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            mDate = simpleDateFormat.parse(dateString);
            if (mDate == null) {
                throw new IllegalArgumentException("DateX update illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("DateX update illegal argument");
        }
    }

    public String format() {
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return sdFormatter.format(mDate);
    }

    public String format(String format) {
        SimpleDateFormat sdFormatter = new SimpleDateFormat(format);
        return sdFormatter.format(mDate);
    }

    /**
     * getDay day of month.
     * @return from 1 to 31.
     */
    public int getDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * getWeek return week of year.
     * @return
     */
    public int getWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * get month of year.
     * @return from 0 to 11.
     */
    public int getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.MONTH);
    }

    public int getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * compare DateX to compareTo.
     * If earlier, return negative number.
     * If equal, return zero.
     * if later, return positive number.
     * @return
     */
    public long compare(DateX compareTo) {
        return mDate.getTime() - compareTo.mDate.getTime();
    }

    /**
     * get different days with diffDateX.
     * if mDate is earlier, return negative number.
     * if mDate is after, return positive number.
     * @return
     */
    public long getDiffDaysWith(DateX diffDateX) {
        return (mDate.getTime() - diffDateX.mDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    public DateX getDateAfterDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.DATE, days);

        DateX afterDate = new DateX();
        afterDate.mDate = calendar.getTime();
        return afterDate;
    }

    public DateX getDateBeforeDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.DATE, -1 * days);

        DateX afterDate = new DateX();
        afterDate.mDate = calendar.getTime();
        return afterDate;
    }
}
