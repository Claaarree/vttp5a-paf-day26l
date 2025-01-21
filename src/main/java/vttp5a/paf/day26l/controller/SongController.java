package vttp5a.paf.day26l.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp5a.paf.day26l.model.Song;
import vttp5a.paf.day26l.service.SongService;

@Controller
@RequestMapping
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping()
    public ModelAndView showLanding() {
        ModelAndView mav = new ModelAndView("index");
        List<Integer> yearsList = songService.getYears();
        mav.addObject("years", yearsList);
        
        return mav;
    }

    @PostMapping("/songs")
    public ModelAndView getYear(@RequestParam Integer year) {
        ModelAndView mav = new ModelAndView("redirect:/songs/{year}");
        mav.addObject("year", year);
      
        return mav;
    }

    @GetMapping("/songs/{year}")
    public ModelAndView showResults(@PathVariable Integer year) {
        ModelAndView mav = new ModelAndView("results");
        List<Song> songsList = songService.getSongsByYear(year);
        mav.addObject("year", year);
        mav.addObject("songs", songsList);
       
        return mav;
    }
    
}
