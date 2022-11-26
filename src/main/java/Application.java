import com.example.org.util.HibernateUtil;
import com.example.org.util.TokenUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Application {

    public static void main(String[] args) {
        oneWay();
        System.out.println("done");
    }

    private static void oneWay() {

        System.out.println("One Way");
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();

            transaction.commit();
            System.out.println("Yaayee");
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            System.out.println("Nawwwwww");
        }

    }


}