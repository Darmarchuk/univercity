package ado.rush.university.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @SequenceGenerator(name = "news_id_seq",
            sequenceName = "news_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    private Long id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime dateFrom;
    @OneToMany(mappedBy = "news", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "news",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<NewsRole> newsRole;

}
