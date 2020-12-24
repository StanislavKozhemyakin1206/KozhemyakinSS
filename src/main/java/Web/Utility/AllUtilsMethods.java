package Web.Utility;

import java.nio.file.*;
import java.text.*;
import java.util.*;

import net.bytebuddy.utility.*;

import static java.lang.Math.abs;


public class AllUtilsMethods {

    public static final String REPORT_FORMAT = "yyyyMMdd";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIMESTAMP_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final String TIMESTAMP_MILLIS_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    private static final Random rnd = new Random(System.nanoTime());

    public String genrateRandomName(int length) {
        String randomName = "TEST " + new RandomString().make(length);
        return randomName;
    }

    public String genrateString(int length) {
        String randomName = new RandomString().make(length);
        return randomName;
    }

    public String uniqueIp() {
        return getRandomNumberInRange(1, 255) + "." + getRandomNumberInRange(1, 255) + "." + getRandomNumberInRange(1, 255) + "." + getRandomNumberInRange(1, 255);
    }

    public String randomPhone() {
        return "+37" + uniqueNumbers(9);
    }

    public String uniqueNumbers(int size) {

        StringBuilder sb = new StringBuilder(size);

        while (sb.length() < size) {
            long l = abs(rnd.nextLong());
            sb.append(Long.toString(l));
        }

        String s = sb.toString();
        return s.substring(0, size);
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String currentDate() {
        Date now = Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        return format(c.getTime(), DATE_FORMAT);
    }

    public static String currentDatePlusDay(int day) {
        Date now = Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, +day);
        return format(c.getTime(), DATE_FORMAT);
    }

    public static String currentDateMinusDay(int day) {
        Date now = Calendar.getInstance().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, -day);
        return format(c.getTime(), DATE_FORMAT);
    }

    public static String datePlusNDays(String dateStr, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(dateStr, DATE_FORMAT));
        c.add(Calendar.DAY_OF_MONTH, n);
        return format(c.getTime(), DATE_FORMAT);
    }


    public static String dateMinusNDays(String date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime((parseDate(date, DATE_FORMAT)));
        c.add(Calendar.DAY_OF_MONTH, -1 * n);
        return format(c.getTime(), DATE_FORMAT);
    }

    public static Date parseDate(String date, String format) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static void renameFileUseJavaNewIO(String srcFilePath, String destFilePath) {
        try {
            if (srcFilePath != null && srcFilePath.trim().length() > 0 && destFilePath != null && destFilePath.trim().length() > 0) {
                /* Create the source Path instance. */
                Path srcPathObj = Paths.get(srcFilePath).toAbsolutePath();

                /* Create the target Path instance. */
                Path destPathObj = Paths.get(destFilePath + "\\allure-results").toAbsolutePath();

                /* Rename source to target, replace it if target exist. */
                Path targetPathObj = Files.move(srcPathObj, destPathObj, StandardCopyOption.COPY_ATTRIBUTES);

                System.out.println("Use java new io to moveFiles success from " + srcFilePath + " to " + destFilePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
