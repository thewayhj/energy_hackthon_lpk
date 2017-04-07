package com.lpk.energy.controller;

import com.lpk.energy.ClassDo;
import com.lpk.energy.TimeTableLoad;
import com.lpk.energy.TimeTableMongoRepository;
import com.lpk.energy.weather.weatherDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by HeeJoongKim on 2017-04-07.
 */
@Controller
public class WebController
{

    @Autowired
    TimeTableMongoRepository timeTableMongoRepository;

    @RequestMapping(value="/")
    public String main(){
        return "main";
    }
    @RequestMapping(value="/main")
    public String mainframe(Model model)
    {
        URL url =null;
        URLConnection conn=null;
        List<weatherDo> weatherList = new ArrayList();
        try {
            url =new URL("http://web.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162069500");
            conn=url.openConnection();

            DocumentBuilderFactory f= DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            Document doc = b.parse(conn.getInputStream());

            doc.getDocumentElement().normalize();

            NodeList data = doc.getElementsByTagName("data");
            for(int i=0;i<data.getLength();i++) {
                weatherDo weatherdo = new weatherDo();
                Element e = (Element) data.item(i);
                if(e.getNodeType() != Node.ELEMENT_NODE)
                    continue;

                String hour = e.getElementsByTagName("hour").item(0).getTextContent(); //트리구조이기떄문에 첫번째데이터 item(0)을 해줘야함. 의 데이터를 꺼내준다.
                String temp = e.getElementsByTagName("temp").item(0).getTextContent();
                String wfEn = e.getElementsByTagName("wfEn").item(0).getTextContent();
                weatherdo.setHour(Integer.parseInt(hour));
                weatherdo.setTemp(Double.parseDouble(temp));
                weatherdo.setWfen(wfEn);

                weatherList.add(weatherdo);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        }

        model.addAttribute("weather",weatherList);
        return "main";
    }

    @RequestMapping(value="/flot")
    public String flot(){
        return "flot";
    }


    @RequestMapping(value="/test1234")
    public String test(){
        TimeTableLoad timeTableLoad = new TimeTableLoad();
        timeTableMongoRepository.save(timeTableLoad.send());
        return "main";
    }

    @RequestMapping(value="/morris")
    public String morris(){
        return "morris";
    }
    @RequestMapping(value="/tables", method = RequestMethod.GET)
    public String tables(Model model, @RequestParam(required = false) String room){
        List<ClassDo> boards = timeTableMongoRepository.findAll();
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK)-3;
        String today = week[num];
        System.out.println("test"+boards.get(0).getName());
        System.out.println(today);
        Collections.sort(boards, new Comparator<ClassDo>() {
            @Override
            public int compare(ClassDo o1, ClassDo o2) {
                return o1.getRoom().compareTo(o2.getRoom());
            }

        });

        for (int i=0;i<boards.size();i++) {
            if (!boards.get(i).getRoom().contains(today) || boards.get(i).getProfessor().equals("()?")) {
                boards.remove(i);

            }
            else {
                StringTokenizer str = new StringTokenizer(boards.get(i).getRoom(), today);

                int count = str.countTokens();
                System.out.println("파싱할 문자열의 수는 총" + count + " 개");

                while (str.hasMoreTokens()) { //아직 파싱할 토큰이 더 있는지 여부를 확인한다 System.out.println(str.nextToken()); //파싱해서 구한 다음토큰을 반환한다. }
                    switch (str.nextToken())
                    {
                        case "03":
                            boards.get(i).setStarttime("10:00");
                            break;
                        case "04":
                            boards.get(i).setEndtime("11:30");
                            break;
                        case "07":
                            boards.get(i).setStarttime("13:00");
                            break;
                        case "08":
                            boards.get(i).setEndtime("14:45");
                            break;
                        case "09":
                            boards.get(i).setStarttime("15:00");
                            break;
                        case "10":
                            boards.get(i).setEndtime("16:45");
                            break;
                        case "11":
                            boards.get(i).setStarttime("17:00");
                            break;
                        case "12":
                            boards.get(i).setEndtime("18:45");
                            break;
                        case "13":
                            boards.get(i).setStarttime("19:00");
                            break;
                        case "14":
                            boards.get(i).setEndtime("20:45");
                            break;
                    }

                }
            }


        }


        model.addAttribute("boards",boards);
        return "tables";
    }

    @RequestMapping(value="/calendar")
    public String calendar(){
        return "calendar";
    }
}
