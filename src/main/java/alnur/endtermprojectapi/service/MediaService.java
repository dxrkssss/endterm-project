package alnur.endtermprojectapi.service;

import alnur.endtermprojectapi.dto.MediaCreateRequest;
import alnur.endtermprojectapi.dto.MediaResponse;

import java.util.List;

public interface MediaService {
    MediaResponse create(MediaCreateRequest req);
    MediaResponse getById(int id);
    List<MediaResponse> getAll();
    MediaResponse update(int id, MediaCreateRequest req);
    void delete(int id);
}