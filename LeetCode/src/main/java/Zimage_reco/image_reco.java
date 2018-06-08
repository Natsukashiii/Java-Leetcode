//package Zimage_reco;
//
//import java.util.HashMap;
//import java.util.Iterator;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import com.baidu.aip.ocr.AipOcr;
//
///**
// * 测试百度云OCR的文字识别功能 <br>
// * 打开百度云AI的官网： <br>
// * https://console.bce.baidu.com/ai/?_=1517288853048#/ai/ocr/overview/index <br>
// */
//public class image_reco
//{
//
//    //设置APP ID/AK/SK
//    public static final String APP_ID = "11362796";
//    public static final String API_KEY = "1D1bipRmVuwMquHErAeXXatj";
//    public static final String SECRET_KEY = "ZUbP9OjUyOTIAij7nwGoyPyCP4UfUeKj";
//
//    public static void main(String[] args)
//    {
//
//        // 初始化一个AipOcr
//        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
//
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//        // 是否定位单字符位置，big：不定位单字符位置，默认值；small：定位单字符位置
//        options.put("recognize_granularity", "big");
//        // 识别语言类型，默认为CHN_ENG。可选值包括：
//        // CHN_ENG：中英文混合；
//        // ENG：英文；
//        // POR：葡萄牙语；
//        // FRE：法语；
//        // GER：德语；
//        // ITA：意大利语；
//        // SPA：西班牙语；
//        // RUS：俄语；
//        // JAP：日语；
//        // KOR：韩语；
//        options.put("language_type", "CHN_ENG");
//        // 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。
//        options.put("detect_direction", "true");
//        // 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
//        options.put("detect_language", "true");
//        // 是否返回文字外接多边形顶点位置，不支持单字位置。默认为false
//        options.put("vertexes_location", "false");
//        // 是否返回识别结果中每一行的置信度
//        options.put("probability", "false");
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        // 调用接口
//        String path = "/Users/natsukashii/Desktop/9.png";
//        JSONObject res = client.accurateGeneral(path, options);
//        JSONArray myJson = res.getJSONArray("words_result");
//        Iterator<Object> iterator = myJson.iterator();
//        while (iterator.hasNext())
//        {
//            Object value = iterator.next();
//            JSONObject obj = new JSONObject(value.toString());
//            System.out.println(obj.get("words"));
//        }
//
//    }
//}
