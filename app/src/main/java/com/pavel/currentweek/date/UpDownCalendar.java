package com.pavel.currentweek.date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/***
 * Put date and check what is current week
 */
public class UpDownCalendar {
    public final static Map<Integer,Integer> weeksAPI = new HashMap<>();
    public static final String UP = "Верхняя неделя";
    public static final String DOWN = "Нижняя неделя";
    private int diff=0;
    private int dayOfYear;
    public UpDownCalendar(int year, int month, int day) {
        setUpDate(year, month, day);
    }
    public UpDownCalendar(){
        setUpDate(
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
    public void setUpDate(int year, int month, int day){
        Calendar calendar = new GregorianCalendar(year,month,day);
        this.dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        weeksAPIInit();
        diffInit(year);
    }

    /***
     * Method will return whick is currently week depend on input date
     * @return Up or Down statics constants
     */
    public String weekCalc(){
        if (Math.ceil((dayOfYear+diff) / 7.0) % 2 != 0)
            return DOWN;
        return UP;
    }
    private void weeksAPIInit(){
        /*Поскольку у них сделано по угребишному,
         воскр равное 1 якобы первый день недели добавляется отдельно...*/
        for(int i = Calendar.MONDAY,j=0; i <= Calendar.SATURDAY;i++,j++)
            weeksAPI.put(i,j);
        weeksAPI.put(Calendar.SUNDAY,6);
    }
    private void diffInit(int currentYear){
        Calendar calendar = new GregorianCalendar(currentYear, Calendar.JANUARY,1);//начало года
        this.diff = weeksAPI.get(calendar.get(Calendar.DAY_OF_WEEK));
    }
}
