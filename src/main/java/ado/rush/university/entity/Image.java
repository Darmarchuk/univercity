package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Image {
    @Id
    @SequenceGenerator(name="image_id_seq",
            sequenceName = "image_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="image_id_seq")
    private Long id;

    private String name;

    @Column(name = "user_id",nullable = true)
    private Long userId;

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "news_id",
    referencedColumnName = "id")
    private News news;

}
