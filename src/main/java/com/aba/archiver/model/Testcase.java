package com.aba.archiver.model;

import com.aba.archiver.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The persistent class for the testcase database table.
 */
@Entity
@Table(name = "testcase")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Testcase {
    @Id
    @GeneratedValue(generator = "testcase")
    @GenericGenerator(name = "testcase", strategy = "com.aba.archiver.utils.CustomIdentityGenerator",
            parameters = {@Parameter(name = "SEQUENCE_PARAM", value = "testcase")})
    private Long id;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;
}
