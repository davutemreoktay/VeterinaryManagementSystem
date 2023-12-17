package dev.patika.VeterinaryManagementSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "animal")
@NoArgsConstructor
@AllArgsConstructor
@Builder//
@Getter
@Setter
public class Animal {
    //GenerationType.IDENTITY, otomatik artış (auto-increment) özelliği olan birincil anahtar kullanılmasını ifade eder.
    // Bu strateji, genellikle MySQL veya PostgreSQL gibi bazı veritabanları için uygundur.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "species", nullable = false)
    private String species;

    @Column (name = "breed", nullable = false)
    private String breed;

    @Column (name = "gender", nullable = false)
    private String gender;

    @Column (name = "colour", nullable = false)
    private String colour;

    @Column (name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    //@JoinColumn: Bu annotasyon, @ManyToOne tarafında kullanılır ve birincil anahtar sütunlarının ismini ve diğer özelliklerini belirlemek için kullanılır.
    //Yani, @ManyToOne ilişkisinde, bu annotasyon ile hangi alanın diğer varlık sınıfındaki birincil anahtar sütunu ile eşleştiğini belirtiriz.

    @OneToMany (mappedBy = "animal", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<AppointmentDate> appointmentDates;
    //mappedBy: Bu annotasyon, @OneToMany tarafında kullanılır ve iki varlık sınıfı arasındaki ilişkinin sahibini belirtir.
    //Yani, @OneToMany ilişkisi olan taraf, bu annotasyon ile hangi alanın karşı tarafın anahtarı olduğunu belirtir.
    //Bu, aynı ilişkiyi yöneten iki taraf arasındaki çift yönlü ilişkilerde önemlidir.

    @OneToMany (mappedBy = "animal", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List <Vaccine> vaccines;

    //Hangi yükleme stratejisinin kullanılacağı, uygulamanın ihtiyaçlarına ve performans gereksinimlerine bağlıdır.
    //EAGER yükleme stratejisi, ilişkili verilerin genellikle kullanılacağı durumlarda tercih edilebilir, ancak bu durumda tüm ilişkili verilerin her zaman yüklenmesine neden olabilir.
    //LAZY yükleme stratejisi ise, ilişkili verilerin genellikle kullanılmayacağı durumlarda performans avantajı sağlayabilir, çünkü veri yüklemesi talep üzerine yapılır.
    //Ancak, bu durumda, ilişkili veriye ilk erişildiğinde ek bir SQL sorgusu gerçekleşecektir.

}
