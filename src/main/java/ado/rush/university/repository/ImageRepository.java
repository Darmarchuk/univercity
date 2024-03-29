package ado.rush.university.repository;

import ado.rush.university.dto.ImageProjection;
import ado.rush.university.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    @Transactional
    ImageProjection findByNewsIdAndId(Long newsId,Long imageId);

}
