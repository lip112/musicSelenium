package com.example.music;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class mainController {
    private final TitleService titleService;
    private final AlbumService albumSearch;

    @GetMapping("/")
    public void main() throws IOException {
        albumSearch.albumSearch();

    }
}
