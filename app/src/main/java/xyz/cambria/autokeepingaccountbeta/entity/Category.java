package xyz.cambria.autokeepingaccountbeta.entity;

public enum Category {

    NONE("未分类" , CategoryParent.NONE),
    DRINK("饮料" , CategoryParent.FOOD),
    DINNER("晚餐" , CategoryParent.FOOD),
    LAUNCH("午餐" , CategoryParent.FOOD),
    WATER("饮水" , CategoryParent.FOOD);

    public final String name;
    public final CategoryParent parent;

    Category(String name , CategoryParent parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name=" + name +
                ", parent=" + parent.name +
                '}';
    }

    public enum CategoryParent {

        NONE("未分类"),
        FOOD("饮食");

        public final String name;

        CategoryParent(String name) {
            this.name = name;
        }

    }
}


