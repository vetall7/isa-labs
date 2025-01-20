import {DetailedGenre} from "@genre/models";
import {Movies} from "@genre/models";

export class GenreWithMovies{
    detailedGenre: DetailedGenre | undefined;
    movies: Movies | undefined;
}
