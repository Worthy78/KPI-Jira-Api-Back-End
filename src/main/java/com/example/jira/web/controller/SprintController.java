package com.example.jira.web.controller;

import com.example.jira.domain.Sprint;
import com.example.jira.repository.SprintRepository;
import com.example.jira.web.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Api( description="API pour es opérations CRUD sur les Sprints.")

@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
public class SprintController {
    @Autowired
    private  SprintRepository sprintRepository;

    public   long countWeekDaysMath (LocalDate start , LocalDate stop ) {
        // Code taken from Answer by Roland.
        // https://stackoverflow.com/a/44942039/642706
        long count = 0;
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek stopW = stop.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between( start , stop );
        final long daysWithoutWeekends = days - 2 * ( ( days + startW.getValue() ) / 7 );

        //adjust for starting and ending on a Sunday:
        count = daysWithoutWeekends + ( startW == DayOfWeek.SUNDAY ? 1 : 0 ) + ( stopW == DayOfWeek.SUNDAY ? 1 : 0 );

        return count;
    }
    public LocalDate formatDate(String newDate){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(newDate, inputFormatter);
        return  date;
    }

    @ApiOperation(value = "Récupère un sprint grâce à son ID à condition que celui-ci soit en présent!")
    @GetMapping(value = "/sprint/{id}")

    public Sprint getSprint(@PathVariable Integer id) {

        Optional<Sprint> sprint = sprintRepository.findById(id);
        Sprint theSprint = sprint.get();
       /*
        LocalDate start = formatDate(theSprint.getStartDate()) ;
        LocalDate stop = formatDate(theSprint.getEndDate());
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
        System.out.println("START : "+outputFormatter.format(start)+ " END : "+outputFormatter.format(stop));
        System.out.println("ICI : "+countWeekDaysMath(start,stop));

        */
       // sprint.get()
        return theSprint;
    }
    @GetMapping(value = "/sprint/board/{boardId}")
    public   List <Sprint> getProjectBoardSprints(@PathVariable Integer boardId) {
        List <Sprint> sprintList = sprintRepository.findByBoardId(boardId);
        if(sprintList==null ||sprintList.size()==0) throw new ResourceNotFoundException("les Sprint du tableau", boardId);
        return sprintList;
    }
}

