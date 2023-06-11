module com.imconsalting.projekat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires org.controlsfx.controls;
    requires java.sql;
    requires java.sql.rowset;




    opens com.imconsalting.projekat.customer to javafx.base,org.hibernate.orm.core;
    opens com.imconsalting.projekat.response to org.hibernate.orm.core;
    opens com.imconsalting.projekat.employee to org.hibernate.orm.core,javafx.base,jakarta.persistence;
    opens com.imconsalting.projekat.employee.privilege to org.hibernate.orm.core;
    opens com.imconsalting.projekat.profession to org.hibernate.orm.core;
    opens com.imconsalting.projekat.action to org.hibernate.orm.core,javafx.base;
    opens com.imconsalting.projekat.company to org.hibernate.orm.core,javafx.base;
    opens com.imconsalting.projekat.channel to org.hibernate.orm.core;
    opens com.imconsalting.projekat.empstatus to org.hibernate.orm.core;
    opens com.imconsalting.projekat.UI to org.hibernate.orm.core;



    exports com.imconsalting.projekat.UI;
    exports com.imconsalting.projekat.UI.paneli;
    opens com.imconsalting.projekat.UI.paneli to org.hibernate.orm.core;
    exports com.imconsalting.projekat;
    opens com.imconsalting.projekat to org.hibernate.orm.core;
    exports com.imconsalting.projekat.products;
    opens com.imconsalting.projekat.products to org.hibernate.orm.core;

}