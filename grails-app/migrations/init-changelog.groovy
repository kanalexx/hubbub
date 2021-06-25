databaseChangeLog = {

    changeSet(author: "KanAA (generated)", id: "1624634204636-1") {
        createTable(tableName: "following") {
            column(name: "following_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT")
        }
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-2") {
        createTable(tableName: "post") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "postPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "content", type: "VARCHAR(1000)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-3") {
        createTable(tableName: "post_tags") {
            column(name: "post_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "tag_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }
        }
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-4") {
        createTable(tableName: "profile") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "profilePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "jabber_address", type: "VARCHAR(255)")

            column(name: "homepage", type: "VARCHAR(255)")

            column(name: "bio", type: "VARCHAR(1000)")

            column(name: "photo", type: "BLOB(2097152)")

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "skin", type: "VARCHAR(9)")

            column(name: "country", type: "VARCHAR(255)")

            column(name: "full_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "timezone", type: "VARCHAR(255)")

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-5") {
        createTable(tableName: "tag") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tagPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-6") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(8)") {
                constraints(nullable: "false")
            }

            column(name: "login_id", type: "VARCHAR(20)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-7") {
        addUniqueConstraint(columnNames: "login_id", constraintName: "UC_USERLOGIN_ID_COL", tableName: "user")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-8") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "post", constraintName: "FK72mt33dhhs48hf9gcqrq4fxte", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-9") {
        addForeignKeyConstraint(baseColumnNames: "following_id", baseTableName: "following", constraintName: "FK8mtuqxfx1t7gtaabu19q7qt6b", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-10") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "profile", constraintName: "FKawh070wpue34wqvytjqr4hj5e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-11") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "following", constraintName: "FKe8xr7t6y8n6nktnsrpxmvubqt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-12") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "tag", constraintName: "FKld85w5kr7ky5w4wda3nrdo0p8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-13") {
        addForeignKeyConstraint(baseColumnNames: "post_id", baseTableName: "post_tags", constraintName: "FKmmtgl185ka210lj8kenewllt1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "post", validate: "true")
    }

    changeSet(author: "KanAA (generated)", id: "1624634204636-14") {
        addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "post_tags", constraintName: "FKp7cfgjsujc2vl3p88qfqkpws6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tag", validate: "true")
    }
}
