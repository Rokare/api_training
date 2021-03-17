/* (C)2021 */
package fr.esiea.ex4A.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @NonNull @NotEmpty @Setter @Getter private String userName;
    @NonNull @NotEmpty @Setter @Getter private int userAge;
    @NonNull @NotEmpty @Setter @Getter private int userCount;
}
