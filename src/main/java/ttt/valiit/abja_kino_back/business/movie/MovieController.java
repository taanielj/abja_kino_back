package ttt.valiit.abja_kino_back.business.movie;


import org.springframework.web.bind.annotation.*;

@RequestMapping("/movie")
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable("id") Integer id) {
        return movieService.getMovieDto(id);
    }

    @PostMapping("/add")
    public void addNewMovie(@RequestBody MovieAddRequest request) {
        movieService.addNewMovie(request);
    }

    @GetMapping("/all-ids")
    public Integer[] getAllMovieIds() {
        return movieService.getAllMovieIds();
    }

}
