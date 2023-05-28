package ttt.valiit.abja_kino_back.business.movie;


import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieAdminSummary;


import java.util.List;

@RequestMapping("/api/v1/movie")
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable("id") Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping("/add")
    public void addNewMovie(@RequestBody MovieDto movieDto) {
        movieService.addNewMovie(movieDto);
    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable("id") Integer id, @RequestBody MovieDto movieDto) {
        movieService.updateMovie(id, movieDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/ids")
    public Integer[] getAllMovieIds() {
        return movieService.getAllMovieIds();
    }

    @GetMapping("/admin-summary")
    public List<MovieAdminSummary> getMovieAdminSummary() {
        return movieService.getMovieAdminSummary();
    }

    @GetMapping("/all")
    public List<MovieListDto> getAllMovies() {
        return movieService.getAllMovies();
    }

}
