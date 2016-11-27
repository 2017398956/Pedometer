package nfl.pedometerlibrary;

import android.content.Context;

/**
 * 使用该方法前必须对context进行初始化
 * Created by fuli.niu on 2016/7/19.
 */
public class StepsCountTool {

    public static Context context;

    /**
     * 获得当天的步数，若步数异常重置步数
     * 这里返回String是因为方便往服务器提交数据时加密（如：RSA）
     *
     * @return
     */
    public static String getTodaySteps() {
        if (null == context) {
            return "0";
        }
        int result = 0;
        Database db = Database.getInstance(context);
        long today = Util4Pedometer.getToday();
        result = db.getSteps(today) + db.getSensorSteps(today);
        if (0 > result) {
            // 说明数据异常，应该重置当前的步数
            db.updateSteps(today, -db.getSteps(today));
            db.updateSensorSteps(today, 0);
            result = 0;
        }
        return result + "";
    }
}
