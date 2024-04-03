package db.changelog

databaseChangeLog
        {
            changeSet(id: '1', author: 'alex', runInTransaction: true)
                    {
                        createTable(tableName: 'job')
                                {
                                    column(name: 'id', type: 'BIGINT', autoIncrement: true)
                                            {
                                                constraints(primaryKey: true, primaryKeyName: 'job_pkey')
                                            }
                                    column(name: 'job_name', type: 'VARCHAR(100)')
                                    column(name: 'user_name', type: 'VARCHAR(100)')
                                }

                        createTable(tableName: 'test')
                                {
                                    column(name: 'id', type: 'BIGINT', autoIncrement: true)
                                            {
                                                constraints(primaryKey: true, primaryKeyName: 'test_pkey')
                                            }
                                    column(name: 'test_name', type: 'VARCHAR(100)')
                                    column(name: 'test_owner', type: 'VARCHAR(100)')
                                    column(name: 'job_id', type: 'BIGINT')
                                    column(name: 'status', type: 'VARCHAR(30)')
                                    column(name: 'test_type', type: 'VARCHAR(100)')
                                }

                        createTable(tableName: 'testcase')
                                {
                                    column(name: 'id', type: 'BIGINT', autoIncrement: true)
                                            {
                                                constraints(primaryKey: true, primaryKeyName: 'testcase_pkey')
                                            }
                                    column(name: 'status', type: 'VARCHAR(30)')
                                    column(name: 'name', type: 'VARCHAR(30)')
                                    column(name: 'display_name', type: 'VARCHAR(255)')
                                    column(name: 'test_id', type: 'BIGINT')
                                }

                        addForeignKeyConstraint(baseColumnNames: 'job_id', baseTableName: 'test', constraintName: 'fk_job_test_id', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'job', validate: true)
                        addForeignKeyConstraint(baseColumnNames: 'test_id', baseTableName: 'testcase', constraintName: 'fk_test_testcase_id', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id', referencedTableName: 'test', validate: true)

                        rollback
                                {
                                    delete(schemaName: 'public', tableName: 'testcase')
                                    delete(schemaName: 'public', tableName: 'test')
                                    delete(schemaName: 'public', tableName: 'job')

                                    dropForeignKeyConstraint(baseTableName: 'test', constraintName: 'fk_test_testcase_id')
                                    dropForeignKeyConstraint(baseTableName: 'test', constraintName: 'fk_job_test_id')

                                    rollback "drop table testcase"
                                    rollback "drop table test"
                                    rollback "drop table job"
                                }
                    }
        }