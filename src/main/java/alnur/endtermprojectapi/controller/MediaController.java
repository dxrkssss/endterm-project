package alnur.endtermprojectapi.controller;

import alnur.endtermprojectapi.dto.MediaCreateRequest;
import alnur.endtermprojectapi.dto.MediaResponse;
import alnur.endtermprojectapi.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService service;

    public MediaController(MediaService service) {
        this.service = service;
    }

    @GetMapping
    public List<MediaResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MediaResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MediaResponse create(@RequestBody MediaCreateRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    public MediaResponse update(@PathVariable int id, @RequestBody MediaCreateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
