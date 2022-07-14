package kopo.poly.controller;


import kopo.poly.dto.ChatDTO;
import kopo.poly.service.IChatService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Resource(name = "ChatService")
    private IChatService chatService;

    //모든페이지를 메핑하는 함수
    //메인페이지
    @GetMapping(value = "/voice")
    public String www() {
        log.info(getClass().getName() + "Start SearchList ");

        log.info(getClass().getName() + "End SearchList ");
        return "/voicePg";
    }

    //메인페이지
    @GetMapping(value = "home")
    public String Home(ModelMap model)throws Exception {
        log.info(getClass().getName() + "homepage Start get dust ");

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=AeTOXdBlqhuatKirOyh3dBmnZk%2FwL%2Fd%2Bm5P%2FabLeUy1ejkKi3u6V0Ntqfd5Og8XrG57w0rgDE24UMf5TVHYRIQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*xml 또는 json*/
        //urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수(조회 날짜로 검색 시 사용 안함)*/
        //urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호(조회 날짜로 검색 시 사용 안함)*/
        urlBuilder.append("&" + URLEncoder.encode("searchDate","UTF-8") + "=" + URLEncoder.encode(DateUtil.getDateTime("yyyy-MM-dd"), "UTF-8")); /*통보시간 검색(조회 날짜 입력이 없을 경우 한달동안 예보통보 발령 날짜의 리스트 정보를 확인)*/
        urlBuilder.append("&" + URLEncoder.encode("InformCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*통보코드검색(PM10, PM25, O3)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        log.info("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        log.info("========파싱한데이터========");
        log.info(sb.toString());
        JSONObject result = null;
        result = (JSONObject) new JSONParser().parse(sb.toString());
        //result.get("response");
        JSONObject result1 = (JSONObject) result.get("response");
        JSONObject result2 = (JSONObject) result1.get("body");
        JSONArray result3 = (JSONArray) result2.get("items");

        //초미세먼지
        JSONObject result4 = (JSONObject) result3.get(2);
        String result5 = (String) result4.get("informGrade");
        log.info(String.valueOf(result5));

        String finedust = String.valueOf(result5).substring(5,7);
        log.info("finedust :"+finedust);

        //미세먼지
        JSONObject result6 = (JSONObject) result3.get(3);
        String result7 = (String) result6.get("informGrade");
        String informOverall = (String) result6.get("informOverall");
        String dust = String.valueOf(result7).substring(5,7);
        log.info("dust :"+dust);
        log.info(String.valueOf(result7));


//        for(int i =0; i<10; i++){
//            JSONObject result4 = (JSONObject) result3.get(i);
//            String result5 = (String) result4.get("informCode");
//            log.info(String.valueOf(result5));
//        }

        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("dust", dust);
        model.addAttribute("finedust", finedust);
        model.addAttribute("informOverall", informOverall);

        log.info(getClass().getName() + "homepage Start get dust End ");
        return "/home";
    }


    @GetMapping(value = "loginPage")
    public String login() {
        log.info(getClass().getName() + "Start SearchList ");

        log.info(getClass().getName() + "End SearchList ");
        return "/login/login";
    }

    //메인페이지
    @GetMapping(value = "changPw")
    public String changPw() {
        log.info(getClass().getName() + "Start SearchList ");

        log.info(getClass().getName() + "End SearchList ");
        return "/login/changPw";
    }

    //메인페이지
    @GetMapping(value = "createUser")
    public String createUser() {
        log.info(getClass().getName() + "Start SearchList ");

        log.info(getClass().getName() + "End SearchList ");
        return "/login/creatUser";
    }

    //메인페이지
    @GetMapping(value = "findPw")
    public String findPW() {
        log.info(getClass().getName() + "Start SearchList ");

        log.info(getClass().getName() + "End SearchList ");
        return "/login/findPw";
    }



    //메인페이지
    @GetMapping(value = "picSearch")
    public String picSearch(HttpSession session) {
        log.info(getClass().getName() + "Start SearchList ");


        log.info(getClass().getName() + "End SearchList ");
        return "/picSearch/picSearch";
    }

    @ResponseBody
    @GetMapping(value = "/dust")
    public  String dust()throws Exception{

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMinuDustFrcstDspth"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=AeTOXdBlqhuatKirOyh3dBmnZk%2FwL%2Fd%2Bm5P%2FabLeUy1ejkKi3u6V0Ntqfd5Og8XrG57w0rgDE24UMf5TVHYRIQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
        //urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수(조회 날짜로 검색 시 사용 안함)*/
        //urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호(조회 날짜로 검색 시 사용 안함)*/
        urlBuilder.append("&" + URLEncoder.encode("searchDate","UTF-8") + "=" + URLEncoder.encode(DateUtil.getDateTime("yyyy-MM-dd"), "UTF-8")); /*통보시간 검색(조회 날짜 입력이 없을 경우 한달동안 예보통보 발령 날짜의 리스트 정보를 확인)*/
        urlBuilder.append("&" + URLEncoder.encode("InformCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*통보코드검색(PM10, PM25, O3)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        log.info("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        log.info("========파싱한데이터========");
        log.info(sb.toString());
        JSONObject result = null;
        result = (JSONObject) new JSONParser().parse(sb.toString());
        //result.get("response");
        JSONObject result1 = (JSONObject) result.get("response");
        JSONObject result2 = (JSONObject) result1.get("body");
        JSONArray result3 = (JSONArray) result2.get("items");

        //미세먼지
        JSONObject result4 = (JSONObject) result3.get(2);
        String result5 = (String) result4.get("informGrade");
        log.info(String.valueOf(result5));

        String dust = String.valueOf(result5).substring(5,7);
        log.info("dust :"+dust);

        //초미세먼지
        JSONObject result6 = (JSONObject) result3.get(3);
        String result7 = (String) result6.get("informGrade");
        String finedust = String.valueOf(result7).substring(5,7);
        log.info("finedust :"+finedust);
        log.info(String.valueOf(result7));


//        for(int i =0; i<10; i++){
//            JSONObject result4 = (JSONObject) result3.get(i);
//            String result5 = (String) result4.get("informCode");
//            log.info(String.valueOf(result5));
//        }


        return result3.toString();

    }


}
