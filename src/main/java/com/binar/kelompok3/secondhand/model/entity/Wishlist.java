package com.binar.kelompok3.secondhand.model.entity;

import com.binar.kelompok3.secondhand.model.DateModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})
@Table(name = "wishlist", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Wishlist extends DateModel implements Serializable {

    private static final long serialVersionUID = 1865643897L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Products productId;

    public Wishlist(Users userId, Products productId) {
        this.userId = userId;
        this.productId = productId;
    }

}
