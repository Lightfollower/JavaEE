package adminConsole.persist;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_ {
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Category> category;
}
