package com.aivle.mini7.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
// import org.hibernate.boot.model.TypeContributions;
// import org.hibernate.type.StandardBasicTypes;
// import org.hibernate.type.Type;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super(DatabaseVersion.make(3, 0));
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }
        };
    }
}
