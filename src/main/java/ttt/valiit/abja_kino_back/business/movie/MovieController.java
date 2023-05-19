package ttt.valiit.abja_kino_back.business.movie;


import org.springframework.web.bind.annotation.*;
import ttt.valiit.abja_kino_back.business.movie.dto.MovieAdminSummary;
import ttt.valiit.abja_kino_back.domain.movie.Movie;

import java.util.List;

@RequestMapping("/movie")
@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable("id") Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping("/add")
    public void addNewMovie(@RequestBody MovieAddRequest request) {
        movieService.addNewMovie(request);
    }

    @GetMapping("/admin-summary")
    public List<MovieAdminSummary> getMovieAdminSummary() {
        return movieService.getMovieAdminSummary();
    }

}
