package time;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeX implements Serializable {
    private Date mDate;

    public TimeX() {
        mDate = new Date(System.currentTimeMillis());
    }

    public TimeX(String milliString) {
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            mDate = sdFormatter.parse(milliString);
            if (mDate == null) {
                throw new IllegalArgumentException("TimeX contruction illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("TimeX contruction illegal argument");
        }
    }

    public TimeX(String milliString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            mDate = simpleDateFormat.parse(milliString);
            if (mDate == null) {
                throw new IllegalArgumentException("TimeX contruction illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("TimeX contruction illegal argument");
        }
    }

    public void update() {
        mDate = new Date(System.currentTimeMillis());
    }

    public void update(String milliString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            mDate = simpleDateFormat.parse(milliString);
            if (mDate == null) {
                throw new IllegalArgumentException("TimeX contruction illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("TimeX contruction illegal argument");
        }
    }

    public void update(String milliString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            mDate = simpleDateFormat.parse(milliString);
            if (mDate == null) {
                throw new IllegalArgumentException("TimeX update illegal argument");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("TimeX update illegal argument");
        }
    }

    public String format() {
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdFormatter.format(mDate);
    }

    public String format(String format) {
        SimpleDateFormat sdFormatter = new SimpleDateFormat(format);
        return sdFormatter.format(mDate);
    }

    public long timestamp() {
        return mDate.getTime();
    }

    /**
     * get millisecond of TimeX.
     * @return from 0 to 59.
     */
    public int getMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * get second of TimeX.
     * @return from 0 to 59.
     */
    public int getSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * get minute of TimeX.
     * @return from 0 to 59.
     */
    public int getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * get hour of TimeX.
     * @return from 0 to 23.
     */
    public int getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * compare TimeX to compareTo.
     * If earlier, return negative number.
     * If equal, return zero.
     * if later, return positive number.
     * @return
     */
    public long compare(TimeX compareTo) {
        return mDate.getTime() - compareTo.mDate.getTime();
    }

    /**
     * get different milli seconds with diffTimeX.
     * if mDate is earlier, return negative number.
     * if mDate is after, return positive number.
     * @return
     */
    public long getDiffMillisWith(TimeX diffTimeX) {
        return (mDate.getTime() - diffTimeX.mDate.getTime());
    }

    /**
     * get different seconds with diffTimeX.
     * if mDate is earlier, return negative number.
     * if mDate is after, return positive number.
     * @return
     */
    public long getDiffSecondsWith(TimeX diffTimeX) {
        return (mDate.getTime() - diffTimeX.mDate.getTime()) / 1000;
    }

    /**
     * get different minutes with diffTimeX.
     * if mDate is earlier, return negative number.
     * if mDate is after, return positive number.
     * @return
     */
    public long getDiffMinutesWith(TimeX diffTimeX) {
        return (mDate.getTime() - diffTimeX.mDate.getTime()) / (1000 * 60);
    }

    /**
     * get different hours with diffTimeX.
     * if mDate is earlier, return negative number.
     * if mDate is after, return positive number.
     * @return
     */
    public long getDiffHoursWith(TimeX diffTimeX) {
        return (mDate.getTime() - diffTimeX.mDate.getTime()) / (1000 * 60 * 60);
    }

    /**
     * get different days with diffTimeX.
     * if mDate is earlier, return negative number.
     * if mDate is after, return positive number.
     * @return
     */
    public long getDiffDaysWith(TimeX diffTimeX) {
        return (mDate.getTime() - diffTimeX.mDate.getTime()) / (1000 * 60 * 60 * 24);
    }


    public TimeX getTimeXAfterMillis(int millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.MILLISECOND, millis);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXAfterSeconds(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.SECOND, seconds);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXAfterMinutes(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.MINUTE, minutes);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXAfterHours(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.HOUR, hours);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXAfterDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.DATE, days);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }


    public TimeX getTimeXBeforeMillis(int millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.roll(Calendar.MILLISECOND, -1 * millis);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();

        return afterTimeX;
    }

    public TimeX getTimeXBeforeSeconds(int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.SECOND, -1 * seconds);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXBeforeMinutes(int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.MINUTE, -1 * minutes);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXBeforeHours(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.HOUR, -1 * hours);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }

    public TimeX getTimeXBeforeDays(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        calendar.add(Calendar.DATE, -1 * days);

        TimeX afterTimeX = new TimeX();
        afterTimeX.mDate = calendar.getTime();
        return afterTimeX;
    }
}
