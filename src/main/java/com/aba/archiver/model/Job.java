package com.aba.archiver.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the job database table.
 */
@Entity
@Table(name = "job")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(generator = "job")
    @GenericGenerator(name = "job", strategy = "com.aba.archiver.utils.CustomIdentityGenerator",
            parameters = {@Parameter(name = "SEQUENCE_PARAM", value = "job")})
    private Long Id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "job", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Test> tests = new ArrayList<>();
}
