package com.lpk.energy.controller;

import com.lpk.energy.ClassDo;
import com.lpk.energy.TimeTableLoad;
import com.lpk.energy.TimeTableMongoRepository;
import com.lpk.energy.room.RoomDo;
import com.lpk.energy.room.RoomMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

/**
 * Created by HeeJoongKim on 2017-04-07.
 */
@Controller
public class WebController
{

    @Autowired
    TimeTableMongoRepository timeTableMongoRepository;

    @Autowired
    RoomMongoRepository roomMongoRepository;

    @RequestMapping(value="/")
    public String main(){
        return "main";
    }
    @RequestMapping(value="/main")
    public String mainframe(){
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
        List<RoomDo> rooms = roomMongoRepository.findByBuilding("18");



        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_WEEK)-3;
        String today = week[num];
        System.out.println("test"+boards.get(0).getName());
        System.out.println(today);

        for (int i=0;i<boards.size();) { //i<boards.size()
            int j = boards.get(i).getRoom().indexOf(today);
            if(j != -1) {
                boards.get(i).setTest(boards.get(i).getRoom().charAt(j+1));
                boards.get(i).setTest(boards.get(i).getRoom().charAt(j+2));
                i++;

                for(RoomDo roomDo: rooms) {
                    if(roomDo.getRoom().equals(boards.get(i-1).getProfessor())) {
                        roomDo.setTime(boards.get(i-1));
                    }
                }
            }
            else {
                boards.remove(i);
            }
        }

//        for (RoomDo roomDo:rooms) {
//
//            System.out.println(roomDo.get);
//
//        }

        model.addAttribute("boards", boards);
        model.addAttribute("rooms", rooms);
        return "tables";
    }

    @RequestMapping(value="/calendar")
    public String calendar(){
        return "calendar";
    }
}
