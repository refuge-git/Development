package school.sptech.refuge.core.domain.paginacao;

import java.util.List;

public class Page<T> {

    private final List<T> items;
    private final long total;
    private final int page;
    private final int size;

    public Page(List<T> items, long total, int page, int size) {
        this.items = items;
        this.total = total;
        this.page = page;
        this.size = size;
    }

    public List<T> getItems() {
        return items;
    }

    public long getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
