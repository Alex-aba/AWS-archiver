package com.aba.archiver.model;

import com.aba.archiver.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
 * The persistent class for the test database table.
 */
@Entity
@Table(name = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
 @Id
 @GeneratedValue(generator = "test")
 @GenericGenerator(name = "test", strategy = "com.aba.archiver.utils.CustomIdentityGenerator",
         parameters = {@Parameter(name = "SEQUENCE_PARAM", value = "test")})
 private Long id;

 @Column(name = "test_name")
 private String testName;

 @Column(name = "test_owner")
 private String testOwner;

 @Column(name = "status")
 @Enumerated(value = EnumType.STRING)
 private Status status;

 @Column(name = "test_type")
 private String testType;

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "job_id")
 private Job job;

 @OneToMany(mappedBy = "test", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
 private List<Testcase> testcases = new ArrayList<>();
}
