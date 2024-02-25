package ma.montaxi.montaxiRestfulApi.entities;

import lombok.*;
import ma.montaxi.montaxiRestfulApi.settings.converters.RoleEnumConverter;
import ma.montaxi.montaxiRestfulApi.settings.security.RoleEnum;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    @Convert(converter = RoleEnumConverter.class)
    private RoleEnum role;
}
