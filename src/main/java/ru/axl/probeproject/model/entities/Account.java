package ru.axl.probeproject.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_ACCOUNT")
    private UUID idAccount;

    @Column(name="NUMBER", length=20, nullable=false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "ID_CURRENCY")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    @Column(name = "RESERVATION_DATE")
    private OffsetDateTime reservationDate;

    @Column(name = "OPENING_DATE")
    private OffsetDateTime openingDate;

    @Column(name = "OPEN_DATE")
    private OffsetDateTime openDate;

    @ManyToOne
    @JoinColumn(name = "ID_ACCOUNT_STATUS")
    private AccountStatus accountStatus;

}
