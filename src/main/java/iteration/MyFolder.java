package iteration;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This implementation is part of Atlassian interview screenings. It's not allowed to use external libraries.
 * Compiled with Java 8. Like the question says "Reimplement this code so that its results will always be the same"
 * 
 * @author miguel.djebaile
 *
 * @param <T> generic type T
 * @param <U> generic type U
 */
public class MyFolder<T, U> implements Folder<T, U> {

    private static final Logger logger = Logger.getLogger(MyFolder.class.getName());

    /**
     * It applies a parameterized function to each element of the Queue.
     */
    public U fold(U u, Queue<T> ts, Function2<T, U, U> function) {

        // Even 'u' could be null without breaking the contract, let's make all params @NonNull
        if (u == null || ts == null || function == null) {
            // Runtime exception, unchecked
            throw new IllegalArgumentException("This method does not allow Nullable params");
        }

        while (!ts.isEmpty()) {
            u = function.apply(ts.poll(), u);

            // If necessary, override toString in U class.
            // Log levels INFO and higher will be automatically written
            logger.log(Level.FINE, "Function applied to item {0} from queue poll", u);
        }
        return u;
    }
}
