package alnur.endtermprojectapi.repository;

import alnur.endtermprojectapi.exception.DatabaseOperationException;
import alnur.endtermprojectapi.exception.ResourceNotFoundException;
import alnur.endtermprojectapi.model.Media;
import alnur.endtermprojectapi.patterns.MediaFactory;
import alnur.endtermprojectapi.utils.DatabaseConfig;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MediaRepository {

    private final DatabaseConfig db = DatabaseConfig.getInstance();

    public Media create(Media media) {
        String sql = "INSERT INTO media(name, type, duration) VALUES (?, ?, ?)";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, media.getName());
            ps.setString(2, media.getType());
            ps.setInt(3, media.getDuration());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    media.setId(keys.getInt(1));
                }
            }

            return media;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Create failed", e);
        }
    }

    public List<Media> getAll() {
        List<Media> list = new ArrayList<>();
        String sql = "SELECT * FROM media WHERE is_deleted = 0";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(fromRow(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Read all failed", e);
        }
    }

    public Optional<Media> getById(int id) {
        String sql = "SELECT * FROM media WHERE id = ? AND is_deleted = 0";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(fromRow(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Read by id failed", e);
        }
    }

    public Media update(int id, Media media) {
        String sql = "UPDATE media SET name = ?, type = ?, duration = ? WHERE id = ? AND is_deleted = 0";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, media.getName());
            ps.setString(2, media.getType());
            ps.setInt(3, media.getDuration());
            ps.setInt(4, id);

            int updated = ps.executeUpdate();
            if (updated == 0) throw new ResourceNotFoundException("Media not found");

            media.setId(id);
            return media;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Update failed", e);
        }
    }

    public void delete(int id) {
        String sql = "UPDATE media SET is_deleted = 1 WHERE id = ?";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            int deleted = ps.executeUpdate();
            if (deleted == 0) throw new ResourceNotFoundException("Media not found: " + id);

        } catch (SQLException e) {
            throw new DatabaseOperationException("Delete failed", e);
        }
    }

    private Media fromRow(ResultSet rs) throws SQLException {
        return MediaFactory.create(
                rs.getString("type"),
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("duration")
        );
    }
}