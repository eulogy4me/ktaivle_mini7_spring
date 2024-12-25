package com.aivle.mini7.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.hibernate.type.descriptor.sql.spi.SqlTypeDescriptorContributor;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super(DatabaseVersion.make(3, 0));
    }

    // Identity column support
    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }
        };
    }

    @Override
    public void contributeTypes(SqlTypeDescriptorContributor contributor) {
        contributor.addSqlTypeDescriptor(SqlTypes.INTEGER, new org.hibernate.type.descriptor.sql.IntegerTypeDescriptor());
        contributor.addSqlTypeDescriptor(SqlTypes.VARCHAR, new org.hibernate.type.descriptor.sql.VarcharTypeDescriptor());
        contributor.addSqlTypeDescriptor(SqlTypes.DOUBLE, new org.hibernate.type.descriptor.sql.DoubleTypeDescriptor());
    }
}
