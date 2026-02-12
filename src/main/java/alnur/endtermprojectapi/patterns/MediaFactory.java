package alnur.endtermprojectapi.patterns;

import alnur.endtermprojectapi.exception.BadRequestException;
import alnur.endtermprojectapi.model.Media;
import alnur.endtermprojectapi.model.Podcast;
import alnur.endtermprojectapi.model.Song;
import alnur.endtermprojectapi.model.*;

public class MediaFactory {

    public static Media create(String type, Integer id, String name, int duration) {
        if (type == null) throw new BadRequestException("type is required");

        String t = type.trim().toUpperCase();

        if ("SONG".equals(t)) {
            return new SongBuilder()
                    .id(id)
                    .name(name)
                    .duration(duration)
                    .build();
        }

        if ("PODCAST".equals(t)) {
            return new PodcastBuilder()
                    .id(id)
                    .name(name)
                    .duration(duration)
                    .build();
        }

        throw new BadRequestException("Unknown media type: " + type);
    }

    public static class SongBuilder {
        private Integer id;
        private String name;
        private Integer duration;

        public SongBuilder id(Integer id) { this.id = id; return this; }
        public SongBuilder name(String name) { this.name = name; return this; }
        public SongBuilder duration(int duration) { this.duration = duration; return this; }

        public Song build() {
            if (name == null || name.isBlank()) throw new BadRequestException("name is required");
            if (duration == null || duration <= 0) throw new BadRequestException("duration must be positive");
            return new Song(id, name, duration);
        }
    }

    public static class PodcastBuilder {
        private Integer id;
        private String name;
        private Integer duration;

        public PodcastBuilder id(Integer id) { this.id = id; return this; }
        public PodcastBuilder name(String name) { this.name = name; return this; }
        public PodcastBuilder duration(int duration) { this.duration = duration; return this; }

        public Podcast build() {
            if (name == null || name.isBlank()) throw new BadRequestException("name is required");
            if (duration == null || duration <= 0) throw new BadRequestException("duration must be positive");
            return new Podcast(id, name, duration);
        }
    }
}
