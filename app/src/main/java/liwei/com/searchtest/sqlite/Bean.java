package liwei.com.searchtest.sqlite;

/**
 * Created by wu  suo  wei on 2017/5/7.
 */

public class Bean {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
