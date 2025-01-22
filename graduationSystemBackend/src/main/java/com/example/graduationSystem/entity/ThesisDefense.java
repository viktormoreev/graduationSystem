package com.example.graduationSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "thesis_defenses")
public class ThesisDefense extends IdGenerator {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thesis_id", nullable = false)
    private Thesis thesis;

    @Temporal(TemporalType.DATE)
    private Date defenseDate;

    @NotNull(message = "Grade must not be null")
    @DecimalMin(value = "2.0", message = "Grade must be at least 2.0")
    @DecimalMax(value = "6.0", message = "Grade must not exceed 6.0")
    @Column(name = "grade", nullable = false)
    private Double grade;

    @ManyToMany
    @JoinTable(
            name = "defense_committee",
            joinColumns = @JoinColumn(name = "defense_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private Set<Professor> committeeMembers;

}