package rhcloud.com.financialcontrol.impl;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import rhcloud.com.droidutils.tabutil.tabutil.interfaces.Consumer;
import rhcloud.com.financialcontrol.dao.ExpenseDAO;
import rhcloud.com.financialcontrol.javabean.Expense;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 *
 * A implementation of {@link ExpenseDAO} using the framework {@link <a href="https://realm.io/docs/java/latest/">Realm Android</a>}
 *
 * @version 1.0
 * @since 1.0
 * @see <a href="https://realm.io/docs/java/latest/">Realm Android</a>
 */
public class ExpenseDAORealm implements ExpenseDAO {

    /**
     * The context to start the {@link RealmConfiguration}
     * @since 1.0
     */
    private Context context;

    /**
     * The {@link Realm} object to transaction over the database
     * @since 1.0
     */
    private Realm realm;

    /**
     * A callback for the {@link io.realm.RealmAsyncTask}
     * @since 1.0
     */
    private Consumer consumer;

    /**
     * Default Constructor with no callback call.
     * @param context , the required context.
     */
    public ExpenseDAORealm(Context context) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmConfig);
        this.context = context;

        realm = Realm.getDefaultInstance();
    }

    /**
     * Constructor design to give back the callback call.
     * @param context , the required context.
     * @param consumer , the callback to be call
     * @since 1.0
     */
    public ExpenseDAORealm(Context context, Consumer consumer) {
        this(context);
        this.consumer = consumer;
    }

    @Override
    public void addExpense(Expense expense) {
        checkForNullExpense(expense);
        realm.beginTransaction();
        expense.setIdExpense(getNextKey());
        realm.insert(expense);
        realm.commitTransaction();
    }

    @Override
    public void removeById(final int expenseId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<Expense> result = bgRealm.where(Expense.class).equalTo("idExpense", expenseId).findAll();
                result.deleteAllFromRealm();
                callConsumer();
            }
        });
    }

    @Override
    public void removeExpense(final Expense expense) {
        checkForNullExpense(expense);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                RealmResults<Expense> result = bgRealm.where(Expense.class).equalTo("idExpense", expense.getIdExpense()).findAll();
                result.deleteAllFromRealm();
                callConsumer();
            }
        });
    }

    @Override
    public void updateExpense(final Expense expense) {
        checkForNullExpense(expense);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.insertOrUpdate(expense);
                callConsumer();
            }
        });
    }

    @Override
    public List<Expense> getExpenseList() {
        RealmResults<Expense> result = realm.where(Expense.class).findAll();
        return realm.copyFromRealm(result);
    }

    @Override
    public Expense getExpenseById(int id) {
        Expense exp = realm.where(Expense.class).equalTo("idExpense", id).findFirst();
        checkForNullExpense(exp, "Could found the expense with id [ " + id + "]");
        return realm.copyFromRealm(exp);
    }

    /**
     * Default get for the {@link ExpenseDAORealm#consumer}
     * @return consumer
     * @since 1.0
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     * Default setter for the {@link ExpenseDAORealm#consumer}
     * @param consumer
     * @since 1.0
     */
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    private int getNextKey() {
        return realm.where(Expense.class).max("idExpense").intValue() + 1;
    }

    /**
     * Check if the {@link Expense} is null
     * @param expense , the expense to be checked.
     * @param message , the message to be throw in the {@link NullPointerException}
     * @throws NullPointerException if the {@link Expense} is null.
     * @since 1.0
     */
    private void checkForNullExpense(Expense expense, String message) {
        if (expense == null) {
            throw new NullPointerException(message == null ? "The expense cannot be null" : message.isEmpty() ? "The expense cannot be null" : message);
        }
    }

    /**
     * Check if the {@link Expense} is null
     * @param expense , the expense to be checked.
     * @throws NullPointerException if the {@link Expense} is null.
     * @since 1.0
     */
    private void checkForNullExpense(Expense expense) {
        checkForNullExpense(expense, "The expense cannot be null");
    }

    /**
     * Callback called whatever a {@link io.realm.RealmAsyncTask} is call
     * @since 1.0
     */
    private void callConsumer() {
        if (consumer != null) {
            consumer.onDataChange();
        }
    }
}
