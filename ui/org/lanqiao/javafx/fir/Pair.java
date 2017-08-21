package org.lanqiao.javafx.fir;

/**
 *
 * @author Mi Zhou
 * @param <L>
 * @param <R>
 */
public class Pair<L, R> {

	private final L l;
	private final R r;

	public static <L, R> Pair<L, R> of(L l, R r) {
		return new Pair<>(l, r);
	}

	public Pair(L l, R r) {
		this.l = l;
		this.r = r;
	}

	public L left() {
		return l;
	}

	public R right() {
		return r;
	}
}
