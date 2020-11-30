package com.utilities.monitoring.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Measurement implements Serializable {

    private static final long serialVersionUID = -5423308238066666289L;

    public Measurement(User user, UtilityType type, Integer value, LocalDateTime timestamp) {
        this.user = user;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UtilityType type;

    @Column(nullable = false)
    private Integer value;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
