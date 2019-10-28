package com.example.jira.web.controller;

import com.example.jira.domain.Sprint;
import com.example.jira.repository.SprintRepository;
import com.example.jira.web.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Api(description = "API pour les opérations CRUD sur les Sprints.")

@RestController
public class SprintController {
    @Autowired
    private SprintRepository sprintRepository;

    @ApiOperation(value = "This get a sprint by his id (if it exists)")
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

    @ApiOperation(value = "This get all sprints of a specific board by board_id")
    @GetMapping(value = "/sprint/board/{boardId}")
    public Page<Sprint> getProjectBoardSprints(
            @PathVariable Integer boardId,
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "startDate", direction = Sort.Direction.DESC),
                    // @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            }) Pageable pageable,
            @RequestParam(value ="state", defaultValue = "foo") final String state //for filtering purpose
    ) {
        System.out.println(pageable);
        //System.out.println(state);
        if (state.equals("foo"))
            return sprintRepository.findByBoardId(boardId, pageable);
        else
            return sprintRepository.findByBoardIdAndState(boardId, state,pageable);

        //if(sprintList==null ||sprintList.size()==0) throw new ResourceNotFoundException("les Sprint du tableau", boardId);
    }
}

