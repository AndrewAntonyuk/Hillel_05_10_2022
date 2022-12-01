public class MathOperation {
    ItemSource itemSource;

    public MathOperation(ItemSource itemSource) {
        this.itemSource = itemSource;
    }

    Integer sum() {
        checkLegalItem();

        Integer sum = 0;
        for (Integer i : itemSource.getItems()) {
            sum += i;
        }
        return sum;
    }

    Integer avg() {
        checkLegalItem();

        return sum() / itemSource.getItems().size();
    }

    Integer max() {
        checkLegalItem();

        Integer maximum = Integer.MIN_VALUE;

        for (Integer i : itemSource.getItems()) {
            if (i > maximum) maximum = i;
        }
        return maximum;
    }

    private void checkLegalItem() {
        if (itemSource.getItems().size() == 0) {
            throw new IllegalStateException("List can't be empty");
        }
    }
}
