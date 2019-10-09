package com.example.jira;

import com.example.jira.api.report.Report;
import com.example.jira.config.ApplicationProperties;
import com.example.jira.service.BoardService;
import com.example.jira.service.CategoryService;
import com.example.jira.service.ProjectService;
import com.example.jira.service.SprintService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({ApplicationProperties.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

/*
@Component
class InitData implements CommandLineRunner {
	private final WebClient webClient ;
	private final String API_BASE_URL = "http://jira.tools.orange-sonatel.com" ;
	private final String API_MIME_TYPE = "application/json" ;
	@Autowired
	private ProjectDao ProjectDao;
	@Autowired
	private BoardDao  boardDao ;
	private final SprintDao sprintDao ;
	private  final ProjectService projectService ;
	InitData(SprintDao sprintDao, ProjectService projectService) {
		this.sprintDao = sprintDao;
		this.projectService = projectService;
		this.webClient = WebClient.builder()
						.baseUrl(API_BASE_URL)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
				.defaultHeaders(header -> header.set(HttpHeaders.AUTHORIZATION, "Basic c3RnX2VwdF9kczplcHQyMDE5"))
						.build();
	}

	@Override
	public void run(String... args) throws Exception {

		List<Project> projects = new ArrayList<>();
		// GETTING ALL PROJECTS
	//	WebClient client = (new Client()).getClient();
		projectService.getAllProject()
				.map(project -> ProjectDao.saveAndFlush(project))
				.flatMap(project -> projectService.getAllBoard(project.getId()))
				.subscribe(project -> System.out.println("BD POPULATED"));

		/*
		// SAVING THEM
		for (Project project : projects) {
			ProjectDao.save(project);
		}


		System.out.println("ALL PROJECTS SAVED IN THE DATABASE");
		//System.out.println(projects);
*/


/*


		// GETTING THEIR BOARDS
		List<Project> projects = ProjectDao.findAll();
		for(Project project : projects){
			List<Board> boardList = project.getAllBoard();
			for (Board board : boardList) {
				boardDao.save(board);
				//BOARD SPRINT  LIST
				List<SprintProcess> boardSprintList = board.getAllSprint();
				for(SprintProcess sprint :boardSprintList ){
					sprintDao.save(sprint.getSprint());
				}
			}
		}


/*
		System.out.println("\"ALL BOARDS SAVED IN THE DATABASE\")");

		// Getting Sprint of the first board of the project
		System.out.println("BOARD SPRINT  LIST:  ");
		List<Sprint> boardSprintList = boardList.get(0).getAllSprint();
		System.out.println(boardSprintList.size() +" boardSprintList :  ");
		int count=-1;
		for (Sprint temp : boardSprintList) {
			count++;
			System.out.println("COUNT : "+count+"\n"+temp);
		}


		 */
		/*
		Project aProject = new Project("10002","EDPS","Exemple de projet Scrum");
		aProject = ProjectDao.save(aProject);
        *
	}
}
*/

@Component
class InitData implements CommandLineRunner {

    private final CategoryService categoryService;
    private final ProjectService projectsService;
    private final BoardService boardService;
    private final SprintService sprintService;

    InitData(CategoryService categoryService, ProjectService projectsService, BoardService boardService, SprintService sprintService) {
        this.categoryService = categoryService;
        this.projectsService = projectsService;
        this.boardService = boardService;
        this.sprintService = sprintService;
    }

    @Override
    public void run(String... args) {
        categoryService
                .getAllCategories()
                .flatMap(categoryService::save)
                .flatMap(category -> projectsService.allProjects(category.getId())
                    .flatMap(project -> projectsService.save(project.toBuilder().category(category).build())))
                        .flatMap(project -> boardService.allBoardByProjects(project.getId())
                            .flatMap(board -> boardService.save(board.toBuilder().project(project).build()))
                            .flatMap(board -> sprintService.getAllSprint(board.getId())
                                .flatMap(sprint -> {
                                    Mono <Report> report = sprintService.getReport(sprint.getOriginBoardId(), sprint.getId());
                                    report.map(theReport -> sprint.setReport(theReport)).subscribe(theSprint ->  sprintService.save(theSprint.toBuilder().board(board).build()));
                                    return report;
                                })
                        )
                )
                .then(Mono.just(true))
                .subscribe(System.out::println);

        /*
        projectsService
                .allProjects()
                .flatMap(projectsService::save)
                .flatMap(project -> boardService.allBoardByProjects(project.getId())
                        .flatMap(board -> boardService.save(board.toBuilder().project(project).build()))
                        .flatMap(board -> sprintService.getAllSprint(board.getId())
                                .flatMap(sprint -> {
                                    Mono <Report> report = sprintService.getReport(sprint.getOriginBoardId(), sprint.getId());
                                    report.map(theReport -> sprint.setReport(theReport)).subscribe(theSprint ->  sprintService.save(theSprint.toBuilder().board(board).build()));
                                    return report;
                                })
                        )
                )
                .then(Mono.just(true))
                .subscribe(System.out::println);

         */
    }

}
