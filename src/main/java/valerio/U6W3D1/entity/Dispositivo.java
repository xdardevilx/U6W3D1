package valerio.U6W3D1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue
    private int id;
    private String tipologia;
    private String stato;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;

    public Dispositivo(String tipologia, String stato) {
        this.tipologia = tipologia;
        this.stato = stato;
    }


}
