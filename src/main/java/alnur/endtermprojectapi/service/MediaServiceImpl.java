package alnur.endtermprojectapi.service;

import alnur.endtermprojectapi.dto.MediaCreateRequest;
import alnur.endtermprojectapi.dto.MediaResponse;
import alnur.endtermprojectapi.exception.ResourceNotFoundException;
import alnur.endtermprojectapi.model.Media;
import alnur.endtermprojectapi.patterns.MediaFactory;
import alnur.endtermprojectapi.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository repository;

    public MediaServiceImpl(MediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public MediaResponse create(MediaCreateRequest req) {
        Media media = MediaFactory.create(req.getType(), null, req.getName(), req.getDuration());
        Media saved = repository.create(media);
        return toResponse(saved);
    }

    @Override
    public MediaResponse getById(int id) {
        Media media = repository.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Media not found: " + id));
        return toResponse(media);
    }

    @Override
    public List<MediaResponse> getAll() {
        return repository.getAll().stream().map(this::toResponse).toList();
    }

    @Override
    public MediaResponse update(int id, MediaCreateRequest req) {
        Media media = MediaFactory.create(req.getType(), id, req.getName(), req.getDuration());
        Media updated = repository.update(id, media);
        return toResponse(updated);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    private MediaResponse toResponse(Media m) {
        return new MediaResponse(m.getId(), m.getName(), m.getDuration(), m.getType());
    }
}
