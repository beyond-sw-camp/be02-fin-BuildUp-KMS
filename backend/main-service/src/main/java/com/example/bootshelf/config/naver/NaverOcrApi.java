package com.example.bootshelf.config.naver;

import com.example.bootshelf.common.error.ErrorCode;
import com.example.bootshelf.common.error.entityexception.UserException;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NaverOcrApi {

    /**
     * 네이버 ocr api 호출한다
     *
     * @param {string} type 호출 메서드 타입
     * @param {string} filePath 파일 경로
     * @param {string} naver_secretKey 네이버 시크릿키 값
     * @param {string} ext 확장자
     * @returns {List} 추출 text list
     */

    private static String apiKey;
    @Value("${naver.api-key}")
    public void setApiKey(String apiKey) {
        NaverOcrApi.apiKey = apiKey;
    }

    public static String callApi(String type, byte[] imageData, String naver_secretKey, String ext) {
        String apiURL = apiKey;
        String secretKey = naver_secretKey;
        String parseData = null;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod(type);
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", ext);
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.add(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            long start = System.currentTimeMillis();
            writeMultiPart(wr, postParams, imageData, boundary);
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            parseData = jsonparse(response);

        } catch (Exception e) {
            System.out.println(e);
        }
        return parseData;
    }

    private static void writeMultiPart(OutputStream out, String jsonMessage, byte[] imageData, String boundary) throws
            IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("--").append(boundary).append("\r\n");
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
        sb.append(jsonMessage);
        sb.append("\r\n");

        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();

        if (imageData != null && imageData.length > 0) {
            out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            StringBuilder fileString = new StringBuilder();
            fileString
                    .append("Content-Disposition:form-data; name=\"file\"; filename=");
            fileString.append("\"" + "demo.jpg" + "\"\r\n");
            fileString.append("Content-Type: application/octet-stream\r\n\r\n");
            out.write(fileString.toString().getBytes("UTF-8"));
            out.flush();

            out.write(imageData);
            out.write("\r\n".getBytes());

            out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        out.flush();
    }

    /**
     * 데이터 가공
     *
     * @param {StringBuffer} response 응답값
     * @returns {List} result text list
     */
    private static String jsonparse(StringBuffer response) throws ParseException {
        //json 파싱
        JSONParser jp = new JSONParser(); // 여기로 json 형태 데이터가 날라옴
        JSONObject jobj = (JSONObject) jp.parse(response.toString());
        //images 배열 obj 화
        JSONArray JSONArrayPerson = (JSONArray) jobj.get("images");
        JSONObject JSONObjImage = (JSONObject) JSONArrayPerson.get(0);
        JSONArray s = (JSONArray) JSONObjImage.get("fields");
        //
        List<Map<String, Object>> m = JsonUtill.getListMapFromJsonArray(s);
        List<String> result = new ArrayList<>();
        for (Map<String, Object> as : m) {
            result.add((String) as.get("inferText"));
        }

        // 2022- 로 시작하는 리스트 인덱스
        Pattern datePattern = Pattern.compile("\\d{4}-");
        int startIndex = -1;

        // 리스트 순회하여 날짜 형식 패턴과 일치하는 인덱스 찾기
        for (int i = 0; i < result.size(); i++) {
            Matcher matcher = datePattern.matcher(result.get(i));
            if (matcher.find()) {
                if ((i + 1) < result.size() && "V".equals(result.get(i + 1))) {
                    // 다음 인덱스의 값이 "V"와 정확히 일치하면, startIndex를 그 다음으로 설정
                    startIndex = i + 2;
                } else {
                    // "V"와 일치하지 않으면, 현재 패턴이 일치한 부분의 다음 인덱스로 startIndex 설정
                    startIndex = i + 1;
                }
                break;
            }
        }

        // 시작 인덱스 설정 확인
        if (startIndex == -1 || startIndex >= result.size()) {
            return "";
        }

        // 모든 공백과 특수 문자 제거.
        StringBuilder courseNameBuilder = new StringBuilder();
        for (int i = startIndex; i < result.size(); i++) {
            String cleanedText = result.get(i).replaceAll("[^a-zA-Z0-9가-힣]", "");
            courseNameBuilder.append(cleanedText);
        }
        String courseName = courseNameBuilder.toString();

        if(!courseName.isEmpty()) {
            return courseName;
        } else {
            throw new UserException(ErrorCode.OCR_TEXT_NOT_FOUND, String.format("Text cannot be found in the image."));
        }
    }
}
