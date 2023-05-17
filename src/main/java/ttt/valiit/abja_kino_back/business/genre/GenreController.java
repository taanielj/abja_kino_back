package ttt.valiit.abja_kino_back.business.genre;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttt.valiit.abja_kino_back.domain.genre.Genre;

import java.util.List;

@RequestMapping("genre")
@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping("/all")
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

}
