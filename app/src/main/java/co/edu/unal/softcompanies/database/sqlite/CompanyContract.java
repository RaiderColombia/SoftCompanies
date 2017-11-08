package co.edu.unal.softcompanies.database.sqlite;


import android.provider.BaseColumns;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/5/17.
 * Universidad Nacional de Colombia
 */
public final class CompanyContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private CompanyContract() {}

    /* Inner class that defines the table contents */
    public static class CompanyTable implements BaseColumns {
        public static final String TABLE_NAME = "company";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PRODUCTS = "products";
        public static final String COLUMN_NAME_CLASSIFICATION = "classification";
    }
}