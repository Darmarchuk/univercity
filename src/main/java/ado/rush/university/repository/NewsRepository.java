package ado.rush.university.repository;

import ado.rush.university.dto.ImageProjection;
import ado.rush.university.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {


}
