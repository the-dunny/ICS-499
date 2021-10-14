package tech.dunny.gameproject.repos;

import entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointRepository extends JpaRepository<Point, Long> {
}
