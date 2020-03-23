package kr.co.shineware.ds.aho_corasick;

import kr.co.shineware.ds.aho_corasick.model.AhoCorasickNode;

/**
 * FindContext keeps the state of trie traversal.
 *
 * @param <V> Trie element type
 */
public class FindContext<V> {
	
	private AhoCorasickNode<V> currentNode;

	public FindContext(final AhoCorasickNode<V> findRoot) {
		this.currentNode = findRoot;
	}

	public AhoCorasickNode<V> getCurrentNode() {
		return currentNode;
	}
	public void setCurrentNode(final AhoCorasickNode<V> currentNode) {
		this.currentNode = currentNode;
	}

	public AhoCorasickNode<V> getCurrentFailNode() {
		return currentNode.getFailNode();
	}

	public AhoCorasickNode<V>[] getCurrentChildren() {
		return currentNode.getChildren();
	}

	public boolean isCurrentRoot() {
		return this.currentNode.getParent() == null;
	}
	
}
