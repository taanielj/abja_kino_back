package ttt.valiit.abja_kino_back.business.movie;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Resource
    private MovieRepository movieRepository;



    public Movie getMovie(Integer movieId) {
        return movieRepository.findById(movieId).get();
    }


}
