package com.example.music;

import com.example.music.service.AlbumService;
import com.example.music.service.ArtistService;
import com.example.music.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class mainController {
    private final TitleService titleService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    @GetMapping("/")
    public void main() throws IOException, InterruptedException {
//        albumService.DetailAlbum("82184531");

        artistService.ArtistDetail("14945854");


    }
}
