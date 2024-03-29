package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Sequence;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementation of the Abstract Syntax Tree node for a sequence building expression.
 * @author Marc Pantel
 *
 */
public class SequenceImpl implements Sequence {

	private LinkedList<Expression> values;

	public SequenceImpl(Iterable<Expression> _values) {
		this();
		for (Expression _value : _values) {
			this.values.add(_value);
		}
	}

	public SequenceImpl() {
		this.values = new LinkedList<>();
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Sequence#add(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public void add(Expression _value) {
		this.values.add(_value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder _result = new StringBuilder("{ ");
		Iterator<Expression> _iter = this.values.iterator();
		if (_iter.hasNext()) {
			_result.append(_iter.next());
			while (_iter.hasNext()) {
				_result.append(" ,").append(_iter.next());
			}
		}
		return _result + " }";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		// throw new SemanticsUndefinedException("Semantics getType undefined in PointerTypeImpl.");
		SequenceTypeImpl _result = new SequenceTypeImpl();
		for (Expression _value : this.values) {
			_result.add(_value.getType());
		}
		return _result;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		for (Expression e : this.values) {
			fragment.append(e.getCode(_factory));
		}
		fragment.addComment("Declaration de la sequence");
		return fragment;
	}

}
