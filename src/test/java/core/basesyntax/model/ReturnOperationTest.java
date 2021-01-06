package core.basesyntax.model;

import static org.junit.Assert.assertEquals;

import core.basesyntax.db.Warehouse;
import core.basesyntax.exception.InvalidOperationException;
import core.basesyntax.model.entities.Fruit;
import core.basesyntax.model.impl.BalanceOperation;
import core.basesyntax.model.impl.ReturnOperation;
import org.junit.After;
import org.junit.Test;

public class ReturnOperationTest {
    private static final Fruit TEST_PRODUCT = new Fruit("test");
    private static final Integer TEST_AMOUNT = 10;
    private static final ReturnOperation<Fruit> RETURN_OPERATION =
            new ReturnOperation<>(Warehouse.getFruitStorage());

    @Test
    public void executeReturnOperationTest_ok() {
        new BalanceOperation<>(Warehouse.getFruitStorage()).execute(TEST_PRODUCT, TEST_AMOUNT);
        RETURN_OPERATION.execute(TEST_PRODUCT, TEST_AMOUNT);
        assertEquals(1, Warehouse.getFruitStorage().size());
    }

    @Test(expected = InvalidOperationException.class)
    public void executeReturnOperation_notOk() {
        RETURN_OPERATION.execute(TEST_PRODUCT, TEST_AMOUNT);
    }

    @After
    public void tearDown() {
        Warehouse.getFruitStorage().clear();
    }
}
