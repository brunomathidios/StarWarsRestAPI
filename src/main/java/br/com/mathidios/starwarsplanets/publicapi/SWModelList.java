package br.com.mathidios.starwarsplanets.publicapi;

import java.io.Serializable;
import java.util.List;

public class SWModelList<T> implements Serializable {

	public int count;
    public String next;
    public String previous;
    public List<T> results;

    public boolean hasMore() {
        return (next != null && !next.isEmpty());
}
}
