package ProXX.timeCountUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTaskUtil {


    private final static String DATE_FORMAT = "yyyyMMddHHmmssSSS";


    /**
     * 计算当前时间加上 给定时间 后的系统时间
     * @param time
     */
    public static void caculateTime(int time) {

    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 判断时间大小
     * @param time1
     * @param time2
     */
    public static void compareTime(String time1, String time2) {

    }

//    /**
//     * 判断time1与time2的大小；如果time1大于time2,则返回true;否则返回false;
//     *
//     * @param time1
//     * @param time2
//     * @param pattern
//     *            time1与time2均符合pattern模式
//     * @return
//     * @throws ParseException
//     */
//    public static boolean isAfter(String time1, String time2, String pattern) throws ParseException {
//        Date date1 = TimeUtil.string2Date(time1, pattern);
//        Date date2 = TimeUtil.string2Date(time2, pattern);
//        long tim1 = date1.getTime();
//        long tim2 = date2.getTime();
//
//        return tim1 > tim2;
//    }

    /**
     * 计算当前时间到下offset个整点的时间
     *
     * @param offset
     *            小时个数，比如计算当前时间到下一个整点的时间，传值为1；到第二个整点，则传值为2；依次类推
     * @return
     */
    public static long nextHourDelay(int offset) {
        Calendar calendar = Calendar.getInstance();
        long currTime = calendar.getTimeInMillis();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, hour + offset);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long nextHourTime = calendar.getTimeInMillis();

        return nextHourTime - currTime;
    }

}
